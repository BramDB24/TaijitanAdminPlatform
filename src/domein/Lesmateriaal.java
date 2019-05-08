package domein;

import domein.DTO.LesmateriaalDTO;
import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type")
@Table(name = "Lesmateriaal")
public abstract class Lesmateriaal implements LesmateriaalInterface, Serializable {

    @Id
    private int lesmateriaalid;
    private String naam;
    private int oefeningId;
    
    public Lesmateriaal(LesmateriaalDTO dto) {
        naam = dto.getNaam();
    }

    protected Lesmateriaal() {

    }

    @Override
    public String getNaam() {
        return naam;
    }
}
