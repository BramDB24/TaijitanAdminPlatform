package domein;

import domein.DTO.GebruikerDTO;
import javafx.collections.ObservableList;

public class GebruikerController<E> extends DomeinController<E> {

    private Gebruiker huidigeGebruiker;

    public GebruikerController() {
        super();
    }

    @Override
    public void newItem(E object) {
        //getTaijitan().createUser((GebruikerDTO) object);
        huidigeGebruiker = new Lid();
        getTaijitan().addUser(huidigeGebruiker);
    }

    @Override
    public void removeItem() {
        getTaijitan().removeUser(huidigeGebruiker);
    }

    @Override
    public void toonItem(E gebruiker) {
        huidigeGebruiker = (Gebruiker) gebruiker;
        notifyObservers();
    }

    @Override
    public ObservableList<E> toonOverzicht() {
        return (ObservableList<E>) (Object) getTaijitan().getGebruikers();
    }

    @Override
    public void notifyObservers() {
        getObservers().forEach(o -> o.update(huidigeGebruiker.getGebruikerDTO()));
    }

    @Override
    public void editItem(E dto) {
        //if (huidigeGebruiker != null) {
            getTaijitan().updateUser((GebruikerDTO) dto, huidigeGebruiker);
        //}
        //else{
          //  huidigeGebruiker = new Lid((GebruikerDTO) dto);
            //getTaijitan().addUser(huidigeGebruiker);
        //}
    }

    @Override
    public void changeFilter(String fieldname, String filterValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
