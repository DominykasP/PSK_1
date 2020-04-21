package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Room;
import lt.vu.entities.RoomType;
import lt.vu.persistence.RoomTypesDAO;
import lt.vu.persistence.RoomsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Rooms {
    @Inject
    private RoomsDAO roomsDAO;
    @Inject
    private RoomTypesDAO roomTypesDAO;

    @Getter
    private List<Room> allRooms;
    @Getter
    private List<RoomType> allRoomTypes;

    @Getter @Setter
    private Room roomToCreate = new Room();
    @Getter @Setter
    private Integer roomTypeId;

    @PostConstruct
    public void init() {
        allRooms = roomsDAO.loadAll();
        allRoomTypes = roomTypesDAO.loadAll();
    }

    @Transactional
    public String createRoom() {
        RoomType roomType = roomTypesDAO.findOne(roomTypeId);
        roomToCreate.setRoomType(roomType);

        roomsDAO.persist(roomToCreate);
        return "roomDetails?faces-redirect=true&roomId=" + roomToCreate.getId();
    }
}
