package interfaces;

public class CanFlyDuckFeature implements DuckFeature {

    @Override
    public void doSomething() {
        System.out.println("Fly");
    }
}
