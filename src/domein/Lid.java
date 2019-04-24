package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Lid")
@Table(name = "lid")
public class Lid extends Gebruiker implements Serializable {

    protected Lid() {
        super();
    }

    Lid(String naam, String voornaam, String wachtwoord, Date geboortedatum, String straat, int postcode, String land, String rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer, String stad, String nationaliteit, String emailOuders, String gsm, char geslacht, int graad, LocalDateTime inschrijvingsdatum) {
        super(naam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer, stad, nationaliteit, emailOuders, gsm, geslacht, graad, inschrijvingsdatum);
    }

}
