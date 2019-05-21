package domein;

import domein.DTO.RaadplegingenDTO;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GebruikerOefening")
public class GebruikerOefening implements Serializable {
    
    @Id
    private int GebruikerOefeningId;
    
    @ManyToOne
    @JoinColumn(name = "gebruikersnaam")
    private Gebruiker gebruiker;
    
    @ManyToOne
    @JoinColumn(name = "oefeningId")
    private Oefening oefening;
    
    private LocalDateTime raadpleegTijdstip;
    
    public GebruikerOefening() {
        
    }
    
    public LocalDateTime getRaadpleegTijdstip(){
        return raadpleegTijdstip;
    }
    
    public RaadplegingenDTO getRaadplegingenDTO(){
        RaadplegingenDTO dto = new RaadplegingenDTO();
        dto.setGebruikersnaam(gebruiker.getGebruikersnaam());
        dto.setNaam(gebruiker.getNaam());
        dto.setTijdstip(raadpleegTijdstip);
        dto.setVoornaam(gebruiker.getVoornaam());
        dto.setOefening(oefening.getNaam());
        return dto;
    }
}
