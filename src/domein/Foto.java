/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author bramd
 */
@Entity
public class Foto extends Lesmateriaal implements Serializable{
   
    private byte[] image;

    protected Foto() {

    }

    public byte[] getFoto() {
        return this.image;
    }

}
