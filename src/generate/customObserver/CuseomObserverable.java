package generate.customObserver;

public interface CuseomObserverable {

    void registerObserver(CustomObserver o);

    void removeObserver(CuseomObserverable o);

    void notifyObserver();
}
