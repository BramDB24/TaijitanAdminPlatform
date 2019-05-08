package domein;

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
}
