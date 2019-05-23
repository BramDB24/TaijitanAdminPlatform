package domein;

import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public abstract class DomeinController<E> implements Subject {

    private final Taijitan taijitan;
    private final Set<Observer> observerlist;

    public DomeinController() {
        taijitan = new Taijitan(new GenericDaoJpa<>(Gebruiker.class), new GenericDaoJpa<>(Oefening.class), new GenericDaoJpa<>(Sessie.class), new GenericDaoJpa<>(Activiteit.class));
        observerlist = new HashSet<>();
    }

    //<editor-fold desc="methodes">

    public abstract ObservableList<E> toonOverzicht();

    public abstract void changeFilter(String fieldname, String filterValue);

    @Override
    public void addObserver(Observer observer) {
        observerlist.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerlist.remove(observer);
    }

    protected Set<Observer> getObservers() {
        return observerlist;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters">
    public Taijitan getTaijitan() {
        return taijitan;
    }
    //</editor-fold>
}
