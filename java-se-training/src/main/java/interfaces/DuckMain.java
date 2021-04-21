package interfaces;

import java.util.List;

public class DuckMain {

    public static void main(String[] args) {
        Duck duck = new Duck(List.of(new CanFlyDuckFeature(), new SayDuckFeature()));
        duck.doAllFeatures();

        Duck rubberDuck = new Duck(List.of(new NoFlyFeature(), new ChipFeature()));
    }
}
