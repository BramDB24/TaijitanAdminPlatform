package domein;

import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.GebruikerKenmerkenDTO;
import domein.DTO.InschrijvingenDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
    private String graad;
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

    @OneToMany(mappedBy = "gebruiker")
    private List<GebruikerOefening> raadplegingen;

    private enum Graad {
        Kyu6(1),
        Kyu5(2),
        Kyu4(3),
        Kyu3(4),
        Kyu2(5),
        Kyu1(6),
        Dan1(7),
        Dan2(8),
        Dan3(9),
        Dan4(10),
        Dan5(11),
        Dan6(12),
        Dan7(13),
        Dan8(14);

        private final int value;

        private Graad(int value) {
            this.value = value;
        }

        public int getvalue() {
            return this.value;
        }

    }

    // </editor-fold>
    protected Gebruiker() {

    }

    public Gebruiker(GebruikerDTO dto) { //validatie (setters) hier? of in dto
        setAttributes(dto);
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
    public String getGraad() {
        for (Graad g : Graad.values()) {
            if (graad != null && Integer.valueOf(graad) == g.getvalue()) {
                return g.name();
            }
        }
        return null;
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
    public GebruikerDTO getGebruikerDTO() {
        return createGebruikerDTO();
    }

    public GebruikerKenmerkenDTO getGebruikerKenmerkenDTO() {
        GebruikerKenmerkenDTO dto = new GebruikerKenmerkenDTO();
        dto.setVoornaam(voornaam);
        dto.setNaam(naam);
        dto.setGebruikersnaam(gebruikersnaam);
        dto.setGraad(graad);
        return dto;
    }

    public GebruikerpuntenDTO getGebruikerPuntenDTO() {
        return createGebruikerPuntenDTO();
    }

    private GebruikerDTO createGebruikerDTO() {
        GebruikerDTO dto = new GebruikerDTO();
        dto.setGebruikersnaam(gebruikersnaam);
        dto.setWachtwoord(wachtwoord);
        dto.setNaam(naam);
        dto.setVoornaam(voornaam);
        dto.setTelefoonnummer(telefoonnummer);
        dto.setGeboortedatum(geboortedatum);
        dto.setEmail(email);
        dto.setGraad(getGraad());
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

    //<editor-fold defaultstate="collapsed" desc="setters">
    public final void setAttributes(GebruikerDTO dto) {
        setGebruikersnaam(dto.getGebruikersnaam());
        setNaam(dto.getNaam());
        setWachtwoord(dto.getWachtwoord());
        setVoornaam(dto.getVoornaam());
        setTelefoonnummer(dto.getTelefoonnummer());
        setGeboortedatum(dto.getGeboortedatum());
        setEmail(dto.getEmail());
        setGraad(dto.getGraad());
        setInschrijvingsdatum(dto.getInschrijvingsdatum());
        setStraatnaam(dto.getStraat());
        setHuisnummer(dto.getHuisnummer());
        setPostcode(dto.getPostcode());
        setStad(dto.getStad());
        setLand(dto.getLand());
        setRijksregisternummer(dto.getRijksregisternummer());
        setGsm(dto.getGsm());
        setEmailouders(dto.getEmailouders());
        setGeboorteplek(dto.getGeboorteplek());
        setNationaliteit(dto.getNationaliteit());
        setGeslacht(dto.getGeslacht());
        setFormulenaam(dto.getFormulenaam());
        setScore(dto.getScore());
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam != null && !gebruikersnaam.isEmpty()) {
            this.gebruikersnaam = gebruikersnaam;
        } else {
            throw new IllegalArgumentException("Er is geen gebruikersnaam ingevuld");
        }
    }

    public void setWachtwoord(String wachtwoord) {
        if (wachtwoord != null && !wachtwoord.isEmpty()) {
            this.wachtwoord = wachtwoord;
        } else {
            throw new IllegalArgumentException("Er is geen wachtwoord ingevuld");
        }
    }

    public void setNaam(String naam) {
        if (naam != null && !naam.isEmpty()) {
            this.naam = naam;
        } else {
            throw new IllegalArgumentException("Er is geen naam ingevuld");
        }
    }

    public void setVoornaam(String voornaam) {
        if (voornaam != null && !voornaam.isEmpty()) {
            this.voornaam = voornaam;
        } else {
            throw new IllegalArgumentException("Er is geen voornaam ingevuld");
        }
    }

    public void setTelefoonnummer(String telefoonnummer) {
        Pattern pattern = Pattern.compile("[0,9|5]{2}[0-9]{7,8}");
        Matcher m = pattern.matcher(telefoonnummer);
        if (telefoonnummer != null && m.matches()) {
            this.telefoonnummer = telefoonnummer;
        } else {
            throw new IllegalArgumentException("Er is een ongeldig formaat ingegeven voor de telefoonnummer");
        }
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        if (geboortedatum != null && !LocalDate.now().isBefore(geboortedatum)) {
            this.geboortedatum = geboortedatum;
        } else {
            throw new IllegalArgumentException("Geboortedatum kan niet in de toekomst liggen");
        }
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher m = pattern.matcher(email);
        if (email != null && m.matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Er is een ongeldig formaat ingegeven voor het emailadres");
        }
    }

    public void setGraad(String graad) {
        if (graad != null && !graad.isEmpty()) {
            for (Graad g : Graad.values()) {
                if (g.name().equals(graad)) {
                    this.graad = String.format("%s", g.getvalue());
                }
            }
        }else {
            throw new IllegalArgumentException("Er is een ongeldige graad ingegeven");
        }
    }

    public void setInschrijvingsdatum(LocalDate inschrijvingsdatum) {
        if (inschrijvingsdatum != null && !LocalDate.now().isBefore(inschrijvingsdatum)) {
            this.inschrijvingsdatum = inschrijvingsdatum;
        } else {
            throw new IllegalArgumentException("Inschrijvingsdatum kan niet in de toekomst liggen");
        }
    }

    public void setStraatnaam(String straatnaam) {
        if (straatnaam != null && !straatnaam.isEmpty()) {
            this.straatnaam = straatnaam;
        }else {
            throw new IllegalArgumentException("Er is geen straatnaam ingevuld");
        }
    }

    public void setHuisnummer(String huisnummer) {
        if (huisnummer != null && !huisnummer.isEmpty()) {
            this.huisnummer = huisnummer;
        }
    }

    public void setPostcode(String postcode) {
        String pattern = "[a-zA-Z]{1,}";
        if (postcode != null && !postcode.matches(pattern)) {
            this.postcode = postcode;
        } else {
            throw new IllegalArgumentException("Er is een ongeldig formaat ingegeven voor de postcode");

        }
    }

    public void setStad(String stad) {
        if (stad != null && !stad.isEmpty()) {
            this.stad = stad;
        }else {
            throw new IllegalArgumentException("Er is geen stad ingevuld");
        }
    }

    public void setLand(String land) {
        if (land != null && !land.isEmpty()) {
            this.land = land;
        }else {
            throw new IllegalArgumentException("Er is geen land ingevuld");
        }
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        if (rijksregisternummer != null && !rijksregisternummer.isEmpty()) {
            this.rijksregisternummer = rijksregisternummer;
        }else {
            throw new IllegalArgumentException("Er is geen rijksregisternummer ingevuld");
        }
    }

    public void setGsm(String gsm) {
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        if (gsm.matches(pattern)) {
            this.gsm = gsm;
        } else {
            throw new IllegalArgumentException("Er is een ongeldig formaat ingegeven voor het gsmnummer");
        }
    }

    public void setEmailouders(String emailouders) {
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (emailouders != null && emailouders.matches(pattern)) {
            this.emailouders = emailouders;
        } else {
            throw new IllegalArgumentException("Er is een ongeldig formaat ingegeven voor het emailadres van de ouders");
        }
    }

    public void setGeboorteplek(String geboorteplek) {
        if (geboorteplek != null && !geboorteplek.isEmpty()) {
            this.geboorteplek = geboorteplek;
        }else {
            throw new IllegalArgumentException("Er is geen geboorteplek ingevuld");
        }
    }

    public void setNationaliteit(String nationaliteit) {
        if (nationaliteit != null && !nationaliteit.isEmpty()) {
            this.nationaliteit = nationaliteit;
        }else {
            throw new IllegalArgumentException("Er is geen nationaliteit ingevuld");
        }
    }

    public void setGeslacht(String geslacht) {
        if (geslacht != null && !geslacht.isEmpty()) {
            this.geslacht = geslacht;
        }else {
            throw new IllegalArgumentException("Er is geen geslacht gekozen");
        }
    }

    public void setFormulenaam(String formulenaam) {
        //if (formulenaam != null && !formulenaam.isEmpty()) {
            this.formulenaam = formulenaam;
        //}else {
        //    throw new IllegalArgumentException("Er is geen formule meegegeven");
        //}
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRaadplegingen(List<GebruikerOefening> raadplegingen) {
        this.raadplegingen = raadplegingen;
    }

    //</editor-fold>
    public InschrijvingenDTO getInschrijvingenDTO() {
        InschrijvingenDTO dto = new InschrijvingenDTO();
        dto.setVoornaam(voornaam);
        dto.setNaam(naam);
        dto.setGebruikersnaam(gebruikersnaam);
        dto.setFormule(formulenaam);
        return dto;
    }

    public List<GebruikerOefening> getRaadplegingen() {
        return raadplegingen;
    }

    @Override
    public String toString() {
        return gebruikersnaam;
    }

}
