package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TwomapsTest {

    @Test
    void test() throws Exception {
        Twomaps<String, Integer> tm = new Twomaps<>();
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> map2 = Map.of("a", 1, "b", 2, "c", 4);
        assertFalse(tm.compareMaps(map1, map2));
    }

    @Test
    void testException() {
        Exception exception = assertThrows(Exception.class, () -> {
            Twomaps<String, Integer> tm = new Twomaps<>();
            Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3);
            Map<String, Integer> map2 = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
            tm.compareMaps(map1, map2);
        } );
        assertEquals("Maps are not the same size", exception.getMessage());
    }

}