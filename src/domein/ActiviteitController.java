/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.DTO.ActiviteitDTO;
import javafx.collections.ObservableList;

/**
 *
 * @author bramd
 */
public class ActiviteitController<E> extends DomeinController<E> {
    private Activiteit huidigeActiviteit;
    
    @Override
    public void newItem(E object) {
        getTaijitan().createActiviteit((ActiviteitDTO) object);
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
    public void toonItem(E activiteit) {
        huidigeActiviteit = (Activiteit) activiteit;
        notifyObservers();
    }
    
    @Override
    public ObservableList<E> toonOverzicht() {
        return (ObservableList<E>) (Object) getTaijitan().getActiviteitenOverzicht();
    }
    
    @Override
    public void notifyObservers() {
        getObservers().forEach(o -> o.update(huidigeActiviteit.getActiviteitDTO()));
    }
    
    @Override
    public void changeFilter(String fieldname, String filterValue) {
        getTaijitan().filterActiviteiten(fieldname, filterValue);
    }
    
}
