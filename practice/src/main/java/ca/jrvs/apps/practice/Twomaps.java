package ca.jrvs.apps.practice;

import java.util.Map;

public class Twomaps<K, V> {
    /**
     * Compare two maps and return true if they are equal.
     * @param map1 first map
     * @param map2 second map
     * @return true if maps are equal
     * @throws Exception if maps sizes are not equal
     */
    public boolean compareMaps(Map<K, V> map1, Map<K, V> map2) throws Exception {
        if (map1.size() != map2.size()) {
            throw new Exception("Maps are not the same size");
        }
        for (K key : map1.keySet()) {
            if (!map2.containsKey(key) || !map2.get(key).equals(map1.get(key))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Twomaps<String, Integer> tm = new Twomaps<>();
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
        Map<String, Integer> map2 = Map.of("a", 1, "b", 2, "c", 3);
        try {
            System.out.println(tm.compareMaps(map1, map2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

