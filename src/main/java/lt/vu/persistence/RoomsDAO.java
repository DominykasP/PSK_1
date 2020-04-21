package lt.vu.persistence;

import lt.vu.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@ApplicationScoped
public class RoomsDAO {
    @Inject
    private EntityManager em;

    public void persist(Room room) {
        em.persist(room);
    }

    public Room findOne(Integer id) {
        return em.find(Room.class, id);
    }

    public List<Room> loadAll() {
        return em.createNamedQuery("Room.findAll", Room.class).getResultList();
    }

    public List<Room> findEmptyByDates(Date dateFrom, Date dateTo) {
        return em.createNamedQuery("Room.findEmptyByDate", Room.class)
                .setParameter("date_from", dateFrom)
                .setParameter("date_to", dateTo)
                .getResultList();
    }

    public Room update(Room room) {
        return em.merge(room);
    }
}
