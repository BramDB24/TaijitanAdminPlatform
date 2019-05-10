package domein;

import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.LidSessieDTO;
import domein.DTO.SessieDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class Taijitan{

    private ObservableList<Oefening> oefeningen;
    private ObservableList<Gebruiker> gebruikers;
    private ObservableList<Activiteit> activiteiten;
    private ObservableList<Sessie> sessies;
    private final GenericDaoJpa<Gebruiker> userDao;
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

    public void initOefeningen() {
        oefeningen = FXCollections.observableArrayList(this.lesmateriaalDaoJpa.getAll());
    }

    public void initSessies() {
        sessies = FXCollections.observableArrayList(this.sessieDaoJpa.getAll());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters">
    public ObservableList<OefeningInterface> getOefening() {
        return FXCollections.unmodifiableObservableList((ObservableList<OefeningInterface>) (Object) oefeningen);
    }

    public ObservableList<GebruikerInterface> getGebruikers() {
        if (gebruikers == null) {
            initUsers();
        }
        return FXCollections.unmodifiableObservableList((ObservableList<GebruikerInterface>) (Object) gebruikers);
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
        return FXCollections.observableArrayList(sessie.getLedenlijst().stream().map(s -> s.getLidSessieDTO()).collect(Collectors.toList())); 
    }

    public ObservableList<GebruikerpuntenDTO> getClubkampioenschapOverzicht() {
        if (gebruikers == null) {
            initUsers();
        }
        return FXCollections.observableArrayList(gebruikers.stream().map(g -> g.getGebruikerPuntenDTO()).collect(Collectors.toList()));
    }

    public ObservableList<SessieDTO> getActiviteitenOverzicht() {
        if (sessies == null) {
            initSessies();
        }
        return FXCollections.observableArrayList(sessies.stream().map(s -> s.getSessieDTO()).collect(Collectors.toList()));
    }

    //</editor-fold>
}
