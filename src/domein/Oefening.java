package domein;

import domein.DTO.OefeningDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Oefening implements OefeningInterface, Serializable{
    
    @Id
    private int oefeningId;
    private String naam;
    private String graad;
    private int oefeningType;
    
    private List<LesmateriaalInterface> lesmateriaal;

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
        return lesmateriaal;
    }
}