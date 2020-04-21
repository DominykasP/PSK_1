package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Clients {
    @Inject
    private ClientsDAO clientsDAO;

    @Getter
    private List<Client> allClients;

    @Getter @Setter
    private Client clientToCreate = new Client();

    @PostConstruct
    public void init() {
        allClients = clientsDAO.loadAll();
    }

    @Transactional
    @LoggedInvocation
    public String createClient() {
        clientsDAO.persist(clientToCreate);
        return "clientDetails?faces-redirect=true&clientId=" + clientToCreate.getId();
    }
}
