package interfaces;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Duck {

    private List<DuckFeature> features;

    public void doAllFeatures() {
        for (var feature: features) {
            feature.doSomething();
        }
    }
}
