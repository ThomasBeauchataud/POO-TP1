import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 04.09.2019
 */
class Command implements Serializable {

    private ArrayList<String> value;
    private String separator;

    /**
     * Constructor Command
     */
    Command() {
        this.value = new ArrayList<>();
        this.separator = "#";
    }

    /**
     * @return ArrayList<String>
     */
    ArrayList<String> getValue() {
        return this.value;
    }

    /**
     * @param value String
     */
    void addValue(String value) {
        this.value.add(value);
    }

    /**
     * @return String
     */
    String getSeparator() {
        return this.separator;
    }
}
