package domein;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public void addGebruiker(String familienaam, String voornaam, String wachtwoord, Date geboortedatum, String straat, int postcode,
            String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        this.gebruikers.add(new Lid(familienaam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer,
                stad, nationaliteit, emailOuders, gsm, geslacht));
    }
    
    public void aanpassenGebruiker(String gebruikersnaam, String familienaam, String voornaam, String wachtwoord, Date geboortedatum, String straat, int postcode,
            String land, int rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        Gebruiker gebruiker = this.gebruikers.stream().filter(g -> g.getGebruikersNaam().equals(gebruikersnaam)).findFirst().get();
        gebruiker.setFamilienaam(familienaam);
        gebruiker.setVoornaam(voornaam);
        gebruiker.setWachtwoord(wachtwoord);
        gebruiker.setGeboorteDatum(geboortedatum);
        gebruiker.setStraatnaam(straat);
        gebruiker.setPostcode(postcode);
        gebruiker.setLand(land);
        gebruiker.setRijksregisternummer(rijksregisternummer);
        gebruiker.setEmailAdres(email);
        gebruiker.setVastTelefoonnummer(telefoon);
        gebruiker.setGeborenTe(geboorteplaats);
        gebruiker.setHuisnummer(huisnummer);
        gebruiker.setStad(stad);
        gebruiker.setNationaliteit(nationaliteit);
        gebruiker.setEmailAdresOuders(emailOuders);
        gebruiker.setGsmNummer(gsm);
        gebruiker.setGeslacht(geslacht);
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
