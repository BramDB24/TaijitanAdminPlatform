package domein;

import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerKenmerkenDTO;
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
        huidigeGebruiker = getTaijitan().getUser(((GebruikerKenmerkenDTO) gebruiker).getGebruikersnaam());
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
}
