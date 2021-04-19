package functional;

import java.text.Collator;
import java.util.*;

public class ShortExamples {

    public static void main(String[] args) {
        // List.of-al létrehozott lista nem módosítható
        List<String> names = new ArrayList<>(List.of("John Doe", "Jack Doe", "Jane Doe",
                "john Doe", "jane Doe", "Ábel"
                ));

        Collections.sort(names, Collator.getInstance(new Locale("hu", "HU")));
        System.out.println(names);

        List<Trainer> trainers = new ArrayList<>(
                List.of(
                        new Trainer("Jack Doe"),
                        new Trainer("John"),
                        new Trainer("Ábel"),
                        new Trainer("Jane Smith")));
        //Collections.sort(trainers, Comparator.comparingInt(o -> o.getName().length()));
//        Collections.sort(trainers, Comparator
//                .comparing(Trainer::getName, Comparator.nullsFirst(Comparator.naturalOrder()))
//                .reversed()
//        );

//        Collections.sort(trainers, Comparator.comparing(Trainer::getName,
//                Collator.getInstance(new Locale("hu", "HU"))));

        trainers.sort(Comparator.comparing(Trainer::getName));

        System.out.println(trainers);
    }
}
