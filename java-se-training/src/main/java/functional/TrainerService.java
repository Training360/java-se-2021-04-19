package functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class TrainerService {

    public static void main(String[] args) {


        // array - lehetőleg kerülendő
        // diamond
        // interfész
        //List<Trainer> trainers = new LinkedList<>();
        var trainers = new ArrayList<Trainer>(100);
        // String nameS Hungarian notation NEM HASZNÁLJUK!
        var name = "John Doe";

        var i = 5; // int
        var f = 3.5; // double
        // nem ismert érték: null -> Optional

        trainers.add(new Trainer("John Doe"));
        trainers.add(new Trainer("Jack Doe"));
        trainers.add(new Trainer("Jane Doe"));

        // Wrapper, csomagoló
        // List<int> Nem használhatunk primitív típus

        // Object

        // primitívek?
//         Collections.sort(trainers, new SortByName());

        // Anonim inner class
//        Collections.sort(trainers, new Comparator<Trainer>() {
//            @Override
//            public int compare(Trainer o1, Trainer o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });

        // Java 8, syntactic sugar

//        Collections.sort(trainers,
//                (o1, o2) -> TrainerService.compareByName(o1, o2));

        // Effective Java: mindig method reference-t használjunk
        // Ne használjuk a nyilakat

                Collections.sort(trainers,
                        Trainer::byName);

//        Comparator<Trainer> comparator = (first, second) -> first.getName().compareTo(second.getName());
//        Collections.sort(trainers, comparator);

//        Collections.sort(trainers,
//                (Trainer o1, Trainer o2) -> {
//            String name1 =  o1.getName();
//            String name2 = o2.getName();
//            return name1.compareTo(name2);
//        });

        System.out.println(trainers);

        // Currency
        // 11 Ft Java verzióváltás
        // 11 Ft (non-braking space)

        var trainerService = new TrainerService();

        // Arrays.asList()
        // Java 9-től kezdve
        var newTrainers = List.of(new Trainer("John Doe"),
                new Trainer("Jane Doe"),
                new Trainer("Jack Doe"),
                new Trainer("Jack Smith")
        );

//        Trainer trainer = trainerService.findTrainerByName(newTrainers, "Jack Doe");
//        System.out.println(trainer);

//                Trainer trainer = trainerService.findTrainerBy(newTrainers,
//                        new SearchCriteria() {
//                            @Override
//                            public boolean test(Trainer trainer) {
//                                return trainer.getName().equals("Jack Doe");
//                            }
//                        });

        Trainer trainer = trainerService.findTrainerBy(newTrainers,
                TrainerService::byName);
        System.out.println(trainer);

//        Trainer trainerFoundByPart = trainerService.findTrainerByNamePart(newTrainers, "jack");
//        System.out.println(trainerFoundByPart);

//        Trainer trainerFoundByPart = trainerService.findTrainerBy(newTrainers,
//                new SearchCriteria() {
//                    @Override
//                    public boolean test(Trainer trainer) {
//                        return trainer.getName().toLowerCase().contains("jack");
//                    }
//                });

        Trainer trainerFoundByPart = trainerService.findTrainerBy(newTrainers,
                TrainerService::byNamePart);
        System.out.println(trainerFoundByPart);
    }




//    public Trainer findById(long id) {
// SOHA!
 //        if (not exists) {
//            return null;
//        }

        // 1. exception
            // nem használunk checked exception
            // csak unchecked exception: RuntimeException leszármazottja
        // JPA
        // Spring Boot
        // 2. Optional
        // 3. Dummy object, null object EmptyTrainer extends Trainer
//    }

//    public void update() {
//        var dataSource = new MySqlDataSource();
//        var connection = dataSource.getConnection();
//        var preparedStatement = c.prepareStatement();
//    }

    public Trainer findTrainerBy(List<Trainer> trainers, Predicate<Trainer> searchCriteria) {
        for (var trainer: trainers) {
            if (searchCriteria.test(trainer)) {
                return trainer;
            }
        }
        // NEM ÍRUNK ILYENT return null;
        throw new IllegalArgumentException("Trainer not found with name: " + searchCriteria);
    }

//    public Trainer findTrainerByName(List<Trainer> trainers, String name) {
//        for (var trainer: trainers) {
//            if (trainer.getName().equals(name)) {
//                return trainer;
//            }
//        }
//        // NEM ÍRUNK ILYENT return null;
//        throw new IllegalArgumentException("Trainer not found with name: " + name);
//    }
//
//    public Trainer findTrainerByNamePart(List<Trainer> trainers, String namePart) {
//        for (var trainer: trainers) {
//            if (trainer.getName().toLowerCase().contains(namePart.toLowerCase())) {
//                return trainer;
//            }
//        }
//        // NEM ÍRUNK ILYENT return null;
//        throw new IllegalArgumentException("Trainer not found with name: " + namePart);
//    }

    static boolean byName(Trainer trainer1) {
        return trainer1.getName().equals("Jack Doe");
    }

    static boolean byNamePart(Trainer trainer12) {
        return trainer12.getName().toLowerCase().contains("jack");
    }
}
