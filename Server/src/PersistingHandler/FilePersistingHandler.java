package PersistingHandler;

import java.io.*;

/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 05.09.2019
 */
public class FilePersistingHandler implements PersistingHandlerInterface {

    private final String directory = "persisting/";

    /**
     * Persist an element in a file identified by his name
     * @param object Serializable
     * @param name String
     */
    public void persist(Serializable object, String name) {
        try {
            FileOutputStream file = new FileOutputStream(this.directory + name);
            ObjectOutputStream objectOutput = new ObjectOutputStream(file);
            objectOutput.writeObject(object);
            objectOutput.close();
        } catch (Exception e) {
        	System.err.println(e.getMessage());
            System.out.println("Impossible to save " + name + " in a file");
        }
    }

    /**
     * Return a Serializable object previously persisted
     * @param name String
     * @return Object
     */
    public Object get(String name) {
        try {
            FileInputStream file = new FileInputStream(this.directory + name);
            ObjectInputStream objectIn = new ObjectInputStream(file);

            Object object = objectIn.readObject();

            objectIn.close();
            return object;
        } catch (Exception e) {
            System.out.println("Impossible to read " + name + " from a file : " + e.getMessage());
        }

        return null;
    }
}
