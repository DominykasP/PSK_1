package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Reservation;
import lt.vu.entities.RoomType;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ReservationsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Model
public class ReservationDetails {
    @Inject
    private ReservationsDAO reservationsDAO;

    @Getter @Setter
    private Reservation reservation;

    @Getter @Setter
    private Long toPay;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer reservationId = Integer.parseInt(requestParameters.get("reservationId"));
        reservation = reservationsDAO.findOne(reservationId);
        toPay = calculateTotalPrice();
    }

    @Transactional
    @LoggedInvocation
    public String checkIn() {
        reservation.setIsCheckedIn(true);
        reservationsDAO.update(reservation);

        return "index?faces-redirect=true";
    }

    @Transactional
    @LoggedInvocation
    public String checkOut() {
        reservationsDAO.remove(reservation);
        return "index?faces-redirect=true";
    }

    private Long calculateTotalPrice() {
        LocalDateTime dateFrom = reservation.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dateTo = reservation.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        long daysBetween = Duration.between(dateFrom, dateTo).toDays();

        RoomType roomType = reservation.getRoom().getRoomType();
        return roomType.getNightPrice() * daysBetween;
    }
}
