package domein;

import domein.DTO.GebruikerDTO;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Lid")
@Table(name = "lid")
public class Lid extends Gebruiker implements Serializable{

    protected Lid() {
        super();
    }

    public Lid(GebruikerDTO dto) {
        
    }
}
