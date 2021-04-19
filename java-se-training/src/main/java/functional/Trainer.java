package functional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer implements Comparable<Trainer> {

    private String name;

    public static int byName(Trainer o1, Trainer o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public int compareTo(Trainer another) {
        return this.getName().compareTo(another.getName());
    }
}
