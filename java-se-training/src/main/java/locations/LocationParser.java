package locations;

public class LocationParser {

    private static final int NAME_POSITION = 0;

    private static final int LAT_POSITION = 1;

    private static final int LON_POSITION = 2;

    private static final int NUMBER_OF_PARTS = 3;

    public static final String SEPARATOR = ",";

    public Location parse(String text) {
        var parts = text.split(SEPARATOR);
        if (parts.length != NUMBER_OF_PARTS) {
            throw new IllegalArgumentException("Must contains three parts!");
        }

        var name = parts[NAME_POSITION];
        var lat = parseCoordinate(parts[LAT_POSITION], "Lat not number!");
        var lon = parseCoordinate(parts[LON_POSITION], "Lon not number!");

        return new Location(name, lat, lon);
    }

    private double parseCoordinate(String text, String exceptionMessage) {
        var coord = 0.0;
        try {
            coord = Double.parseDouble(text);
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(exceptionMessage + " " + text);
        }
        return coord;
    }
}
