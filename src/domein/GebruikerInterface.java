package domein;

import domein.DTO.GebruikerDTO;
import java.time.LocalDate;

public interface GebruikerInterface {

    public String getGebruikersnaam();

    public String getVoornaam();

    public String getNaam();

    public String getWachtwoord();

    public String getTelefoonnummer();

    public LocalDate getGeboortedatum();

    public String getEmail();

    public int getGraad();

    public LocalDate getInschrijvingsdatum();

    public String getStraat();

    public String getHuisnummer();

    public String getPostcode();

    public String getStad();

    public String getLand();

    public String getRijksregisternummer();

    public String getGsm();

    public String getEmailouders();

    public String getGeboorteplek();

    public String getNationaliteit();

    public String getGeslacht();


    public String getFormulenaam();

    public int getScore();
    
    public GebruikerDTO getGebruikerDTO();

}
