package domein;

import java.util.ArrayList;
import java.util.List;

public class DomeinController {

    private final List<Gebruiker> gebruikers;

    public DomeinController() {
        this.gebruikers = new ArrayList<>();
    }

    public List<Gebruiker> getGebruikers() {
        return this.gebruikers;
    }

    public void addGebruiker(String familienaam, String voornaam, String geboortedatum, String straat, int postcode,
            String land, int rijksregisternummer, String email, int telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, int gsm, char geslacht) {
        this.gebruikers.add(new Lid(familienaam, voornaam, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer,
                stad, nationaliteit, emailOuders, gsm, geslacht));
    }

}
