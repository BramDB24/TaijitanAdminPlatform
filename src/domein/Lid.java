package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lid extends Gebruiker {

    @Id
    private String gebruikersNaam;

    public Lid() {
        // TODO - implement Lid.Lid
        throw new UnsupportedOperationException();
    }

    public String getId() {
        return gebruikersNaam;
    }

    public String setId(String gebruikersNaam) {
        return this.gebruikersNaam = gebruikersNaam;
    }
}
