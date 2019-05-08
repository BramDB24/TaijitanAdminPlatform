/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author bramd
 */
@Entity
@Table(name = "LidSessie")
public class LidSessie implements Serializable {

    @EmbeddedId
    private LidSessieKey id;

    @ManyToOne
    @MapsId("gebruikersnaam")
    @JoinColumn(name = "gebruikersnaam")
    private Lid lid;

    @ManyToOne
    @MapsId("sessieDatum")
    @JoinColumn(name = "sessieDatum")
    private Sessie sessie;

    private boolean aanwezigheid;

    public LidSessie() {

    }

}
