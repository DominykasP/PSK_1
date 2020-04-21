package lt.vu.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "select client from Client as client")
})
@Table(name = "CLIENT")
@Data
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE_NR")
    private String phoneNr;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();
}
