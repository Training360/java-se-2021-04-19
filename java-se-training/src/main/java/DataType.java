public enum DataType {

    MEASUREMENT_CP("cp"), MEASUREMENT_FLOW("flow");

    private String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
