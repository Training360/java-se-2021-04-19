package locations;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void filterByName() {
        var locations = List.of(
                new Location("home", 1, 1),
                new Location("Work", 2, 1),
                new Location("Home", 3, 1),
                new Location("Sport", 4, 1));

        var result = new LocationsService().filterByName(locations, "Home");

        assertEquals(2, result.size());

//        assertEquals(List.of("home", "Home"),
//                result.stream().map(Location::getName).collect(Collectors.toList()));

        assertThat(result)
                .extracting(Location::getName)
                .containsExactly("home", "Home");
    }

}