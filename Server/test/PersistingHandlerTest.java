import PersistingHandler.FilePersistingHandler;
import PersistingHandler.PersistingHandlerInterface;

import java.util.ArrayList;

/**
 * @since 23.09.2019
 * @author Thomas Beauchataud & Francois Montigny
 *
 * This test in testing all PersistingHandler
 */
class PersistingHandlerTest {

    private ArrayList<PersistingHandlerInterface> getPersistingHandlerList() {
        ArrayList<PersistingHandlerInterface> persistingHandlerList = new ArrayList<>();
        persistingHandlerList.add(
                new FilePersistingHandler()
        );
       return persistingHandlerList;
    }

    private String run() {
        ArrayList<PersistingHandlerInterface> persistingHandlerList= this.getPersistingHandlerList();
        StringBuilder testResult = new StringBuilder();
        for(PersistingHandlerInterface persistingHandler : persistingHandlerList) {
            try {
                if (this.classTest(persistingHandler)) {
                    testResult.append(persistingHandler.toString()).append(" passed the class test \n");
                } else {
                    testResult.append(persistingHandler.toString()).append(" failed the class test \n");
                }
            } catch (Exception e) {
                testResult.append(persistingHandler.toString()).append("encountered an error : ").append(e.getMessage()).append("\n");
            }
        }
        return testResult.toString();
    }

    private Boolean classTest (PersistingHandlerInterface persistingHandler) {
        TestObject testObject = new TestObject();
        persistingHandler.persist(testObject, "test_object");
        Object e = persistingHandler.get("test_object");
        return e.getClass() == testObject.getClass();
    }

    public static void main(String[] args) {
        PersistingHandlerTest persistingHandlerTest = new PersistingHandlerTest();
        System.out.println(persistingHandlerTest.run());
    }

}

