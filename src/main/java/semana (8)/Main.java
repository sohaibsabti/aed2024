package Semana8;

import Utils.TreePrinter;

import java.util.Arrays;

import static Semana8.HeapSorting.left;
import static Semana8.HeapSorting.right;
import static Utils.Test.assertEquals;

public class Main {
  public static void main(String[] args) {
    Integer[] a = { 0, 1, 2, 3, 4, 5, 6 };
    assertEquals(left(0), 1);
    assertEquals(right(0), 2);

    assertEquals(left(1), 3);
    assertEquals(right(1), 4);

    assertEquals(left(2), 5);
    assertEquals(right(2), 6);

    assertEquals(HeapSorting.parent(0), 0);
    assertEquals(HeapSorting.parent(1), 0);
    assertEquals(HeapSorting.parent(2), 0);
    assertEquals(HeapSorting.parent(3), 1);
    assertEquals(HeapSorting.parent(4), 1);
    assertEquals(HeapSorting.parent(5), 2);
    assertEquals(HeapSorting.parent(6), 2);

    HeapSorting.sink(a, 0, 7);
    int[] expected = {2, 1, 6, 3, 4, 5, 0};
    for(int i = 0; i < expected.length; i++) {
      assertEquals(a[i], expected[i]);
    }

    HeapSorting.heapify(a, a.length);
    expected = new int[]{6, 4, 5, 1, 3, 2, 0};
    for(int i = 0; i < expected.length; i++) {
      assertEquals(a[i], expected[i]);
    }

    Integer[] b = new Integer[31];
    for(int i = 0; i < 31; i++) { b[i] = (int) (Math.random() * 100); }
    HeapSorting.heapify(b, b.length);
    print(b, b.length);
    HeapSorting.sortdown(b, b.length);
    print(b, b.length);

    assertEquals(HeapSorting.isSorted(b), false);
    HeapSorting.sortdown(b, b.length - 1);
    print(b, b.length);
    HeapSorting.sort(b);
    assertEquals(HeapSorting.isSorted(b), true);
  }

  public static void print(Comparable[] arr, int N) {
    TreePrinter<Integer> printer = new TreePrinter<>(k -> "" + arr[k],
      k -> left(k) < N ? left(k) : null,
      k -> right(k) < N ? right(k) : null);
    printer.setHspace(1); //espaço horizontal entre nós
    printer.setSquareBranches(true); //acho que gosto mais assim, mas false também é fixe
    printer.printTree(0); //1 = root, isto é com a heap a começar no índice 1
  }
}
