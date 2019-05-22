/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

import java.time.LocalDate;

public class GebruikerDTO {

    private String gebruikersnaam;
    private String wachtwoord;
    private String naam;
    private String voornaam;
    private String telefoonnummer;
    private LocalDate geboortedatum;
    private String email;
    private String graad;
    private LocalDate inschrijvingsdatum;
    private String straat;
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
    private String rol;
    private String formulenaam;
    private int score;

    // <editor-fold desc="Setters">
    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGraad(String graad) {
        this.graad = graad;
    }

    public void setInschrijvingsdatum(LocalDate inschrijvingsdatum) {
        this.inschrijvingsdatum = inschrijvingsdatum;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setEmailouders(String emailouders) {
        this.emailouders = emailouders;
    }

    public void setGeboorteplek(String geboorteplek) {
        this.geboorteplek = geboorteplek;
    }

    public void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setFormulenaam(String formulenaam) {
        this.formulenaam = formulenaam;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // </editor-fold>
    // <editor-fold desc="getters">
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public String getEmail() {
        return email;
    }

    public String getGraad() {
        return graad;
    }

    public LocalDate getInschrijvingsdatum() {
        return inschrijvingsdatum;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStad() {
        return stad;
    }

    public String getLand() {
        return land;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public String getGsm() {
        return gsm;
    }

    public String getEmailouders() {
        return emailouders;
    }

    public String getGeboorteplek() {
        return geboorteplek;
    }

    public String getNationaliteit() {
        return nationaliteit;
    }

    public String getGeslacht() {
        return geslacht;
    }


    public String getFormulenaam() {
        return formulenaam;
    }

    public int getScore() {
        return score;
    }
    // </editor-fold>
}
