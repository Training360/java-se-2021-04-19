package functional;

import java.util.Comparator;

public class SortByName implements Comparator<Trainer> {

    @Override
    public int compare(Trainer o1, Trainer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
