/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.DTO.ActiviteitDTO;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author bramd
 * @param <E>
 */
public class ActiviteitController<E> extends DomeinController<E> {
    private Activiteit huidigeActiviteit;
        
    public void removeItem() {
        getTaijitan().removeActiviteit(huidigeActiviteit);
    }
    
    
    public void editItem(E dto) {
        getTaijitan().updateActiviteit((ActiviteitDTO) dto, huidigeActiviteit);
    }
    
    
    public void toonItem(E activiteit) {
        huidigeActiviteit = (Activiteit) activiteit;
        notifyObservers();
    }
    
    @Override
    public ObservableList<E> toonOverzicht() {
        return (ObservableList<E>) (Object) getTaijitan().getActiviteitenOverzicht();
    }
    
    public void notifyObservers() {
        getObservers().forEach(o -> o.update(huidigeActiviteit.getActiviteitDTO()));
    }
    
    @Override
    public void changeFilter(String fieldname, String filterValue) {
        getTaijitan().filterActiviteiten(fieldname, filterValue);
    }
    
    public List<Gebruiker> geefAanwezigen() {
        return huidigeActiviteit.getAanwezigen();
    }
    
    public void pasAanwezigenAan(List<Gebruiker> aanwezigen) {
        huidigeActiviteit.setAanwezigen(aanwezigen);
    }
    
}
