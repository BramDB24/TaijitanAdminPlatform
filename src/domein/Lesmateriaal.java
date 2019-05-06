package domein;

import domein.DTO.LesmateriaalDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lesmateriaal implements LesmateriaalInterface, Serializable {

    @Id
    private int lesmateriaalid;
    private String naam;
    private String type;
    private byte[] image;
    private byte[] file;
    private String url;

    public Lesmateriaal(LesmateriaalDTO dto) {       
        naam = dto.getNaam();
        type = dto.getType();
        image = dto.getImage();
        file = dto.getFile();
        url = dto.getUrl();
    }

    protected Lesmateriaal() {

    }


    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public byte[] getImage() {
        return image;
    }

    @Override
    public byte[] getFile() {
        return file;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
