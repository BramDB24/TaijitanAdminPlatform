package domein;

public interface Subject {

    /**
     *
     * @param observer
     */
    void addObserver(Observer observer);

    /**
     *
     * @param observer
     */
    void removeObserver(Observer observer);

}
