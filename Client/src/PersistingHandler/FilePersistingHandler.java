package PersistingHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FilePersistingHandler implements PersistingHandlerInterface {

    @Override
    public void persist(String content, String reference) {
        try {
            File file = new File(reference);

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
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
