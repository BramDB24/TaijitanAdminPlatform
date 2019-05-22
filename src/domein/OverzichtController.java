/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author Jonah
 * @param <E>
 */
public class OverzichtController<E> extends DomeinController<E> {

    private ObservableList<E> overzichtslijst;
    private FilteredList<E> filteredOverzichtslijst;
    private SortedList<E> sortedOverzichtslijst;

    public OverzichtController() {
        super();
    }

    @Override
    public void newItem(E object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editItem(E dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toonItem(E object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void toonActiviteitenOverzicht() {
        veranderOverzicht((ObservableList<E>) getTaijitan().getActiviteitenOverzicht());
    }

    public void toonInschrijvingenOverzicht() {
        veranderOverzicht((ObservableList<E>) (Object) getTaijitan().getInschrijvingen());
    }

    public void toonAanwezighedenOverzicht() {
        veranderOverzicht((ObservableList<E>) (Object) getTaijitan().getAanwezigheden());
    }

    public void toonClubkampioenschapOverzicht() {
        veranderOverzicht((ObservableList<E>) getTaijitan().getClubkampioenschapOverzicht());
    }

    public void toonRaadplegingenLesmateriaalOverzicht() {
        veranderOverzicht((ObservableList<E>) getTaijitan().getRaadplegingenLesmateriaalOverzicht());
    }

    public void toonGebruikers() {
        veranderOverzicht((ObservableList<E>) getTaijitan().getGebruikers());
    }

    private void veranderOverzicht(ObservableList<E> list) {
        overzichtslijst = list;
        filteredOverzichtslijst = new FilteredList<>(overzichtslijst, p -> true);
        sortedOverzichtslijst = new SortedList<>(filteredOverzichtslijst);
    }

    @Override
    public ObservableList<E> toonOverzicht() {
        return sortedOverzichtslijst;
    }

    @Override
    public void changeFilter(String fieldname, String filterValue) {
        filteredOverzichtslijst.setPredicate(val -> {
            boolean filterLeeg = filterValue == null || filterValue.isEmpty();
            if (filterLeeg) {
                return true;
            }
            try {
                Field field = val.getClass().getDeclaredField(fieldname);
                field.setAccessible(true);
                var fieldvalue = field.get(val);
                fieldvalue = fieldvalue.toString();
                boolean stringfilter = filterLeeg ? false : ((String) ((String) fieldvalue).toLowerCase()).startsWith(filterValue.toLowerCase());
                return stringfilter;

            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                return false;
            }
        }
        );
    }
}
