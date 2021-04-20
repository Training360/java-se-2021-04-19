package cloud;

import lombok.AllArgsConstructor;

import java.text.Collator;
import java.util.*;

@AllArgsConstructor
public class Clouds {

    private List<CloudStorage> storages;

    public Optional<CloudStorage> alphabeticallyFirst() {
        if (storages.isEmpty()) {
            Optional.empty();
        }

//        List<CloudStorage> ordered = new ArrayList<>(storages);
//        ordered.sort(Comparator.comparing(CloudStorage::getProvider,
//                String::compareToIgnoreCase));
//        ordered.sort(Collator.getInstance(new Locale("hu", "HU")));

//        return first(ordered);

        return Optional.of(Collections.min(storages,
                Comparator.comparing(CloudStorage::getProvider,
                    String::compareToIgnoreCase)));
    }

    public Optional<CloudStorage> bestPriceForShortestPeriod() {
        if (storages.isEmpty()) {
            Optional.empty();
        }

        return Optional.of(Collections.min(storages,
                Comparator.comparing(CloudStorage::getPayPeriod)
                    .thenComparing(CloudStorage::getPrice)));
    }

    public List<CloudStorage> worstOffers() {
        List<CloudStorage> sorted = new ArrayList<>(storages);
        sorted.sort(Comparator.reverseOrder());

        // shallow copy
        //sorted.get(0).setProvider("rewrtw");
        // deep copy

        return sorted.subList(0, 3);
    }

    private Optional<CloudStorage> first(List<CloudStorage> storages) {
        if (storages.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(storages.get(0));
        }
    }

    public static void main(String[] args) {
        var storages =
                List.of(
                        new CloudStorage("Amazon", 500, 30, PayPeriod.MONTHLY),
                        new CloudStorage("Amazon", 500, 300, PayPeriod.ANNUAL),
                        new CloudStorage("Google", 1000, 57, PayPeriod.MONTHLY),
                        new CloudStorage("Amazon", 2000, 102, PayPeriod.MONTHLY)
                );

        var clouds = new Clouds(storages);

        System.out.println(clouds.alphabeticallyFirst());
        System.out.println(clouds.bestPriceForShortestPeriod());
        System.out.println(clouds.worstOffers());
    }
}
