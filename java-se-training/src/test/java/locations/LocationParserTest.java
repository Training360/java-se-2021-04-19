package locations;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
class LocationParserTest {

    private LocationParser parser = new LocationParser();

//    public LocationParserTest() {
//        System.out.println("LocationParserTest");
//    }

    @BeforeAll
    static void init() {
//        System.out.println("BeforeAll");
    }

    @BeforeEach
    void instanceInit() {
//        System.out.println("BeforeEach");
    }

    @Test
    void parse() {
        assertEquals(new Location("Budapest", 47.497912, 19.040235),
                parser.parse("Budapest,47.497912,19.040235"));
    }

    @Test
    void parseNotThreeParts() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("Budapest,47.497912;19.040235"));
        assertEquals("Must contains three parts!", e.getMessage());
    }

    @Test
    void parseLatNotNumber() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("Budapest,47.a497912,19.040235"));
        //assertTrue(e.getMessage().startsWith("Lat not number!"));
        assertThat(e.getMessage()).startsWith("Lat not number");
    }

    @Test
    void parseLonNotNumber() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("Budapest,47.497912,19.a040235"));
        assertTrue(e.getMessage().startsWith("Lon not number!"));
    }
}