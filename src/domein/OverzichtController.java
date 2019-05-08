/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDateTime;
import javafx.collections.ObservableList;

/**
 *
 * @author Jonah
 * @param <E>
 */
public class OverzichtController<E> extends DomeinController<E>{

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
    public ObservableList<E> toonOverzicht() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<E> toonActiviteitenOverzicht(){
        return getTaijitan().getGebruikers();
    }
    
    public ObservableList<E> toonInschrijvingenOverzicht(){
        return null;
    }
    
    public ObservableList<Object> toonAanwezighedenOverzicht(LocalDateTime date){
        return getTaijitan().getAanwezigheden(date);
    }
    
    public ObservableList<E> toonClubkampioenschapOverzicht(){
        return getTaijitan().getClubkampioenschapOverzicht();
    }
    public ObservableList<E> toonRaadplegingenLesmateriaalOverzicht(){
        return getTaijitan().getOefening();
    }

}
