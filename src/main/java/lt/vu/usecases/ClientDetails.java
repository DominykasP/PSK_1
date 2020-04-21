package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class ClientDetails {
    @Inject
    private ClientsDAO clientsDAO;

    @Getter @Setter
    private Client client;

    @Getter @Setter
    private String nameForUpdate;
    @Getter @Setter
    private String surnameForUpdate;
    @Getter @Setter
    private String phoneNrForUpdate;
    @Getter @Setter
    private String emailForUpdate;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer clientId = Integer.parseInt(requestParameters.get("clientId"));
        client = clientsDAO.findOne(clientId);
    }

    @Transactional
    @LoggedInvocation
    public String updateClient() {
        client.setName(nameForUpdate);
        client.setSurname(surnameForUpdate);
        client.setPhoneNr(phoneNrForUpdate);
        client.setEmail(emailForUpdate);

        clientsDAO.update(client);
        return "clientDetails?faces-redirect=true&clientId=" + client.getId();
    }
}
