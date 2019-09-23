package PersistingHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FilePersistingHandler implements PersistingHandlerInterface {

    private final String directory = "persisting\\";

    @Override
    public void persist(String content, String reference) {
        try {
            BufferedWriter bufferedWriter;
        	bufferedWriter = new BufferedWriter(new FileWriter(this.directory + reference));
        	bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String get(String reference) {
        return null;
    }
}
