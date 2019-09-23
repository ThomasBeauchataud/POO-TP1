import ActionHandler.ReadingHandler;
import ActionHandler.ReadingHandlerInterface;
import ActionHandler.WritingHandler;
import ActionHandler.WritingHandlerInterface;
import PersistingHandler.FilePersistingHandler;
import PersistingHandler.PersistingHandlerInterface;

import java.util.ArrayList;

/**
 * @since 23.09.2019
 * @author Thomas Beauchataud & Francois Montigny
 *
 * This test in testing all WritingHandler
 * It assumes that PersistingHandler and ReadingHandler are already tested
 */
public class WritingHandlerTest {

    private final PersistingHandlerInterface persistingHandler = new FilePersistingHandler();
    private final ReadingHandlerInterface readingHandler = new ReadingHandler(this.persistingHandler);
    private final String objectName = "test_object";

    private ArrayList<WritingHandlerInterface> getWritingHandlerList() {
        ArrayList<WritingHandlerInterface> writingHandlerList = new ArrayList<>();
        writingHandlerList.add(
                new WritingHandler(this.persistingHandler)
        );
        return writingHandlerList;
    }

    private String run() {
        ArrayList<WritingHandlerInterface> writingHandlerList= this.getWritingHandlerList();
        this.persistingHandler.persist(new TestObject(), this.objectName);
        StringBuilder testResult = new StringBuilder();
        for(WritingHandlerInterface writingHandler : writingHandlerList) {
            try {
                if (this.test(writingHandler)) {
                    testResult.append(writingHandler.toString()).append(" passed the test \n");
                } else {
                    testResult.append(writingHandler.toString()).append(" failed the test \n");
                }
            } catch (Exception e) {
                testResult.append(writingHandler.toString()).append("encountered an error : ").append(e.getMessage()).append("\n");
            }
        }
        return testResult.toString();
    }

    private Boolean test (WritingHandlerInterface writingHandler) {
        writingHandler.treat(this.objectName, "value", "test");
        return this.readingHandler.treat(this.objectName, "value").equals("test");
    }

    public static void main(String[] args) {
        WritingHandlerTest WritingHandlerTest = new WritingHandlerTest();
        System.out.println(WritingHandlerTest.run());
    }
    
}
