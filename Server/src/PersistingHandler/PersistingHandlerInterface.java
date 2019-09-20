package PersistingHandler;

import java.io.Serializable;

/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 05.09.2019
 */
public interface PersistingHandlerInterface {

    /**
     * Persist an element in a file identified by his name
     * @param object Serializable
     * @param name String
     */
    void persist(Serializable object, String name);

    /**
     * Return a Serializable object previously persisted
     * @param name String
     * @return Object
     */
    Object get(String name);

}
