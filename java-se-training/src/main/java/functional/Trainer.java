package functional;

public class Trainer {

    private String name;

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                '}';
    }

    public static int byName(Trainer o1, Trainer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
