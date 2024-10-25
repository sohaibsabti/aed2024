package TDD;

import static Utils.Test.assertEquals;
import static Utils.Test.maybe;

public class Main {
  public static void main(String[] args) {
    assertEquals(Fatorial.de(5), 120);

    assertEquals(Fatorial.de(10), 3628800);
    assertEquals(Fatorial.de(6), 720);

    assertEquals(Fatorial.de(5) > Fatorial.de(3), true);
  }
}
