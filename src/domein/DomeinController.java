package domein;

import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public abstract class DomeinController<E> implements Subject {

    private final Taijitan taijitan;
    private final Set<Observer> observerlist;

    public DomeinController() {
        taijitan = new Taijitan(new GenericDaoJpa<>(Object.class));
        observerlist = new HashSet<>();
    }

    //<editor-fold desc="methodes">
    public abstract void newItem(E object);   

    public abstract void removeItem();
    
    public abstract void editItem(E dto);    //STRATEGY VOOR WR ITEMS KUNNEN WORDEN AANGEPAST EN WAAR NIET? //of in elke controller zelfde?

    public abstract void toonItem(E object);
    
    public abstract ObservableList<E> toonOverzicht();
    
    @Override
    public void addObserver(Observer observer) {
        observerlist.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerlist.remove(observer);
    }

    public abstract void notifyObservers();
        
    protected Set<Observer> getObservers(){
        return observerlist;
    }
    
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="getters">
    public Taijitan getTaijitan() {
        return taijitan;
    }
    //</editor-fold>
}
