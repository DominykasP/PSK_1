package lt.vu.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findAll", query = "select reservation from Reservation as reservation"),
        @NamedQuery(name = "Reservation.findAllCheckedIn", query = "select reservation from Reservation as reservation where reservation.isCheckedIn = true"),
        @NamedQuery(name = "Reservation.findNotCheckedIn", query = "select reservation from Reservation as reservation where reservation.isCheckedIn = false")
})
@Table(name = "RESERVATION")
@Data
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;

    @Column(name = "IS_CHECKED_IN")
    private Boolean isCheckedIn;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToMany()
    @JoinTable(name = "RESERVATION_ADITIONAL_REQUEST")
    private List<AditionalRequest> aditionalRequests = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
