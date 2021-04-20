package cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CloneSample {

    public static void main(String[] args) {
        List<CloudStorage> storages = List.of(new CloudStorage("Amazon", 1, 1, PayPeriod.MONTHLY));

//        List<CloudStorage> clone = new ArrayList<>();
//
//        for (var storage: storages) {
//            clone.add(new CloudStorage(storage));
//        }

        // Deep copy!
        List<CloudStorage> clone = storages.stream().map(CloudStorage::new).collect(Collectors.toList());

        clone.get(0).setProvider("Azure");

        System.out.println(storages.get(0).getProvider());
    }
}
