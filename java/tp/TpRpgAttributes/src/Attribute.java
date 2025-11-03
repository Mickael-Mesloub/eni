public class Attribute {
    private String name;
    private String value;

    public Attribute(String _name, String _value) {
        name = _name;
        value = _value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
