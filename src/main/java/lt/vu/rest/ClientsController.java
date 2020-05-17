package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.persistence.ClientsDAO;
import lt.vu.rest.dtos.ClientDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/client")
public class ClientsController {
    @Inject
    @Setter
    @Getter
    ClientsDAO clientsDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Client> clients = clientsDAO.loadAll();
        List<ClientDTO> clientsContract = clients.stream().map(ClientDTO::new).collect(Collectors.toList());
        return Response.ok(clientsContract).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Client client = clientsDAO.findOne(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ClientDTO clientContract = new ClientDTO(client);
        return Response.ok(clientContract).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer clientId, ClientDTO clientData) {
        try {
            Client existingClient = clientsDAO.findOne(clientId);
            if (existingClient == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            existingClient.setName(clientData.getName());
            existingClient.setSurname(clientData.getSurname());
            existingClient.setPhoneNr(clientData.getPhoneNr());
            existingClient.setEmail(clientData.getEmail());

            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ClientDTO clientData) {
        Client newClient = new Client();
        newClient.setName(clientData.getName());
        newClient.setSurname(clientData.getSurname());
        newClient.setPhoneNr(clientData.getPhoneNr());
        newClient.setEmail(clientData.getEmail());

        clientsDAO.persist(newClient);
        return Response.ok().build();
    }
}
