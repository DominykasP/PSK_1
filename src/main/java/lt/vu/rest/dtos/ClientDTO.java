package lt.vu.rest.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Client;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNr;
    private String email;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.phoneNr = client.getPhoneNr();
        this.email = client.getEmail();
    }
}
