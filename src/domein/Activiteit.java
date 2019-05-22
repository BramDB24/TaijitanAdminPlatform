package domein;

import domein.DTO.ActiviteitDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
    private LocalDate startDatum;
    private LocalDate eindDatum;
    private int maxAantal;
    @ManyToMany
    @JoinTable(
            name = "LidActiviteit",
            joinColumns = @JoinColumn(name = "activiteitid"),
            inverseJoinColumns = @JoinColumn(name = "gebruikersnaam"))

    private List<Gebruiker> aanwezigen;

    public Activiteit(ActiviteitDTO dto) {
        this.naam = dto.getNaam();
        this.startDatum = dto.getStartDatum();
        this.eindDatum = dto.getEindDatum();
        this.maxAantal = dto.getMaxAantal();
    }

    protected Activiteit() {

    }

    public ActiviteitDTO getActiviteitDTO() {
        return createDTO();
    }

    private ActiviteitDTO createDTO() {
        ActiviteitDTO dto = new ActiviteitDTO();
        dto.setStartDatum(startDatum);
        dto.setEindDatum(eindDatum);
        dto.setNaam(naam);
        dto.setAantalAanwezigen(aanwezigen == null ? 0 : aanwezigen.size());
        dto.setMaxAantal(maxAantal);
        return dto;
    }

    public String getNaam() {
        return naam;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public ObservableValue<String> getAantalAanwezigen() {
        return new SimpleObjectProperty<>(Integer.toString(aanwezigen.size()));
    }

    public int getMaxAantal() {
        return maxAantal;
    }

    public List<Gebruiker> getAanwezigen() {
        return aanwezigen;
    }

    public void setAanwezigen(List<Gebruiker> aanwezigen) {
        this.aanwezigen = aanwezigen;
    }

    
}
