package lt.vu.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "RoomType.findAll", query = "select roomType from RoomType as roomType")
})
@Table(name = "ROOMTYPE")
@Data
public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SIZE")
    private Integer size;

    @Column(name = "NIGHT_PRICE")
    private Integer nightPrice;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
