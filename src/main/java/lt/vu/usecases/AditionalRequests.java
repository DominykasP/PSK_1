package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.AditionalRequest;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AditionalRequestsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AditionalRequests {
    @Inject
    private AditionalRequestsDAO aditionalRequestsDAO;

    @Getter
    private List<AditionalRequest> allAditionalRequests;

    @Getter @Setter
    private AditionalRequest aditionalRequestToCreate = new AditionalRequest();

    @PostConstruct
    public void init() {
        allAditionalRequests = aditionalRequestsDAO.loadAll();
    }

    @Transactional
    @LoggedInvocation
    public String createAditionalRequest() {
        aditionalRequestsDAO.persist(aditionalRequestToCreate);
        return "aditionalRequests?faces-redirect=true";
    }
}
