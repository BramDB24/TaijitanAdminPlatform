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
@Entity(name = "Video")
@Table(name = "Video")
public class Video extends Lesmateriaal implements Serializable{

    private String url;

    protected Video() {

    }

    public String getUrl() {
        return this.url;
    }

}
