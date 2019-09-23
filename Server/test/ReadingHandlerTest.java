import ActionHandler.ReadingHandler;
import ActionHandler.ReadingHandlerInterface;
import PersistingHandler.FilePersistingHandler;
import PersistingHandler.PersistingHandlerInterface;

import java.util.ArrayList;

/**
 * @since 23.09.2019
 * @author Thomas Beauchataud & Francois Montigny
 *
 * This test in testing all ReadingHandler
 * It assumes that PersistingHandler is already tested
 */
public class ReadingHandlerTest {

    private final PersistingHandlerInterface persistingHandler = new FilePersistingHandler();
    private final String objectName = "test_object";

    private ArrayList<ReadingHandlerInterface> getReadingHandlerList() {
        ArrayList<ReadingHandlerInterface> readingHandlerList = new ArrayList<>();
        readingHandlerList.add(
                new ReadingHandler(this.persistingHandler)
        );
        return readingHandlerList;
    }

    private String run() {
        ArrayList<ReadingHandlerInterface> readingHandlerList= this.getReadingHandlerList();
        TestObject testObject = new TestObject();
        testObject.setValue("test");
        this.persistingHandler.persist(testObject, this.objectName);
        StringBuilder testResult = new StringBuilder();
        for(ReadingHandlerInterface readingHandler : readingHandlerList) {
            try {
                if (this.test(readingHandler)) {
                    testResult.append(readingHandler.toString()).append(" passed the test \n");
                } else {
                    testResult.append(readingHandler.toString()).append(" failed the test \n");
                }
            } catch (Exception e) {
                testResult.append(readingHandler.toString()).append("encountered an error : ").append(e.getMessage()).append("\n");
            }
        }
        return testResult.toString();
    }

    private Boolean test (ReadingHandlerInterface readingHandler) {
        return readingHandler.treat(this.objectName, "value").equals("test");
    }

    public static void main(String[] args) {
        ReadingHandlerTest ReadingHandlerTest = new ReadingHandlerTest();
        System.out.println(ReadingHandlerTest.run());
    }

}
