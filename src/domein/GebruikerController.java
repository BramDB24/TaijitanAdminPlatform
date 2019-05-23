package domein;

import domein.DTO.GebruikerDTO;
import javafx.collections.ObservableList;

public class GebruikerController<E> extends DomeinController<E> {

    private Gebruiker huidigeGebruiker;

    public void removeItem() {
        getTaijitan().removeUser(huidigeGebruiker);
    }

    public void toonItem(E gebruiker) {
        huidigeGebruiker = (Gebruiker) gebruiker;
        notifyObservers();
    }

    @Override
    public ObservableList<E> toonOverzicht() {
        return (ObservableList<E>) (Object) getTaijitan().getGebruikers();
    }

    public void notifyObservers() {
        getObservers().forEach(o -> o.update(huidigeGebruiker.getGebruikerDTO()));
    }

    public void editItem(E dto) {
        getTaijitan().updateUser((GebruikerDTO) dto, huidigeGebruiker);
    }

    @Override
    public void changeFilter(String fieldname, String filterValue) {
        getTaijitan().filterGebruikers(fieldname, filterValue);
    }
}
