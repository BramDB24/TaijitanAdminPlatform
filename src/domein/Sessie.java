/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bramd
 */
@Entity
@Table(name = "Sessies")
public class Sessie implements SessieInterface, Serializable {

    @Id
    private LocalDateTime sessieDatum;
    @OneToMany(mappedBy = "sessie")
    private List<LidSessie> ledenlijst;

    protected Sessie() {

    }

    @Override
    public LocalDateTime getSessieDatum() {
        return sessieDatum;
    }

    @Override
    public ObservableList<LidSessie> getLedenlijst() {
        return FXCollections.observableArrayList(ledenlijst);
    }
}
