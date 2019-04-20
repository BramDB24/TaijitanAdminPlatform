package domein;

import java.io.Serializable;

public class Lid extends Gebruiker implements Serializable {

    protected Lid() {
        super();
    }

    Lid(String naam, String voornaam, String wachtwoord, String geboortedatum, String straat, int postcode, String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer, String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        super(naam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer, stad, nationaliteit, emailOuders, gsm, geslacht);
    }

}
