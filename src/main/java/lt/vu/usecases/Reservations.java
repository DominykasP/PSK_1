package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Reservation;
import lt.vu.persistence.ReservationsDAO;
import lt.vu.persistence.RoomsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Model
public class Reservations {
    @Inject
    private ReservationsDAO reservationsDAO;
    @Inject
    private RoomsDAO roomsDAO;

    @Getter
    private List<Reservation> checkedInReservations;
    @Getter
    private List<Reservation> futureReservations;

    @Getter @Setter
    private Date dateFromForSearch;
    @Getter @Setter
    private Date dateToForSearch;

    @PostConstruct
    public void init() {
        checkedInReservations = reservationsDAO.findAllCheckedIn();
        futureReservations = reservationsDAO.findNotCheckedIn();
    }
}
