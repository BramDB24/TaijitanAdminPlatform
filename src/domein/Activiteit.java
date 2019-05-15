package domein;

import domein.DTO.ActiviteitDTO;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Activiteit")
public class Activiteit implements ActiviteitInterface, Serializable {

    @Id
    private int activiteitId;
    private String naam;
    private LocalDateTime datum;
    @ManyToMany
    @JoinTable(
            name = "LidActiviteit",
            joinColumns = @JoinColumn(name = "activiteitid"),
            inverseJoinColumns = @JoinColumn(name = "gebruikersnaam"))

    private List<Gebruiker> aanwezigen;

    public Activiteit(ActiviteitDTO dto) {
        this.naam = dto.getNaam();
        this.datum = dto.getDatum();
    }

    protected Activiteit() {

    }

    public ActiviteitDTO getActiviteitDTO() {
        return createDTO();
    }

    private ActiviteitDTO createDTO() {
        ActiviteitDTO dto = new ActiviteitDTO();
        dto.setDatum(datum);
        dto.setNaam(naam);
        dto.setAantalAanwezigen(aanwezigen.size());
        return dto;
    }
}
