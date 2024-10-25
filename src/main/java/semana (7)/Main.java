package Semana7;

import java.util.Arrays;

import static Utils.Test.assertEquals;
import static Utils.Test.maybe;

public class Main {
  public static void main(String[] args) {
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    assertEquals(QuickSorting.isSorted(a), true);
    QuickSorting.exchange(a, 0, 1);
    assertEquals(a[0], 2);
    assertEquals(a[1], 1);
    assertEquals(QuickSorting.isSorted(a), false);
    QuickSorting.insertionSort(a, 0, a.length - 1);
    assertEquals(QuickSorting.isSorted(a), true);
    QuickSorting.shuffle(a);
    assertEquals(QuickSorting.isSorted(a), false);
    QuickSorting.insertionSort(a, 0, a.length - 1);
    assertEquals(QuickSorting.isSorted(a), true);

    int median = QuickSorting.medianOfThree(a, 2, 6);
    assertEquals(median >= 2, true);
    assertEquals(median <= 6, true);

    median = QuickSorting.medianOfThree(a, 0, 0);
    assertEquals(median, 0);

    int[] sizes = {100, 1000, 10000, 100000, 1000000, 10000000};
    for (int size : sizes) {
      System.out.println("Array size: " + size);
      Double[] b = new Double[size];
      for (int j = 0; j < size; j++) b[j] = Math.random() * size;

      assertEquals(QuickSorting.isSorted(b), false);
      QuickSorting.sort(b);
      assertEquals(QuickSorting.isSorted(b), true);
    }
  }
}
