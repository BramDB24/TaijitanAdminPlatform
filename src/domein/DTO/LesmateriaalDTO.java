/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

import domein.Oefeningen;

/**
 *
 * @author Jonah
 */
public class LesmateriaalDTO {


    private String naam;
    private String type;
    private byte[] image;
    private byte[] file;
    private String url;


    public String getNaam() {
        return naam;
    }

    public String getType() {
        return type;
    }

    public byte[] getImage() {
        return image;
    }

    public byte[] getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    

}
