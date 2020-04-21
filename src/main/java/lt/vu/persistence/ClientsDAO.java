package lt.vu.persistence;

import lt.vu.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClientsDAO {
    @Inject
    private EntityManager em;

    public void persist(Client client) {
        em.persist(client);
    }

    public Client findOne(Integer id) {
        return em.find(Client.class, id);
    }

    public List<Client> loadAll() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    public Client update(Client client) {
        return em.merge(client);
    }
}
