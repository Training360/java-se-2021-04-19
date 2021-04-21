package interfaces;

public class NoFlyFeature implements DuckFeature{

    @Override
    public void doSomething() {
        System.out.println("Can not fly");
    }
}
