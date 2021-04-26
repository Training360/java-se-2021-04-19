package locations;

import java.util.List;
import java.util.stream.Collectors;

public class LocationsService {

    public List<Location> filterByName(List<Location> locations, String name) {
        return locations.stream()
                .filter(location -> location.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
