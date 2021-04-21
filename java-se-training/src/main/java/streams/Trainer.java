package streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    private Long id;

    private String name;

    private String city;

    public static Trainer parse(String line) {
        Trainer trainer = new Trainer();
        String[] parts = line.split(",");
        trainer.id = Long.parseLong(parts[0]);
        trainer.name = parts[1] + " " + parts[2];
        trainer.city = parts[3];
        return trainer;
    }
}
