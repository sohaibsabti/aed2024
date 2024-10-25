package Semana3;

class Main {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static void main(String[] args) {
    ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();

    q.enqueue("a");
    System.out.println(q);
    q.enqueue("b");
    System.out.println(q);
    q.enqueue("c");
    System.out.println(q);

    q.shift();
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
    q.enqueue("a");
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
    q.enqueue("z");
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
    q.enqueue("z");
    System.out.println(q);
    q.dequeue();
    System.out.println(q);
  }
  private static void assertEqual(boolean valor, boolean esperado) {
    if(valor == esperado) {
      System.out.println("Tudo bem aqui nessa comparação...");
    } else {
      System.out.println(ANSI_RED + "Tem alguma coisa de errado nessa comparação." + ANSI_RESET);
      System.out.println(ANSI_RED +"No primeiro argumento recebemos " + valor + " e no segundo recebemos " + esperado + ", e esses valores não coincidem" + ANSI_RESET);
    }
  }

  private static void assertEqual(Object valor, Object esperado) {
    if(valor == esperado || valor.toString().equals(esperado.toString())) System.out.println("Tudo bem aqui nessa comparação...");
    else {
      System.out.println(ANSI_RED + "Tem alguma coisa de errado nessa comparação." + ANSI_RESET);
      System.out.println(ANSI_RED +"No primeiro argumento recebemos " + valor + " e no segundo recebemos " + esperado + ", e esses valores não coincidem" + ANSI_RESET);
    }
  }
}