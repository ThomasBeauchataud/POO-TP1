package unit;

import java.io.Serializable;

public class TestObject implements Serializable {

    private String value;

    TestObject() {}

    public String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }
}
