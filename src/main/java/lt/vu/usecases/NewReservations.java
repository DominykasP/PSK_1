package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.AditionalRequest;
import lt.vu.entities.Client;
import lt.vu.entities.Reservation;
import lt.vu.entities.Room;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AditionalRequestsDAO;
import lt.vu.persistence.ClientsDAO;
import lt.vu.persistence.ReservationsDAO;
import lt.vu.persistence.RoomsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Model
public class NewReservations implements Serializable {
    @Inject
    private ReservationsDAO reservationsDAO;
    @Inject
    private RoomsDAO roomsDAO;
    @Inject
    private ClientsDAO clientsDAO;
    @Inject
    private AditionalRequestsDAO aditionalRequestsDAO;

    @Getter @Setter
    private List<AditionalRequest> allAditionalRequests;
    @Getter @Setter
    private Integer[] selectedAditionalRequestsIds;

    @Getter @Setter
    private Date dateFromForSearch;
    @Getter @Setter
    private Date dateToForSearch;
    @Getter @Setter
    private String dateFromFormatted;
    @Getter @Setter
    private String dateToFormatted;

    @Getter @Setter
    private List<Room> freeRooms = new ArrayList<>();
    @Getter @Setter
    private Integer roomIdToReserve;

    @Getter @Setter
    private List<Client> allClients;
    @Getter @Setter
    private Integer clientIdToReserve;

    @PostConstruct
    public void init() throws ParseException {
        allClients = clientsDAO.loadAll();
        allAditionalRequests = aditionalRequestsDAO.loadAll();

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        dateFromFormatted = requestParameters.get("dateFrom");
        dateToFormatted = requestParameters.get("dateTo");

        if (dateFromFormatted == null || dateToFormatted == null) {
            freeRooms = new ArrayList<>();
        } else {
            dateFromForSearch = new SimpleDateFormat("yyyyMMdd").parse(dateFromFormatted);
            dateToForSearch = new SimpleDateFormat("yyyyMMdd").parse(dateToFormatted);
            freeRooms = roomsDAO.findEmptyByDates(dateFromForSearch, dateToForSearch);
        }
    }

    @LoggedInvocation
    public String searchForFreeRooms() {
        dateFromFormatted = new SimpleDateFormat("yyyyMMdd").format(dateFromForSearch);
        dateToFormatted = new SimpleDateFormat("yyyyMMdd").format(dateToForSearch);
        return "newReservations?faces-redirect=true&dateFrom=" + dateFromFormatted + "&dateTo=" + dateToFormatted;
    }

    @Transactional
    @LoggedInvocation
    public String createReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(dateFromForSearch);
        reservation.setDateTo(dateToForSearch);

        Room room = roomsDAO.findOne(roomIdToReserve);
        reservation.setRoom(room);

        Client client = clientsDAO.findOne(clientIdToReserve);
        reservation.setClient(client);

        List<AditionalRequest> aditionalRequestList = new ArrayList<>();
        for (Integer requestId : selectedAditionalRequestsIds) {
            aditionalRequestList.add(aditionalRequestsDAO.findOne(requestId));
        }
        reservation.setAditionalRequests(aditionalRequestList);

        reservation.setIsCheckedIn(false);
        reservationsDAO.persist(reservation);
        return "reservationDetails?faces-redirect=true&reservationId=" + reservation.getId();
    }
}
