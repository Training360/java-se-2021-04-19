package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamWarningDemo {

    public static void main(String[] args) {
        // SOHA NE CSINÁLJ ILYET! SOHA!

        // Effectively final
        final List<String> names = new ArrayList<>();

        final int k = 0;

//        names = new ArrayList<>();

        Stream.iterate(1, i -> i + 1)
//                .parallel()
                .limit(10)
                //.forEach(i -> {names.add("John " + i);});
                .collect(Collectors.toList());

        System.out.println(names);
    }
}
