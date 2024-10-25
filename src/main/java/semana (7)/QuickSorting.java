package Semana7;

import java.util.Arrays;
import java.util.Objects;

public class QuickSorting {
  private static final int MAX_INSERTION_SORT_SIZE = 5;

  public static boolean lessOrEqual(Comparable a, Comparable b) {
    return a.compareTo(b) <= 0;
  }

  public static void exchange(Comparable[] a, int i, int j) {
    Comparable c = a[i];
    a[i] = a[j];
    a[j] = c;
  }

  public static void shuffle(Comparable[] a) {
    for(int i = 0; i < a.length; i++) {
      int random = (int) ((i + 1) * Math.random());
      exchange(a, i, random);
    }
  }

  public static boolean isSorted(Comparable[] a) {
    for(int i = 0; i < a.length - 1; i++)
      if(!lessOrEqual(a[i], a[i + 1])) return false;
    return true;
  }

  public static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    while(true) {
      while(lessOrEqual(a[++i], a[lo]))
        if(i == hi) break;

      while(lessOrEqual(a[lo], a[--j]))
        if(j == lo) break;

      if(i >= j) break;

      exchange(a, i, j);
    }

    exchange(a, lo, j);

    return j;
  }

  public static void sortSubarray(Comparable[] a, int lo, int hi) {
    if(lo >= hi) return;
    if(hi - lo <= MAX_INSERTION_SORT_SIZE)
      insertionSort(a, lo, hi);
    else {
      int j = partition(a, lo, hi);
      sortSubarray(a, lo, j - 1);
      sortSubarray(a, j + 1, hi);
    }
  }

  public static void sort(Comparable[] a) {
    shuffle(a);
    sortSubarray(a, 0, a.length - 1);
  }

  public static void insertionSort(Comparable[] a, int lo, int hi) {
    for(int i = lo; i <= hi; i++) {
      Comparable b = a[i];
      int j = i;
      for(; j > lo && lessOrEqual(a[j], a[j - 1]); j--)
        exchange(a, j, j - 1);
      a[j] = b;
    }
  }

  public static void main(String[] args) {
    int n = 10000000;
    Double[] a = new Double[n];
    for(int i = 0; i < a.length; i++)
      a[i] = n * Math.random();

    System.out.println("Before sorting: " + Arrays.toString(a));
    System.out.println("Is sorted? " + isSorted(a));

    sort(a);

    System.out.println("After sorting: " + Arrays.toString(a));
    System.out.println("Is sorted? " + isSorted(a));
  }

  public static int medianOfThree(Comparable[] a, int lo, int hi) {
    int i1 = (int)(Math.random() * (hi - lo + 1)) + lo;
    int i2 = (int)(Math.random() * (hi - lo + 1)) + lo;
    int i3 = (int)(Math.random() * (hi - lo + 1)) + lo;

    Comparable[] b = {a[i1], a[i2], a[i3]};
    sortSubarray(b, 0, 2);

    if(Objects.equals(a[i1], b[1])) return i1;
    if(Objects.equals(a[i2], b[1])) return i2;
    return i3;
  }
}
