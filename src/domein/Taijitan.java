package domein;

import domein.DTO.GebruikerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.UserDaoJpa;

public class Taijitan<E> {

    private Lesmateriaal lesmaterialen;
    private final ObservableList<GebuikerInterface> gebruikers;
    private Activiteit activiteiten;
    private final UserDaoJpa dao;

    public Taijitan(GenericDao<E> dao) {
        this.dao = (UserDaoJpa) dao;
        gebruikers = FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(this.dao.getAll()));
    }

    public void createUser(GebruikerDTO dto) {
        Gebruiker user = new Lid(dto);
        gebruikers.add(user);
        dao.save(user);
    }

    public void removeUser(Gebruiker huidigeGebruiker) {
        gebruikers.remove(huidigeGebruiker);
        dao.delete(huidigeGebruiker);
    }

    public void updateUser(GebruikerDTO dto) {
        Gebruiker user = (Gebruiker) gebruikers.stream().filter(g -> g.getGebruikersnaam().equals(dto.getGebruikersnaam())).findFirst().get();
        gebruikers.set(gebruikers.indexOf(user), user);
        dao.update(user);
    }

    //<editor-fold defaultstate="collapsed" desc="getters">
    public Lesmateriaal getLesmaterialen() {
        return lesmaterialen;
    }

    public ObservableList<Gebruiker> getGebruikers() {
        return (ObservableList<Gebruiker>) (Object) gebruikers;
    }

    public Activiteit getActiviteiten() {
        return activiteiten;
    }
    //</editor-fold>

}
