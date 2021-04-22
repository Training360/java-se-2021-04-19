package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

    @Test
    void parse() {
        assertEquals(new Location("Budapest", 47.497912, 19.040235),
                new LocationParser().parse("Budapest,47.497912,19.040235"));
    }

    @Test
    void parseNotThreeParts() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new LocationParser().parse("Budapest,47.497912;19.040235"));
        assertEquals("Must contains three parts!", e.getMessage());
    }

    @Test
    void parseLatNotNumber() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new LocationParser().parse("Budapest,47.a497912,19.040235"));
        assertTrue(e.getMessage().startsWith("Lat not number!"));
    }

    @Test
    void parseLonNotNumber() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new LocationParser().parse("Budapest,47.497912,19.a040235"));
        assertTrue(e.getMessage().startsWith("Lon not number!"));
    }
}