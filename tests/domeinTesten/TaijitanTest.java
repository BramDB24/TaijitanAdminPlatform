/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTesten;

import domein.Activiteit;
import domein.DTO.GebruikerDTO;
import domein.Gebruiker;
import domein.Lid;
import domein.Oefening;
import domein.Sessie;
import domein.Taijitan;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import repository.GenericDaoJpa;

/**
 *
 * @author Jonah
 */
public class TaijitanTest {

    private GenericDaoJpa<Gebruiker> gebruikerDao;
    private GenericDaoJpa<Oefening> oefeningDao;
    private GenericDaoJpa<Sessie> sessieDao;
    private GenericDaoJpa<Activiteit> activiteitDao;
    private Taijitan t;

    public TaijitanTest() {
        gebruikerDao = Mockito.mock(GenericDaoJpa.class);
        oefeningDao = Mockito.mock(GenericDaoJpa.class);
        sessieDao = Mockito.mock(GenericDaoJpa.class);
        activiteitDao = Mockito.mock(GenericDaoJpa.class);
        t = new Taijitan(gebruikerDao, oefeningDao, sessieDao, activiteitDao);
    }

    public void trainMockobjects() {
        Mockito.when(gebruikerDao.getAll()).thenReturn(seeding());
    }

    public List<Gebruiker> seeding() {
        Gebruiker user = new Lid();
        Gebruiker user1 = new Lid();
        List<Gebruiker> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        return list;
    }

    @Test
    public void getAllUsers_returnsList() {
        trainMockobjects();
        Assert.assertEquals(2, t.getGebruikers().size());
    }

    @Test
    public void adduser_upsListAmountByOne_removeUser_subtractsOne() {
        ObservableList<Gebruiker> l = FXCollections.observableArrayList();
        t.setusers(l);
        Assert.assertEquals(0, l.size());
        Gebruiker local = new Lid();
        t.addUser(local);
        Assert.assertEquals(1, t.getusers().size());
        t.removeUser(local);
        Assert.assertEquals(0, t.getusers().size());
    }

    @Test
    public void updateUser_UserAlreadyExists_DoesntChangeSize() {
        GebruikerDTO dto = new GebruikerDTO();
        dto.setNaam("testName");
        Gebruiker local = new Lid(dto);
        ObservableList<Gebruiker> l = FXCollections.observableArrayList();
        l.add(local);
        t.setusers(l);
        t.updateUser(dto, local);
        Assert.assertEquals(1, t.getusers().size());
    }

    @Test
    public void updateUser_UserDoesNotExist_ChangesListSize() {
        GebruikerDTO dto = new GebruikerDTO();
        dto.setNaam("testName");
        Gebruiker local = new Lid(dto);
        ObservableList<Gebruiker> l = FXCollections.observableArrayList();
        t.setusers(l);
        t.updateUser(dto, local);
        Assert.assertEquals(1, t.getusers().size());
    }
    
    @Test
    public void updateUser_CheckIfDataChanged() {
        GebruikerDTO dto = new GebruikerDTO();
        dto.setNaam("prechange");
        dto.setVoornaam("nonmutal");
        Gebruiker local = new Lid(dto);
        Assert.assertEquals("prechange", local.getNaam());
        ObservableList<Gebruiker> l = FXCollections.observableArrayList();
        t.setusers(l);
        dto.setNaam("postchange");
        t.updateUser(dto, local);
        Assert.assertEquals("postchange", t.getusers().get(0).getNaam());
        Assert.assertEquals("nonmutal", t.getusers().get(0).getVoornaam());
    }
    
    
}
