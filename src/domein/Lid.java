package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lid extends Gebruiker {

    @Id
    private String gebruikersNaam;

    public Lid() {
        super();
    }

    Lid(String familienaam, String voornaam, String geboortedatum, String straat, int postcode, String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer, String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        super(familienaam, voornaam, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer, stad, nationaliteit, emailOuders, gsm, geslacht);
    }

    public String getId() {
        return gebruikersNaam;
    }

    public String setId(String gebruikersNaam) {
        return this.gebruikersNaam = gebruikersNaam;
    }
}
