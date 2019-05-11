/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.LidSessieDTO;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;

/**
 *
 * @author Jonah
 * @param <E>
 */
public class OverzichtController<E> extends DomeinController<E> {

    public OverzichtController() {
        super();
    }

    @Override
    public void newItem(E object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editItem(E dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toonItem(E object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Object> toonActiviteitenOverzicht() {
        return (ObservableList<Object>) (Object) getTaijitan().getActiviteitenOverzicht();
        //return getTaijitan().getGebruikers();
    }

    public ObservableList<E> toonInschrijvingenOverzicht() {
        return null;
    }

    public ObservableList<Object> toonAanwezighedenOverzicht(LocalDateTime date) {
        return (ObservableList<Object>) (Object) getTaijitan().getAanwezigheden(date);
    }

    public ObservableList<Object> toonClubkampioenschapOverzicht() {
        return (ObservableList<Object>) (Object) getTaijitan().getClubkampioenschapOverzicht();
    }

    public ObservableList<OefeningInterface> toonRaadplegingenLesmateriaalOverzicht() {
        return getTaijitan().getOefening();
    }
    
    public ObservableList<Object> toonGebruikers(){
        return (ObservableList<Object>) (Object) getTaijitan().getGebruikers();
    }
    @Override
    public ObservableList<E> toonOverzicht() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
