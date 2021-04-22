package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    // teszt eset = teszt metódus
    // nem írunk módosítószókat
    // nem tartjuk be az elnevezési konvenctiót
    @Test
    void adds_two_positive_number() {
        // BDD: given - when - then

        // Given (adott)
        var calculator = new Calculator();

        // When (ha) - amit tesztelni akarunk - meghívunk metódust
        int result = calculator.add(7, 8);

        // Then (akkor) - automatikus ellenőrzés:
        // összehasonlítjuk a kapott és az elvárt eredményt
        // assert
        assertEquals(15, result);
    }

    @Test
    void  add_numbers_short() {
        assertEquals(15, new Calculator().add(7, 8));
    }

    @Test
    void  add_negative() {
        assertEquals(1, new Calculator().add(-7, 8));
    }

    @Test
    void  add_zero() {
        assertEquals(8, new Calculator().add(0, 8));
    }
}
