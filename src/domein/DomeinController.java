package domein;

import java.util.HashSet;
import java.util.Set;
import repository.UserDaoJpa;

public abstract class DomeinController<E> implements Subject {

    private final Taijitan taijitan;
    private final Set<Observer> observerlist;

    public DomeinController() {
        taijitan = new Taijitan(new UserDaoJpa());
        observerlist = new HashSet<>();
    }

    //<editor-fold desc="methodes">
    public abstract void newItem(E object);

    public void removeItem(E object) {
        throw new UnsupportedOperationException();
    }

    public abstract void removeItem();

    @Override
    public void addObserver(Observer observer) {
        observerlist.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerlist.remove(observer);
    }

    public void notifyObservers() {
        observerlist.forEach(e -> e.update());
    }

    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="getters">
    public Taijitan getTaijitan() {
        return taijitan;
    }
    //</editor-fold>
}
