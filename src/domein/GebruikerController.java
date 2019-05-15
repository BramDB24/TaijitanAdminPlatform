package domein;

import domein.DTO.GebruikerDTO;
import javafx.collections.ObservableList;

public class GebruikerController<E> extends DomeinController<E> {

    private Gebruiker huidigeGebruiker;

    public GebruikerController() {
        super();
        getTaijitan().initUsers();
    }

    @Override
    public void newItem(E object) {
        getTaijitan().createUser((GebruikerDTO) object);
    }

    @Override
    public void removeItem() {
        getTaijitan().removeUser(huidigeGebruiker);
    }

    @Override
    public void toonItem(E gebruiker) {
        //huidigeGebruiker = getTaijitan().getUser((Gebruiker)gebruiker);
        huidigeGebruiker = (Gebruiker)gebruiker;
        notifyObservers();
    }

    
    @Override
    public ObservableList<E> toonOverzicht() {
        return (ObservableList<E>)(Object)getTaijitan().getGebruikers();
    }

    @Override
    public void notifyObservers() {
        getObservers().forEach(o -> o.update(huidigeGebruiker.getGebruikerDTO()));
    }

    @Override
    public void editItem(E dto) {
        getTaijitan().updateUser((GebruikerDTO) dto);
    }

    @Override
    public void changeFilter(String fieldname, String filterValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
