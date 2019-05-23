package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Lesgever")
@Table(name = "Lesgever")
public class Lesgever extends Gebruiker implements Serializable{
    public Lesgever(){
        
    }
}