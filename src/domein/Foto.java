/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author bramd
 */
@Entity(name = "Foto")
@Table(name = "Foto")
public class Foto extends Lesmateriaal implements Serializable{

    private byte[] foto;

    protected Foto() {

    }

    public byte[] getFoto() {
        return this.foto;
    }

}
