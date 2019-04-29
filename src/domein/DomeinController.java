package domein;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityNotFoundException;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomeinController {

    private List<Gebruiker> gebruikers;
    private List<Lesmateriaal> lesmateriaal;
    private GenericDao genericDaoGebruikers;
    private GenericDao genericDaoLesmateriaal;
    private GebruikerDao gebruikerDao;

    public DomeinController(boolean withInit) {
        if (withInit) {
            new DatabasePopulation().seedDb();
        }
        setGenericDao(new GenericDaoJpa<>(Gebruiker.class));
        setGenericDaoLesmateriaal(new GenericDaoJpa(Lesmateriaal.class));
        setGebruikerDao(new GebruikerDaoJpa());
    }

    public void addGebruiker(String familienaam, String voornaam, LocalDate geboortedatum, String straat, int postcode,
            String land, String rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht, int graad, LocalDate inschrijvingsdatum) {
        this.gebruikers.add(new Lid(familienaam, voornaam, "", geboortedatum, straat, postcode, land, rijksregisternummer, email, telefoon, geboorteplaats, huisnummer,
                stad, nationaliteit, emailOuders, gsm, geslacht, graad, inschrijvingsdatum));
    }

    public void aanpassenGebruiker(String gebruikersnaam, String familienaam, String voornaam, LocalDate geboortedatum, String straat, int postcode,
            String land, String rijksregisternummer, String email, String telefoon, String geboorteplaats, int huisnummer,
            String stad, String nationaliteit, String emailOuders, String gsm, char geslacht, int graad, LocalDate inschrijvingsdatum) {
        Gebruiker gebruiker = this.gebruikers.stream().filter(g -> g.getGebruikersNaam().equals(gebruikersnaam)).findFirst().get();
        gebruiker.setFamilienaam(familienaam);
        gebruiker.setVoornaam(voornaam);
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
        gebruiker.setGraad(graad);
        gebruiker.setInschrijvingsdatum(inschrijvingsdatum);
    }

    public void verwijderGebruiker(String gebruikersnaam) {
        //TODO
    }
    
    public void close() {
        GebruikerDaoJpa.closePersistency();
    }

    //setters, getters
    private void setGenericDao(GenericDao<Gebruiker> gd) {
        this.genericDaoGebruikers = gd;
    }

    public final void setGebruikerDao(GebruikerDao gebruikerDao) {
        this.gebruikerDao = gebruikerDao;
    }

    public final void setGenericDaoLesmateriaal(GenericDao genericDaoLesmateriaal) {
        this.genericDaoLesmateriaal = genericDaoLesmateriaal;
    }
    

    public ObservableList<String> getAanwezighedenGebruikers(int oneOrZero) throws EntityNotFoundException {
        try {
            return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(gebruikerDao.getAanwezigeGebruikers(oneOrZero))) ;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Er zijn geen aanwezigen gevonden");
        }
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
    
    public List<String> getLesmateriaal(){
        lesmateriaal = genericDaoLesmateriaal.getAll();
        return this.lesmateriaal.stream().map(Lesmateriaal::getNaam).collect(Collectors.toList());
    }

    public void initializeGebruikersListWhenEmpty() {
        if (gebruikers == null) {
            gebruikers = genericDaoGebruikers.getAll();
        }
    }

}
