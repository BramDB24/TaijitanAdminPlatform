package domein;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Gebruiker {

    private LocalDateTime inschrijvingsdatum;
    private String familienaam;
    private String voornaam;
    private final String gebruikersNaam = voornaam + familienaam;
    private char geslacht;
    private String straatnaam;
    private int huisnummer;
    private int postcode;
    private String stad;
    private String land;
    private String nationaliteit;
    private int rijksregisternummer;
    private int vastTelefoonnummer;
    private int gsmNummer;
    private String emailAdres;
    private String emailAdresOuders;
    private String geborenTe;
    private Date geboorteDatum;

    //constructors
    public Gebruiker(){
        
    }
    public Gebruiker(String familienaam, String voornaam, String geboortedatum, String straat, int postcode, String land, 
            int rijksregisternummer, String email, int telefoon, String geboorteplaats, int huisnummer, String stad, 
            String nationaliteit, String emailOuders, int gsm, char geslacht) {
        setFamilienaam(familienaam);
        setVoornaam(voornaam);
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
    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public void setRijksregisternummer(int rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public void setVastTelefoonnummer(int vastTelefoonnummer) {
        this.vastTelefoonnummer = vastTelefoonnummer;
    }

    public void setGsmNummer(int gsmNummer) {
        this.gsmNummer = gsmNummer;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public void setEmailAdresOuders(String emailAdresOuders) {
        this.emailAdresOuders = emailAdresOuders;
    }

    public void setGeborenTe(String geborenTe) {
        this.geborenTe = geborenTe;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }
    
    
    
    
}
