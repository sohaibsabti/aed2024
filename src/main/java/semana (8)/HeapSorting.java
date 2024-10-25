package Semana8;

import java.util.Arrays;

public class HeapSorting {
  public static void sort(Comparable[] a) {
    int i = 0;
    while(a[i] != null && i < a.length) {
      i++;
    }
    Comparable[] b = new Comparable[i];

    while(i > 0) {
      b[i - 1] = a[0];
      if(i > 0) sortdown(a, i);
      i--;
    }

    exchange(b, 0, 1);
    System.out.println(Arrays.toString(b));

    a = b;
  }

  public static void heapify(Comparable[] a, int N) {
    for(int i = 0; i < N; i++) swim(a, i);
  }

  public static void sortdown(Comparable[] a, int N) {
    exchange(a, 0, --N);
    a[N] = null;
    sink(a, 0, N - 1);
  }

  public static void swim(Comparable[] a, int k) {
    if(k == 0) return;

    if(lessOrEqual(a, parent(k), k)) {
      exchange(a, parent(k), k);
      swim(a, parent(k));
    }
  }

  private static void exchange(Comparable[] a, int i, int j) {
    Comparable aux = a[i];
    a[i] = a[j];
    a[j] = aux;
  }

  public static void sink(Comparable[] a, int k, int N) {
    while(left(k) < N) {
      int child = left(k);
      if(child < N && lessOrEqual(a, child, child + 1))
        child = child + 1; // Escolhe o maior dos dois filhos;

      if(!lessOrEqual(a, k, child)) break;

      exchange(a, k, child);
      k = child;
    }
  }

  private static boolean lessOrEqual(Comparable[] a, int i, int j) {
    return (a[j] == null || a[i].compareTo(a[j]) <= 0);
  }

  public static int parent(int k) {
    return (k - 1) / 2;
  }

  public static int left(int k) {
    return 2 * k + 1;
  }

  public static int right(int k) {
    return 2 * k + 2;
  }

  public static boolean isSorted(Comparable[] a) {
    for(int i = 0; i < a.length - 1; i++)
      if(a[i] != null && a[i + 1] != null && a[i].compareTo(a[i + 1]) > 0)
        return false;

    return true;
  }
}
