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

}
