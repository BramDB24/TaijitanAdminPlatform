/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author bramd
 */
@Entity
public class Tekst extends Lesmateriaal implements Serializable{

    @Column(name = "[file]")
    private byte[] file;

    protected Tekst() {

    }

    public byte[] getTekst() {
        return this.file;
    }
}
