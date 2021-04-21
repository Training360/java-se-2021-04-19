package interfaces;

import java.util.*;

public class StaticMethodsExample {

    public static void main(String[] args) {
//        List<String> names = new ArrayList<>(Arrays.asList("John Doe", "Jack Doe"));
//
//        Collections.sort(names);

        List<String> names = new ArrayList<>(List.of("John Doe", "Jane Doe"));

        names.sort(Comparator.naturalOrder());
    }
}
