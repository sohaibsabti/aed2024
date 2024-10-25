package TDD;

public class Fatorial {
  static int de(int numero) {
    int fat = 1;
    for(int i = numero; i > 0; i--) fat *= i;

    return fat;
  }
}
