package Semana6;

import java.util.Arrays;

public class Sorting {
  public static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  public static void swap(Object[] a, int i, int j) {
    Object b = a[i];
    a[i] = a[j];
    a[j] = b;
  }

  public static void sort(Comparable[] a) {
    for(int i = 0; i < a.length - 1; i++)
      for(int j = i; j >= 0 && less(a[j + 1], a[j]);  j--)
          swap(a, j, j + 1);
  }

  public static boolean isSorted(Comparable[] a) {
    for(int i = 0; i < a.length - 1; i++)
      if(less(a[i + 1], a[i])) return false;
    return true;
  }

  public static void shuffle(Object[] a){
    for(int i = 0; i < a.length; i++)
      swap(a, i, (int)(Math.random() * (i + 1)));
  }

  public static void main(String[] args) {
    Integer[] a = new Integer[] { 5, 3, 1, 4, 2};
    sort(a);
    System.out.println(Arrays.toString(a));
    shuffle(a);
    System.out.println(Arrays.toString(a));
  }
}
