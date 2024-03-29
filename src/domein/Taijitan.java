package domein;

import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerDTO;
import domein.DTO.GebruikerpuntenDTO;
import domein.DTO.InschrijvingenDTO;
import domein.DTO.LidSessieDTO;
import domein.DTO.RaadplegingenDTO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import repository.GenericDaoJpa;

public class Taijitan {

    //oefeningen
    private ObservableList<Oefening> oefeningen;

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
        gebruikers.add(gebruiker);
        userDao.save(gebruiker);
    }

    public void removeUser(Gebruiker huidigeGebruiker) {
        gebruikers.remove(huidigeGebruiker);
        userDao.delete(huidigeGebruiker);
    }

    public void updateUser(GebruikerDTO dto, Gebruiker user) {
        user.setAttributes(dto);
        if (!gebruikers.contains(user)) {
            addUser(user);
        } else {
            userDao.update(user);
        }
    }

    public Gebruiker getUser(Gebruiker gebruiker) {
        return gebruikers.stream().filter(g -> g.equals(gebruiker)).findFirst().get();
    }

    public void createActiviteit(ActiviteitDTO dto) {
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
    }

    private void initSessies() {
        sessies = FXCollections.observableArrayList(this.sessieDaoJpa.getAll());
    }

    private void initActiviteiten() {
        activiteiten = FXCollections.observableArrayList(this.activiteitDaoJpa.getAll());
        activiteitenFiltered = new FilteredList<>(activiteiten, a -> true);
        activiteitenSorted = new SortedList<>(activiteitenFiltered);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters">
    public ObservableList<Gebruiker> getusers() {
        return gebruikers;
    }

    public void setusers(ObservableList<Gebruiker> l) {
        gebruikers = l;
    }

    public ObservableList<OefeningInterface> getOefening() {
        return FXCollections.unmodifiableObservableList(FXCollections.unmodifiableObservableList((ObservableList<OefeningInterface>) (Object) oefeningen));
    }

    public ObservableList<Gebruiker> getGebruikers() {
        if (gebruikers == null) {
            initUsers();
        }
        return gebruikersSorted;
    }

    public ObservableList<ActiviteitInterface> getActiviteiten() {
        if (sessies == null) {
            initSessies();
        }
        return FXCollections.unmodifiableObservableList((ObservableList<ActiviteitInterface>) (Object) activiteiten);
    }

    public ObservableList<LidSessieDTO> getAanwezigheden(/*LocalDateTime date*/) {
        if (sessies == null) {
            initSessies();
        }
        List<LidSessieDTO> aanwezighedenlijst = new ArrayList<>();
        sessies.stream().forEach((Sessie s) -> {
            var aanwezigen = s.getLedenlijst().stream().filter(l -> l.getAanwezigheid()).collect(Collectors.toList());
            aanwezigen.forEach(a -> aanwezighedenlijst.add(a.getLidSessieDTO()));
        });
        //Sessie sessie = sessies.stream().filter(s -> s.getSessieDatum().getYear() == date.getYear()).findFirst().get();
        //return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(sessie.getLedenlijst().stream().map(s -> s.getLidSessieDTO()).collect(Collectors.toList())));
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(aanwezighedenlijst));
    }

    public ObservableList<GebruikerpuntenDTO> getClubkampioenschapOverzicht() {
        if (gebruikers == null) {
            initUsers();
        }
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(gebruikers.stream().map(g -> g.getGebruikerPuntenDTO()).collect(Collectors.toList())));
    }

    public ObservableList<Activiteit> getActiviteitenOverzicht() {
        if (activiteiten == null) {
            initActiviteiten();
        }
        return activiteitenSorted;
    }

    public ObservableList<InschrijvingenDTO> getInschrijvingen() {
        if (gebruikers == null) {
            initUsers();
        }
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(gebruikers.stream().map(g -> g.getInschrijvingenDTO()).collect(Collectors.toList())));
    }

    public ObservableList<RaadplegingenDTO> getRaadplegingenLesmateriaalOverzicht() {
        if (gebruikers == null) {
            initUsers();
        }
        List<RaadplegingenDTO> raadplegingenoverzicht = new ArrayList<>();
        List<List<GebruikerOefening>> raadplegingen = gebruikers.stream().map(g -> g.getRaadplegingen()).collect(Collectors.toList());
        raadplegingen.forEach(r -> {
            r.forEach(raadpleging -> {
                raadplegingenoverzicht.add(raadpleging.getRaadplegingenDTO());
            });
        });
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(raadplegingenoverzicht));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="filters">
    public void filterGebruikers(String fieldname, String filterValue) {
        gebruikersFiltered.setPredicate(getFilterPredicate(fieldname, filterValue));
    }

    public void filterActiviteiten(String fieldname, String filterValue) {
        activiteitenFiltered.setPredicate(getFilterPredicate(fieldname, filterValue));
    }

    private Predicate getFilterPredicate(String fieldname, String filterValue) {
        return val -> {
            boolean filterLeeg = filterValue == null || filterValue.isEmpty();
            if (filterLeeg) {
                return true;
            }
            try {
                Field field;
                switch (val.getClass().getSimpleName()) {
                    case "Lid" : case "Lesgever":
                        field = val.getClass().getSuperclass().getDeclaredField(fieldname);
                        break;
                    default:
                        field = val.getClass().getDeclaredField(fieldname);
                }

                field.setAccessible(true);
                var fieldvalue = field.get(val);
                fieldvalue = fieldvalue.toString();
                boolean stringfilter = filterLeeg ? false : ((String) ((String) fieldvalue).toLowerCase()).startsWith(filterValue.toLowerCase());
                return stringfilter;

            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                return false;
            }
        };
    }
    //</editor-fold>

    public void removeActiviteit(Activiteit huidigeActiviteit) {
        activiteiten.remove(huidigeActiviteit);
        activiteitDaoJpa.delete(huidigeActiviteit);
    }

    public void updateActiviteit(ActiviteitDTO activiteitDTO, Activiteit activiteit) {
        activiteit.setAttributes(activiteitDTO);
        if (!activiteiten.contains(activiteit)) {
            addActiviteit(activiteit);
        } else {
            activiteitDaoJpa.update(activiteit);
        }
    }
    
    private void addActiviteit(Activiteit activiteit){
        activiteiten.add(activiteit);
        activiteitDaoJpa.save(activiteit);
    }
}
