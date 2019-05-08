package domein;

import domein.DTO.GebruikerDTO;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class Taijitan<E> {

    private ObservableList<Oefening> oefeningen;
    private ObservableList<Gebruiker> gebruikers;
    private ObservableList<Activiteit> activiteiten;
    private ObservableList<Sessie> sessies;
    private final  GenericDaoJpa<Gebruiker> userDao;
    private final GenericDaoJpa<Oefening> lesmateriaalDaoJpa;
    private final GenericDaoJpa<Sessie> sessieDaoJpa;

    public Taijitan(/*GenericDao<E> dao*/) {
        this.lesmateriaalDaoJpa = new GenericDaoJpa<>(Oefening.class);
        this.sessieDaoJpa = new GenericDaoJpa<>(Sessie.class);
        this.userDao = new GenericDaoJpa<>(Gebruiker.class);
    }

    //<editor-fold desc="UserActions">
    public void createUser(GebruikerDTO dto) {
        Gebruiker user = new Lid(dto);
        gebruikers.add(user);
        userDao.save(user);
    }

    public void removeUser(Gebruiker huidigeGebruiker) {
        gebruikers.remove(huidigeGebruiker);
        userDao.delete(huidigeGebruiker);
    }

    public void updateUser(GebruikerDTO dto) {
        Gebruiker user = (Gebruiker) gebruikers.stream().filter(g -> g.getGebruikersnaam().equals(dto.getGebruikersnaam())).findFirst().get();
        user.setAttributes(dto);
        userDao.update(user);
    }

    public Gebruiker getUser(Gebruiker gebruiker) {
        return gebruikers.stream().filter(g -> g.equals(gebruiker)).findFirst().get();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Init">
    public void initUsers() {
        gebruikers = FXCollections.observableArrayList(this.userDao.getAll());
    }
    
    public void initOefeningen(){
        oefeningen = FXCollections.observableArrayList(this.lesmateriaalDaoJpa.getAll());
    }
    
    public void initSessies(){
        sessies = FXCollections.observableArrayList(this.sessieDaoJpa.getAll());
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters">

    public ObservableList<OefeningInterface> getOefening() {
        return FXCollections.unmodifiableObservableList((ObservableList<OefeningInterface>) (Object) oefeningen);
    }

    public ObservableList<GebruikerInterface> getGebruikers() {
        return FXCollections.unmodifiableObservableList((ObservableList<GebruikerInterface>) (Object) gebruikers);
    }

    public ObservableList<ActiviteitInterface> getActiviteiten() {
        return FXCollections.unmodifiableObservableList((ObservableList<ActiviteitInterface>) (Object) activiteiten);
    }
    //</editor-fold>

}
