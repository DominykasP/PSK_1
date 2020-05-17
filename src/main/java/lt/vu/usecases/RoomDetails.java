package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Room;
import lt.vu.entities.RoomType;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.RoomTypesDAO;
import lt.vu.persistence.RoomsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class RoomDetails implements Serializable {
    @Inject
    private RoomsDAO roomsDAO;
    @Inject
    private RoomTypesDAO roomTypesDAO;

    @Getter @Setter
    private Room room;
    @Getter @Setter
    private List<RoomType> allRoomTypes;

    @Getter @Setter
    private Integer numberForUpdate;
    @Getter @Setter
    private Integer roomTypeIdForUpdate;
    @Getter @Setter
    private RoomType roomTypeForUpdate;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer roomId = Integer.parseInt(requestParameters.get("roomId"));
        room = roomsDAO.findOne(roomId);

        allRoomTypes = roomTypesDAO.loadAll();
    }

    @Transactional
    @LoggedInvocation
    public String updateRoom() {
        roomTypeForUpdate = roomTypesDAO.findOne(roomTypeIdForUpdate);
        room.setNumber(numberForUpdate);
        room.setRoomType(roomTypeForUpdate);

        roomsDAO.update(room);
        return "roomDetails?faces-redirect=true&roomId=" + room.getId();
    }


}
