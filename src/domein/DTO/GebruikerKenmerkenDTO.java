package domein.DTO;


/**
 *
 * @author Jonah
 */
public class GebruikerKenmerkenDTO {
    private String naam;
    private String voornaam;
    private String gebruikersnaam;
    private int graad;

    public GebruikerKenmerkenDTO() {
    }

    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public int getGraad() {
        return graad;
    }

    public void setGraad(int graad) {
        this.graad = graad;
    }
    
    
    
}
