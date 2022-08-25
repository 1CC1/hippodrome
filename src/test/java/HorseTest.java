import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
}
