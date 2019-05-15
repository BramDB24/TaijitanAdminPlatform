
import domein.DTO.GebruikerDTO;
import domein.Gebruiker;
import domein.Lid;
import java.util.ArrayList;
import java.util.Collection;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonah
 */
public class DatabaseMock {
    
    private GebruikerDTO dto;
    private Gebruiker g;

    public DatabaseMock() {
        dto = new GebruikerDTO();
    }
    
    
    private void init(){
        g = new Lid();
        dto.setGebruikersnaam("unit.test");
        dto.setNaam("unitnaam");
        g.setAttributes(dto);
    }
    
    public Collection<Gebruiker> getusers(){
        init();
        Collection<Gebruiker> gl = new ArrayList();
        gl.add(g);
        return gl;
    }
    
    public Gebruiker getuser(){
        init();
        return g;
    }
}
