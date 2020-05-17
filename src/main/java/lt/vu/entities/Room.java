package lt.vu.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select room from Room as room"),
        @NamedQuery(name = "Room.findEmptyByDate", query = "select room from Room as room where room not in (select reservation.room from Reservation as reservation where not ((reservation.dateFrom < :date_from and reservation.dateTo <= :date_from) or (reservation.dateFrom >= :date_to and reservation.dateTo > :date_to)))")
})
@Table(name = "ROOM")
@Data
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NUMBER")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "ROOM_TYPE_ID")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
