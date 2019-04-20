package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jonah
 */
@Entity
@DiscriminatorColumn(name = "Rol")
@Table(name = "Gebruiker")
public abstract class Gebruiker implements Serializable {

    private LocalDateTime inschrijvingsdatum;
    @Column(name = "Naam")
    private String familienaam;
    private String voornaam;
    private String wachtwoord;
    @Id
    private String gebruikersNaam;
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
    @Column(name = "email")
    private String emailAdres;
    @Column(name = "emailOuders")
    private String emailAdresOuders;
    private String geborenTe;
    private Date geboorteDatum;

    //constructors
    protected Gebruiker() {

    }

    public Gebruiker(String familienaam, String voornaam, String wachtwoord,
            String geboortedatum, String straat, int postcode, String land,
            int rijksregisternummer, String email, String telefoon,
            String geboorteplaats, int huisnummer, String stad,
            String nationaliteit, String emailOuders, String gsm,
            char geslacht) {
        setFamilienaam(familienaam);
        setVoornaam(voornaam);
        setGebruikersNaam(familienaam, voornaam);
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

    public final void setGebruikersNaam(String naam, String voornaam) {
        this.gebruikersNaam = naam + voornaam;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.gebruikersNaam);
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
        final Gebruiker other = (Gebruiker) obj;
        if (!Objects.equals(this.gebruikersNaam, other.gebruikersNaam)) {
            return false;
        }
        return true;
    }

    
}