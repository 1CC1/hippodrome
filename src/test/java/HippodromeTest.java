import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {

    @Test
    public void nullHorsesParamException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }

    @Test
    public void nullHorsesParamMessageException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );

        String expected = "Horses cannot be null.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void emptyListHorsesParamException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );
    }

    @Test
    public void emptyListHorsesParamMessageException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );

        String expected = "Horses cannot be empty.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void sameListHorsesParam() {
        ArrayList<Horse> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("Name " + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list, hippodrome.getHorses());
    }
}
