package domein;

import domein.DTO.ActiviteitDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Activiteit")
public class Activiteit implements ActiviteitInterface, Serializable {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public Activiteit() {

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

    public void setNaam(String naam) {
        if (naam != null && !naam.isEmpty()) {
            this.naam = naam;
        } else {
            throw new IllegalArgumentException("Er is geen naam meegegeven");
        }
    }

    public void setStartDatum(LocalDate startDatum) {
        if (startDatum != null) {
            this.startDatum = startDatum;
        } else {
            throw new IllegalArgumentException("Gelieve een startdatum in te geven");
        }
    }

    public void setEindDatum(LocalDate eindDatum) {
        if (eindDatum != null) {
            if (eindDatum.isAfter(startDatum)) {
                this.eindDatum = eindDatum;
            } else {
                throw new IllegalArgumentException("De einddatum mag niet voor de begindatum liggen");
            }
        }
    }

    public void setMaxAantal(int maxAantal) {
        if (maxAantal == 0) {
            this.maxAantal = 9999;
        } else {
            if (maxAantal > 0) {
                this.maxAantal = maxAantal;
            } else {
                throw new IllegalArgumentException("Max aantal mag niet onder nul liggen!");
            }
        }

    }

    public void setAttributes(ActiviteitDTO activiteitDTO) {
        setNaam(activiteitDTO.getNaam());
        setStartDatum(activiteitDTO.getStartDatum());
        setEindDatum(activiteitDTO.getEindDatum());
        setMaxAantal(activiteitDTO.getMaxAantal());
    }

}
