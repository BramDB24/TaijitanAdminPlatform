/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jonah
 */
@Entity
@Table(name = "Lesmateriaal")
public class Lesmateriaal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Lesmateriaalid")
    private int id;

    private String naam;

    private int oefeningId;

    private String type;

    private byte[] image;
    @Column(name = "File")
    private byte[] file;

    private String url;

    protected Lesmateriaal() {
    }

    public Lesmateriaal(int id, String naam, int oefeningId, String type, byte[] image, byte[] file, String url) {
        this.id = id;
        this.naam = naam;
        this.oefeningId = oefeningId;
        this.type = type;
        setFile(file);
        setImage(image);
        setUrl(url);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public final void setImage(byte[] image) {
        if(image == null){
            this.image = null;
        }
        this.image = image;
    }

    public final void setFile(byte[] file) {
        if(file==null){
            this.file=null;
        }
        this.file = file;
    }

    public final void setUrl(String url) {
        if(url == null){
            this.url = null;
        }
        this.url = url;
    }

    
    
    public String getNaam() {
        return naam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lesmateriaal)) {
            return false;
        }
        Lesmateriaal other = (Lesmateriaal) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domein.Lesmateriaal[ id=" + id + " ]";
    }

}
