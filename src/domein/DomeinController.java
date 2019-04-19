package domein;

import java.util.List;
import java.util.stream.Collectors;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;

public class DomeinController {

    private List<Gebruiker> gebruikers;
    private GebruikerDao gebruikerDao;

    public DomeinController(boolean withInit) {
        if (withInit) {
            new DatabasePopulation().seedDb();
        }
        setGebruikerDao(new GebruikerDaoJpa());

    }

    public void addGebruiker(String familienaam, String voornaam, String wachtwoord, String geboortedatum, String straat, int postcode,
            String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        this.gebruikers.add(new Lid(familienaam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer,
                stad, nationaliteit, emailOuders, gsm, geslacht));
    }
    
    public void close(){
        GebruikerDaoJpa.closePersistency();
    }
    
    //setters, getters
    private void setGebruikerDao(GebruikerDao gd) {
        this.gebruikerDao = gd;
    }

    public List<Gebruiker> getGebruikers() {
        if (gebruikers == null) {
            gebruikers = gebruikerDao.getAll();
        }
        return gebruikers;
    }

    public List<String> getGebruikerNamen() {
        return gebruikers.stream().map(Gebruiker::getGebruikersNaam).collect(Collectors.toList());
    }

}
