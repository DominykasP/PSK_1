package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.model.Roomtype;
import lt.vu.mybatis.model.Reservation;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.ReservationMapper;
import lt.vu.mybatis.model.Room;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Model
public class ReservationDetailsMyBatis {
    @Inject
    private ReservationMapper reservationMapper;

    @Getter @Setter
    private Reservation reservation;

    @Getter @Setter
    private Long toPay;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer reservationId = Integer.parseInt(requestParameters.get("reservationId"));
        reservation = reservationMapper.selectByPrimaryKey(reservationId);
        toPay = calculateTotalPrice();
    }

    @Transactional
    @LoggedInvocation
    public String checkIn() {
        reservation.setIsCheckedIn(true);
        reservationMapper.updateByPrimaryKey(reservation);

        return "index?faces-redirect=true";
    }

    @Transactional
    @LoggedInvocation
    public String checkOut() {
        reservationMapper.deleteByPrimaryKey(reservation.getId());
        return "index?faces-redirect=true";
    }

    private Long calculateTotalPrice() {
        LocalDateTime dateFrom = reservation.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dateTo = reservation.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        long daysBetween = Duration.between(dateFrom, dateTo).toDays();

        Roomtype roomType = reservation.getRoom().getRoomtype();
        return roomType.getNightPrice() * daysBetween;
    }
}
