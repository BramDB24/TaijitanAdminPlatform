package domein;

import domein.DTO.GebruikerDTO;

public class GebruikerController<E> extends DomeinController<E> {

    private Gebruiker huidigeGebruiker;

    public GebruikerController() {
        super();
    }
    
    @Override
    public void newItem(E object){
       getTaijitan().createUser((GebruikerDTO) object);
    }
    
    @Override
    public void removeItem(){
        getTaijitan().removeUser(huidigeGebruiker);
    }
    
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        super.removeObserver(observer);
    }

    
}
