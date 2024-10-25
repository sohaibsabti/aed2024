package Semana6;

import static Utils.Test.assertEquals;
import static Utils.Test.maybe;

public class Main {
  public static void main(String[] args) {
    OrderedList<String> a = new OrderedList<String>();

    assertEquals(a.isEmpty(), true);
    a.add("b"); // a
    assertEquals(a.isEmpty(), false);
    a.add("c"); // a - b
    a.add("d"); // a - b - c

    assertEquals(a.isSorted(), true);
    assertEquals(a.toString(), "bcd");
    assertEquals(a.size(), 3);

    a.add("a");
    assertEquals(a.isSorted(), false);
    assertEquals(a.toString(), "bcda");
    assertEquals(a.size(), 4);

    a.sort();
    assertEquals(a.isSorted(), true);
    assertEquals(a.toString(), "abcd");
    assertEquals(a.size(), 4);

    a.add("a");
    assertEquals(a.isSorted(), false);
    a.sort();
    assertEquals(a.isSorted(), true);
    a.add("z");
    assertEquals(a.isSorted(), true);
    a.sort();
    assertEquals(a.isSorted(), true);
    a.add("h");
    assertEquals(a.isSorted(), false);
    a.sort();
    assertEquals(a.isSorted(), true);
    a.add("p");
    assertEquals(a.isSorted(), false);
    a.sort();
    assertEquals(a.isSorted(), true);

    a.shuffle();
    assertEquals(a.isSorted(), maybe);
    // Criei maybe aqui, porque, ainda que seja improvável,
    // o baralhamento pode não trocar posições nenhumas
    System.out.println(a);
    a.sort();
    assertEquals(a.isSorted(), true);
  }
}