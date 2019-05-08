package domein;

import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Rol")
@Table(name = "Gebruiker")
public abstract class Gebruiker implements GebruikerInterface, Serializable {

    // <editor-fold desc="attributes">
    @Id
    private String gebruikersnaam;
    private String wachtwoord;
    private String naam;
    private String voornaam;
    private String telefoonnummer;
    private LocalDate geboortedatum;
    private String email;
    private int graad;
    private LocalDate inschrijvingsdatum;
    private String straatnaam;
    private String huisnummer;
    private String postcode;
    private String stad;
    private String land;
    private String rijksregisternummer;
    private String gsm;
    private String emailouders;
    private String geboorteplek;
    private String nationaliteit;
    private String geslacht;
    private String formulenaam;
    private int score;

    // </editor-fold>
    protected Gebruiker() {

    }

    public Gebruiker(GebruikerDTO dto) { //validatie (setters) hier? of in dto
        gebruikersnaam = dto.getGebruikersnaam();
        wachtwoord = dto.getWachtwoord();
        naam = dto.getNaam();
        voornaam = dto.getVoornaam();
        telefoonnummer = dto.getTelefoonnummer();
        geboortedatum = dto.getGeboortedatum();
        email = dto.getEmail();
        graad = dto.getGraad();
        inschrijvingsdatum = dto.getInschrijvingsdatum();
        straatnaam = dto.getStraat();
        huisnummer = dto.getHuisnummer();
        postcode = dto.getPostcode();
        stad = dto.getStad();
        land = dto.getLand();
        rijksregisternummer = dto.getRijksregisternummer();
        gsm = dto.getGsm();
        emailouders = dto.getEmailouders();
        geboorteplek = dto.getGeboorteplek();
        nationaliteit = dto.getNationaliteit();
        geslacht = dto.getGeslacht();
        formulenaam = dto.getFormulenaam();
        score = dto.getScore();

    }

    // <editor-fold desc="overrides of interface">
    @Override
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    @Override
    public String getVoornaam() {
        return voornaam;
    }

    @Override

    public String getNaam() {
        return naam;
    }

    @Override
    public String getWachtwoord() {
        return wachtwoord;
    }

    @Override
    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    @Override
    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getGraad() {
        return graad;
    }

    @Override
    public LocalDate getInschrijvingsdatum() {
        return inschrijvingsdatum;
    }

    @Override
    public String getStraatnaam() {
        return straatnaam;
    }

    @Override

    public String getHuisnummer() {
        return huisnummer;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    @Override
    public String getStad() {
        return stad;
    }

    @Override

    public String getLand() {
        return land;
    }

    @Override
    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    @Override
    public String getGsm() {
        return gsm;
    }

    @Override

    public String getEmailouders() {
        return emailouders;
    }

    @Override
    public String getGeboorteplek() {
        return geboorteplek;
    }

    @Override
    public String getNationaliteit() {
        return nationaliteit;
    }

    @Override
    public String getGeslacht() {
        return geslacht;
    }

    @Override
    public String getFormulenaam() {
        return formulenaam;
    }

    @Override
    public int getScore() {
        return score;
    }
    
    @Override
    public GebruikerDTO getGebruikerDTO(){
        return createGebruikerDTO();
    }
    
    public GebruikerpuntenDTO getGebruikerPuntenDTO(){
        return createGebruikerPuntenDTO();
    }
    private GebruikerDTO createGebruikerDTO(){
        GebruikerDTO dto = new GebruikerDTO();
        dto.setGebruikersnaam(gebruikersnaam);
        dto.setNaam(naam);
        dto.setVoornaam(voornaam);
        dto.setTelefoonnummer(telefoonnummer);
        dto.setGeboortedatum(geboortedatum);
        dto.setEmail(email);
        dto.setGraad(graad);
        dto.setInschrijvingsdatum(inschrijvingsdatum);
        dto.setStraat(straatnaam);
        dto.setHuisnummer(huisnummer);
        dto.setPostcode(postcode);
        dto.setStad(stad);
        dto.setLand(land);
        dto.setRijksregisternummer(rijksregisternummer);
        dto.setGsm(gsm);
        dto.setEmailouders(emailouders);
        dto.setGeboorteplek(geboorteplek);
        dto.setNationaliteit(nationaliteit);
        dto.setGeslacht(geslacht);
        dto.setFormulenaam(formulenaam);
        dto.setScore(score);
        return dto;
    }
    
    private GebruikerpuntenDTO createGebruikerPuntenDTO() {
        GebruikerpuntenDTO dto = new GebruikerpuntenDTO();
        dto.setGebruikersnaam(gebruikersnaam);
        dto.setNaam(naam);
        dto.setScore(score);
        dto.setVoornaam(voornaam);
        return dto;
    }
    // </editor-fold>

    //<editor-fold desc="Database hashcode/equals">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.gebruikersnaam);
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
        return Objects.equals(this.gebruikersnaam, other.gebruikersnaam);
    }
    //</editor-fold>

    @Override
    public String toString(){
        //fieldsize klopt niet helemaal in gui omdat bepaalde chars minder plek in nemen dan andere. Bv: i < w
        return String.format("%-40s | %-15s %s", gebruikersnaam, naam, voornaam) ; 
    }

    public void setAttributes(GebruikerDTO dto) {
        gebruikersnaam = dto.getGebruikersnaam();
        naam = dto.getNaam();
        voornaam = dto.getVoornaam();
        telefoonnummer = dto.getTelefoonnummer();
        geboortedatum = dto.getGeboortedatum();
        email = dto.getEmail();
        graad = dto.getGraad();
        inschrijvingsdatum = dto.getInschrijvingsdatum();
        straatnaam = dto.getStraat();
        huisnummer = dto.getHuisnummer();
        postcode = dto.getPostcode();
        stad = dto.getStad();
        land = dto.getLand();
        rijksregisternummer = dto.getRijksregisternummer();
        gsm = dto.getGsm();
        emailouders = dto.getEmailouders();
        geboorteplek = dto.getGeboorteplek();
        nationaliteit = dto.getNationaliteit();
        geslacht = dto.getGeslacht();
        formulenaam = dto.getFormulenaam();
        score = dto.getScore();
    }

    
}
