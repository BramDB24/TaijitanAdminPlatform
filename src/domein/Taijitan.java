package domein;

import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.LidSessieDTO;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import repository.GenericDaoJpa;

public class Taijitan {

    //oefeningen
    private ObservableList<Oefening> oefeningen;
    private FilteredList<Oefening> oefeningenFiltered;
    private SortedList<Oefening> oefeningenSorted;
    
    //gebruikers;
    private ObservableList<Gebruiker> gebruikers;
    private FilteredList<Gebruiker> gebruikersFiltered;
    private SortedList<Gebruiker> gebruikersSorted;
    
    //activiteiten;
    private ObservableList<Activiteit> activiteiten;
    private FilteredList<Activiteit> activiteitenFiltered;
    private SortedList<Activiteit> activiteitenSorted;
    
    //sessies
    private ObservableList<Sessie> sessies;
    private FilteredList<Sessie> sessiesFiltered;
    private SortedList<Sessie> sessiesSorted;
    
    private final GenericDaoJpa<Gebruiker> userDao;
    private final GenericDaoJpa<Oefening> oefeningDaoJpa;
    private final GenericDaoJpa<Sessie> sessieDaoJpa;
    private final GenericDaoJpa<Activiteit> activiteitDaoJpa;

    public Taijitan(GenericDaoJpa<Gebruiker> daoGebruiker, GenericDaoJpa<Oefening> daoOefening, GenericDaoJpa<Sessie> daoSessie, GenericDaoJpa<Activiteit> daoActiviteit) {
        this.oefeningDaoJpa = daoOefening;
        this.sessieDaoJpa = daoSessie;
        this.userDao = daoGebruiker;
        this.activiteitDaoJpa = daoActiviteit;
    }

    //<editor-fold desc="UserActions">
    public void addUser(Gebruiker gebruiker) {
        //Gebruiker user = new Lid(dto);
        gebruikers.add(gebruiker);
        userDao.save(gebruiker);
    }

    public void removeUser(Gebruiker huidigeGebruiker) {
        gebruikers.remove(huidigeGebruiker);
        userDao.delete(huidigeGebruiker);
    }

    public void updateUser(GebruikerDTO dto, Gebruiker user) {
        //Gebruiker user = (Gebruiker) gebruikers.stream().filter(g -> g.getGebruikersnaam().equals(dto.getGebruikersnaam())).findFirst().get();
        user.setAttributes(dto);
        if(!gebruikers.contains(user)){
            addUser(user);
        }
        else{
            userDao.update(user);
        }
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
    public void initUsers() {
        gebruikers = FXCollections.observableArrayList(this.userDao.getAll()/*.stream().filter(g -> g instanceof Lid).collect(Collectors.toList())*/);
        gebruikersFiltered = new FilteredList<>(gebruikers, g -> true);
        gebruikersSorted = new SortedList<>(gebruikersFiltered);
    }

    private void initOefeningen() {
        oefeningen = FXCollections.observableArrayList(this.oefeningDaoJpa.getAll());
        oefeningenFiltered = new FilteredList<>(oefeningen, o -> true);
        oefeningenSorted = new SortedList<>(oefeningenFiltered);
    }

    private void initSessies() {
        sessies = FXCollections.observableArrayList(this.sessieDaoJpa.getAll());
        sessiesFiltered = new FilteredList<>(sessies, s -> true);
        sessiesSorted = new SortedList<>(sessiesFiltered);
    }

    private void initActiviteiten() {
        activiteiten = FXCollections.observableArrayList(this.activiteitDaoJpa.getAll());
        activiteitenFiltered = new FilteredList<>(activiteiten, a -> true);
        activiteitenSorted = new SortedList<>(activiteitenFiltered);
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
