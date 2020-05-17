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

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
