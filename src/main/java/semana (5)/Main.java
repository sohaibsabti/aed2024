package Semana5;

import static Utils.Test.assertEquals;

public class Main {
  public static void main(String[] args) {
    List<String> a = new List<String>();

    assertEquals(a.isEmpty(), true);
    a.add("a"); // a
    assertEquals(a.isEmpty(), false);
    a.add("b"); // a - b
    a.add("c"); // a - b - c
    assertEquals(a.get(3), "c");

    a.remove(2); // a - c
    assertEquals(a.get(2), "c");

    a.add("a"); // a - c - a
    a.removeFirst("a"); // c - a
    assertEquals(a.get(1), "c");

    a.add("c"); // c - a - c
    a.add("a"); // c - a - c - a
    assertEquals(a.size(), 4);
    assertEquals(a.get(3), "c");
    a.removeLast("c"); // c - a - a

    assertEquals(a.get(2), "a");
    assertEquals(a.get(3), "a");
    assertEquals(a.size(), 3);
    a.removeAll("a"); // c
    assertEquals(a.size(), 1);
    assertEquals(a.get(1), "c");

    assertEquals(a.isPalindrome(), true);
    a.add("a"); // c - a
    assertEquals(a.isPalindrome(), false);
    a.add("c"); // c - a - c
    assertEquals(a.isPalindrome(), true);

    assertEquals(a.removeFirst("j"), false);
    assertEquals(a.size(), 3);
    assertEquals(a.removeLast("j"), false);
    assertEquals(a.size(), 3);
    assertEquals(a.removeAll("j"), false);
    assertEquals(a.size(), 3);

    assertEquals(a.removeAll("a"), true);
    assertEquals(a.size(), 2);

    assertEquals(a.removeFirst("c"), true);
    assertEquals(a.size(), 1);
    assertEquals(a.removeLast("c"), true);
    assertEquals(a.size(), 0);

    List<Character> c = new List<Character>();
    String b = "socorrammesubinoonibusemmarrocos";

    for(int i = 0; i < b.length(); i++)
      c.add(b.charAt(i));

    assertEquals(c.isPalindrome(), true);

    assertEquals(c.removeAll('j'), false);
    assertEquals(c.removeAll('o'), true);

    assertEquals(c.isPalindrome(), true);

    c.remove(1);
    assertEquals(c.isPalindrome(), false);

    for(Character s : c) System.out.println(s);
  }
}
