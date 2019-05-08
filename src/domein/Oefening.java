package domein;

import domein.DTO.OefeningDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Oefeningen")
public class Oefening implements OefeningInterface, Serializable {

    @Id
    private int oefeningId;
    private String naam;
    private String graad;
    private int oefeningType;

    @OneToMany
    private List<Lesmateriaal> lesmateriaal;

    protected Oefening() {

    }

    public Oefening(OefeningDTO dto) {

    }

    @Override
    public int getOefeningId() {
        return oefeningId;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public String getGraad() {
        return graad;
    }

    @Override
    public int getOefeningType() {
        return oefeningType;
    }

    @Override
    public List<LesmateriaalInterface> getLesmateriaal() {
        return (List<LesmateriaalInterface>) (Object)lesmateriaal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.oefeningId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oefening other = (Oefening) obj;
        return this.oefeningId == other.oefeningId;
    }


    
    
}
