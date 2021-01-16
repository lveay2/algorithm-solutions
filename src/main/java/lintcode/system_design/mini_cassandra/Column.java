package lintcode.system_design.mini_cassandra;

public class Column {

    public int key;
    public String value;

    public Column(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", \"" + value + "\")";
    }

}
