package PersistingHandler;

public interface PersistingHandlerInterface {

    void persist(String content, String reference);

    String get(String reference);

}
