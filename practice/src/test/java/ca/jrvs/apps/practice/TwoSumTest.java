package ca.jrvs.apps.practice;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

  @org.junit.jupiter.api.Test
  void twoSum() {
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    int[] result = TwoSum.twoSum(nums, target);
    assertEquals(0, result[0]);
    assertEquals(1, result[1]);
  }
}