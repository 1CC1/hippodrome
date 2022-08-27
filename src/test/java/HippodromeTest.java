import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void getHorses() {
        ArrayList<Horse> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("Name " + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list, hippodrome.getHorses());
    }

    @Test
    public void move() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("Horse 1", 1, 2);
        Horse horse2 = new Horse("Horse 2", 2, 5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2));

        assertEquals(horse2, hippodrome.getWinner());
    }
}
