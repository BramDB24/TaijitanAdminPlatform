package domein;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GebruikerDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomeinController {

    private List<Gebruiker> gebruikers;
    private GenericDao genericDao;

    public DomeinController(boolean withInit) {
        if (withInit) {
            new DatabasePopulation().seedDb();
        }
        setGenericDao(new GenericDaoJpa<>(Gebruiker.class));
    }

    public void addGebruiker(String familienaam, String voornaam, String wachtwoord, Date geboortedatum, String straat, int postcode,
            String land, String rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht) {
        this.gebruikers.add(new Lid(familienaam, voornaam, wachtwoord, geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer,
                stad, nationaliteit, emailOuders, gsm, geslacht));
    }

    public void aanpassenGebruiker(String gebruikersnaam, String familienaam, String voornaam, String wachtwoord, Date geboortedatum, String straat, int postcode,
            String land, String rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
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

    public void close() {
        GebruikerDaoJpa.closePersistency();
    }

    //setters, getters
    private void setGenericDao(GenericDao<Gebruiker> gd) {
        this.genericDao = gd;
    }

    public List<Gebruiker> getGebruikers() {
        initializeGebruikersListWhenEmpty();
        return gebruikers;
    }

    public ObservableList<String> getLeden() {
        initializeGebruikersListWhenEmpty();
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(
                gebruikers.stream().filter(g -> g instanceof Lid).map(l -> l.getGebruikersNaam()).collect(Collectors.toList())));
    }

    public String getLidInfo(String gebruikersnaam) throws NullPointerException {
        return gebruikers.stream().filter(g -> g.getGebruikersNaam().equals(gebruikersnaam)).findFirst().get().getDataAsString();
    }

    public List<String> getGebruikerNamen() {
        initializeGebruikersListWhenEmpty();
        return gebruikers.stream().map(Gebruiker::getGebruikersNaam).collect(Collectors.toList());
    }

    public void initializeGebruikersListWhenEmpty() {
        if (gebruikers == null) {
            gebruikers = genericDao.getAll();
        }
    }

}
