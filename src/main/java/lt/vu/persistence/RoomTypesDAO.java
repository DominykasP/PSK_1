package lt.vu.persistence;

import lt.vu.entities.RoomType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RoomTypesDAO {
    @Inject
    private EntityManager em;

    public void persist(RoomType roomType) {
        em.persist(roomType);
    }

    public RoomType findOne(Integer id) {
        return em.find(RoomType.class, id);
    }

    public List<RoomType> loadAll() {
        return em.createNamedQuery("RoomType.findAll", RoomType.class).getResultList();
    }

    public RoomType update(RoomType roomType) {
        return em.merge(roomType);
    }
}
