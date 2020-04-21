package lt.vu.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "AditionalRequest.findAll", query = "select aditionalRequest from AditionalRequest as aditionalRequest")
})
@Table(name = "ADITIONAL_REQUEST")
@Data
public class AditionalRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REQUEST")
    private String request;

//    @ManyToMany(mappedBy = "aditionalRequests")
//    private List<Reservation> reservations = new ArrayList<>();
}
