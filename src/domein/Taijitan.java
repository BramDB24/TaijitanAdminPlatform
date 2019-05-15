package domein;

import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.LidSessieDTO;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class Taijitan {

    private ObservableList<Oefening> oefeningen;
    private ObservableList<Gebruiker> gebruikers;
    private ObservableList<Activiteit> activiteiten;
    private ObservableList<Sessie> sessies;
    private final GenericDaoJpa<Gebruiker> userDao;
    private final GenericDaoJpa<Oefening> lesmateriaalDaoJpa;
    private final GenericDaoJpa<Sessie> sessieDaoJpa;
    private final GenericDaoJpa<Activiteit> activiteitDaoJpa;

    public Taijitan(/*GenericDao<E> dao*/) {
        this.lesmateriaalDaoJpa = new GenericDaoJpa<>(Oefening.class);
        this.sessieDaoJpa = new GenericDaoJpa<>(Sessie.class);
        this.userDao = new GenericDaoJpa<>(Gebruiker.class);
        this.activiteitDaoJpa = new GenericDaoJpa<>(Activiteit.class);
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
    
    public void createActiviteit(ActiviteitDTO dto){
        Activiteit a = new Activiteit(dto);
        activiteiten.add(a);
        activiteitDaoJpa.save(a);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Init">
    private void initUsers() {
        gebruikers = FXCollections.observableArrayList(this.userDao.getAll());
    }

    private void initOefeningen() {
        oefeningen = FXCollections.observableArrayList(this.lesmateriaalDaoJpa.getAll());
    }

    private void initSessies() {
        sessies = FXCollections.observableArrayList(this.sessieDaoJpa.getAll());
    }

    private void initActiviteiten() {
        activiteiten = FXCollections.observableArrayList(this.activiteitDaoJpa.getAll());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters">
    public ObservableList<OefeningInterface> getOefening() {
        return FXCollections.unmodifiableObservableList(FXCollections.unmodifiableObservableList((ObservableList<OefeningInterface>) (Object) oefeningen));
    }

    public ObservableList<Gebruiker> getGebruikers() {
        if (gebruikers == null) {
            initUsers();
        }
        //return FXCollections.observableArrayList(gebruikers.stream().map(e -> e.getGebruikerKenmerkenDTO()).collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(gebruikers);
        //return gebruikers;
    }

    public ObservableList<ActiviteitInterface> getActiviteiten() {
        if (sessies == null) {
            initSessies();
        }
        return FXCollections.unmodifiableObservableList((ObservableList<ActiviteitInterface>) (Object) activiteiten);
    }

    public ObservableList<LidSessieDTO> getAanwezigheden(LocalDateTime date) {
        if (sessies == null) {
            initSessies();
        }
        Sessie sessie = sessies.stream().filter(s -> s.getSessieDatum().getYear() == date.getYear()).findFirst().get();
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(sessie.getLedenlijst().stream().map(s -> s.getLidSessieDTO()).collect(Collectors.toList())));
    }

    public ObservableList<GebruikerpuntenDTO> getClubkampioenschapOverzicht() {
        if (gebruikers == null) {
            initUsers();
        }
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(gebruikers.stream().map(g -> g.getGebruikerPuntenDTO()).collect(Collectors.toList())));
    }

    public ObservableList<ActiviteitDTO> getActiviteitenOverzicht() {
        if (activiteiten == null) {
            initActiviteiten();
        }
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(activiteiten.stream().map(a -> a.getActiviteitDTO()).collect(Collectors.toList())));
    }

    //</editor-fold>
}
