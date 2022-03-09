package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TwomapsTest {

    @Test
    void compareMaps() throws Exception {
        Twomaps<String, Integer> tm = new Twomaps<>();

        Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> map2 = Map.of("a", 1, "b", 2, "c", 3);

        assertTrue(tm.compareMaps(map1, map2));

    }
}