package lt.vu.persistence;

import lt.vu.entities.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ReservationsDAO {
    @Inject
    private EntityManager em;

    public void persist(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findOne(Integer id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> loadAll() {
        return em.createNamedQuery("Reservation.findAll", Reservation.class).getResultList();
    }

    public List<Reservation> findAllCheckedIn() {
        return em.createNamedQuery("Reservation.findAllCheckedIn", Reservation.class).getResultList();
    }

    public List<Reservation> findNotCheckedIn() {
        return em.createNamedQuery("Reservation.findNotCheckedIn", Reservation.class).getResultList();
    }

    public Reservation update(Reservation reservation) {
        return em.merge(reservation);
    }

    public void remove(Reservation reservation) {
        em.remove(reservation);
    }
}
