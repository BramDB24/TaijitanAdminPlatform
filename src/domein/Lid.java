package domein;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lid extends Gebruiker implements Serializable {

    @Id
    private String gebruikersNaam;

    public Lid() {
        super();
    }

    Lid(String familienaam, String voornaam, String wachtwoord, String geboortedatum, String straat, int postcode, String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer, String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        super(familienaam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer, stad, nationaliteit, emailOuders, gsm, geslacht);
    }

    public String getId() {
        return gebruikersNaam;
    }

    public String setId(String gebruikersNaam) {
        return this.gebruikersNaam = gebruikersNaam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.gebruikersNaam);
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
        final Lid other = (Lid) obj;
        return Objects.equals(this.gebruikersNaam, other.gebruikersNaam);
    }
    
    
}
