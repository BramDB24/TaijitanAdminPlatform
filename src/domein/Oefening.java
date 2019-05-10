package domein;

import domein.DTO.OefeningDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="oefeningId")
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

    @Override
    public String toString(){
        return String.format("%s", getNaam());
    }

    @Override
    public List<GebruikerOefening> getRaadplegingen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
