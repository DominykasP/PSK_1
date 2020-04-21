package lt.vu.persistence;

import lt.vu.entities.AditionalRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AditionalRequestsDAO {
    @Inject
    private EntityManager em;

    public void persist(AditionalRequest aditionalRequest) {
        em.persist(aditionalRequest);
    }

    public AditionalRequest findOne(Integer id) {
        return em.find(AditionalRequest.class, id);
    }

    public List<AditionalRequest> loadAll() {
        return em.createNamedQuery("AditionalRequest.findAll", AditionalRequest.class).getResultList();
    }

    public AditionalRequest update(AditionalRequest aditionalRequest) {
        return em.merge(aditionalRequest);
    }
}
