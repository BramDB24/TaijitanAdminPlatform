package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jonah
 */
@Entity
@DiscriminatorColumn(name = "Rol")
public abstract class Gebruiker implements Serializable {

    private LocalDateTime inschrijvingsdatum;
    private String familienaam;
    private String voornaam;
    private String wachtwoord;
    @Id
    private final String gebruikersNaam = voornaam + familienaam;
    private char geslacht;
    private String straatnaam;
    private int huisnummer;
    private int postcode;
    private String stad;
    private String land;
    private String nationaliteit;
    private int rijksregisternummer;
    private String vastTelefoonnummer;
    private String gsmNummer;
    private String emailAdres;
    private String emailAdresOuders;
    private String geborenTe;
    private Date geboorteDatum;

    //constructors
    public Gebruiker() {

    }

    public Gebruiker(String familienaam, String voornaam, String wachtwoord,
            String geboortedatum, String straat, int postcode, String land,
            int rijksregisternummer, String email, String telefoon,
            String geboorteplaats, int huisnummer, String stad,
            String nationaliteit, String emailOuders, String gsm,
            char geslacht) {
        setFamilienaam(familienaam);
        setVoornaam(voornaam);
        setWachtwoord(wachtwoord);
        setGeboorteDatum(geboorteDatum);
        setStraatnaam(straat);
        setPostcode(postcode);
        setLand(land);
        setRijksregisternummer(rijksregisternummer);
        setEmailAdres(email);
        setVastTelefoonnummer(telefoon);
        setGeborenTe(geboorteplaats);
        setHuisnummer(huisnummer);
        setStad(stad);
        setNationaliteit(nationaliteit);
        setEmailAdresOuders(emailOuders);
        setGsmNummer(gsm);
        setGeslacht(geslacht);
    }

    //setters //validatie etc toevoegen
    public final void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public final void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public final void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }

    public final void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public final void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public final void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public final void setStad(String stad) {
        this.stad = stad;
    }

    public final void setLand(String land) {
        this.land = land;
    }

    public final void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public final void setRijksregisternummer(int rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public final void setVastTelefoonnummer(String vastTelefoonnummer) {
        this.vastTelefoonnummer = vastTelefoonnummer;
    }

    public final void setGsmNummer(String gsmNummer) {
        this.gsmNummer = gsmNummer;
    }

    public final void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public final void setEmailAdresOuders(String emailAdresOuders) {
        this.emailAdresOuders = emailAdresOuders;
    }

    public final void setGeborenTe(String geborenTe) {
        this.geborenTe = geborenTe;
    }

    public final void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public final void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

}
