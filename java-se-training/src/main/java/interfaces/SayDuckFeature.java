package interfaces;

public class SayDuckFeature implements DuckFeature {

    @Override
    public void doSomething() {
        System.out.println("Hap!");
    }
}
