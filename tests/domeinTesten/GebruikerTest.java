/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTesten;

import domein.Gebruiker;
import domein.Lid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jonah
 */
public class GebruikerTest {

    Gebruiker g;

    @Before
    public void setup() {
        g = new Lid();
    }

    public GebruikerTest() {
    }

    @Test
    public void gebruiker_settelefoonnr() {
        g.setTelefoonnummer("0478451245");
        Assert.assertEquals("0478451245", g.getTelefoonnummer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gebruiker_settelefoonnr_throwsexception() {
        g.setTelefoonnummer("047");
    }

    @Test
    public void gebruiker_setemail() {
        g.setEmail("test@test.com");
        Assert.assertEquals("test@test.com", g.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gebruiker_setmail_throwsexception() {
        g.setEmail("dasd");
    }

    @Test
    public void gebruiker_setgeboortedatum() {
        LocalDate date = LocalDate.of(1997, Month.MARCH, 10);
        g.setGeboortedatum(date);
        Assert.assertEquals(date, g.getGeboortedatum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gebruiker_setgeboortedatum_throwserror() {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 10);
        g.setGeboortedatum(date);
    }

}
