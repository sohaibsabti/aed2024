package Semana1;

class Main {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static void main(String[] args) {
    Date erick = new Date(3,14, 1991);
    Date erick2 = new Date(3, 14, 1992);
    Date erick33 = new Date(3, 14, 2024);
    Date sayonara = new Date(4, 4, 1991);
    Date epoch = new Date(1, 1, 1970);
    Date epoch2 = new Date();

    assertEqual(epoch, epoch2);

    assertEqual(erick.daysSinceBeginYear(), 73);

    assertEqual(erick.daysUntilEndYear(), 292);
    assertEqual(epoch2.daysUntilEndYear(), 364);
    assertEqual(epoch.daysSinceBeginYear(), 1);

    assertEqual(erick.daysBetween(erick2), 365);

    assertEqual(epoch.daysBetween(epoch2), 0);
    assertEqual(erick.daysBetween(sayonara), 21);

    assertEqual(erick.daysBetween(erick33),12053);
  }

  private static void assertEqual(boolean valor, boolean esperado) {
    if(valor == esperado) {
      System.out.println("Tudo bem aqui nessa comparação...");
    } else {
      System.out.println(ANSI_RED + "Tem alguma coisa de errado nessa comparação." + ANSI_RESET);
      System.out.println(ANSI_RED +"No primeiro argumento recebemos " + valor + " e no segundo recebemos " + esperado + ", e esses valores não coincidem" + ANSI_RESET);
    }
  }

  private static void assertEqual(Object valor, Object esperado) throws IllegalStateException {
    if(valor == esperado || valor.toString().equals(esperado.toString())) System.out.println("Tudo bem aqui nessa comparação...");
    else {
      System.out.println(ANSI_RED + "Tem alguma coisa de errado nessa comparação." + ANSI_RESET);
      System.out.println(ANSI_RED +"No primeiro argumento recebemos " + valor + " e no segundo recebemos " + esperado + ", e esses valores não coincidem" + ANSI_RESET);
    }
  }
}