package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.RoomType;
import lt.vu.persistence.RoomTypesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class RoomTypes {
    @Inject
    private RoomTypesDAO roomTypesDAO;

    @Getter
    private List<RoomType> allRoomTypes;

    @Getter @Setter
    private RoomType roomTypeToCreate = new RoomType();

    @PostConstruct
    public void init() {
        allRoomTypes = roomTypesDAO.loadAll();
    }

    @Transactional
    public String createRoomType() {
        roomTypesDAO.persist(roomTypeToCreate);
        return "roomTypes?faces-redirect=true";
    }
}
