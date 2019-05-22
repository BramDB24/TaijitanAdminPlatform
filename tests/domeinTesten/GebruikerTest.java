/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTesten;

import domein.Gebruiker;
import domein.Lid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void gebruiker_settelefoonnr(){
        g.setTelefoonnummer("0478451245");
        Assert.assertEquals("0478451245", g.getTelefoonnummer());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void gebruiker_settelefoonnr_throwsexception(){
        g.setTelefoonnummer("047");
    }
    
    @Test
    public void gebruiker_setemail(){
        g.setEmail("test@test.com");
        Assert.assertEquals("test@test.com", g.getEmail());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void gebruiker_setmail_throwsexception(){
        g.setEmail("dasd");
    }

}
