import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    public void nullNameMessageException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void blankNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void blankNameMessageException(String name) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", ex.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1.0, 1.0));
    }

    @Test
    public void negativeSpeedMessageException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1.0, -1.0));
    }

    @Test
    public void negativeDistanceMessageException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        String name = "Loshadka";
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }

    @Test
    public void getSpeed() {
        Double speed = 123.12;
        Horse horse = new Horse("Loshadka", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Double distance = 123.12;
        Horse horse = new Horse("Loshadka", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void zeroDistanceByDefault() {
        Horse horse = new Horse("Loshadka", 1.0);
        assertEquals(0D, horse.getDistance());
    }

    @Test
    public void whenMove_ThenGetRandomDoubleCalledOnce() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Loshadka", 1.0);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1, 0.5, 2.5",
            "5, 2, 0.4, 5.8"
    })
    public void move(double distance, double speed, double randomDouble, double expected) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic
                    .when(() -> Horse.getRandomDouble(anyDouble(), anyDouble()))
                    .thenReturn(randomDouble);

            Horse horse = new Horse("Loshadka", speed, distance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
