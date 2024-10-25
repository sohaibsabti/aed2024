package Semana2;

class Main {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static void testWeighted() {
    WeightedQUPathCompressionUF uf = new WeightedQUPathCompressionUF(5);

    uf.union(0, 4);
    uf.union(2, 3);

    assertEqual(uf.connected(2, 4), false);

    uf.union(3, 4);

    assertEqual(uf.connected(2, 4), true);

    double start = System.currentTimeMillis();

    doWUnionOperations(100);

    double end = System.currentTimeMillis();
    double previousElapsedTime = end - start;

    int START = 100;
    for(int n = START; n <= 1000000; n *= 2) {
      start = System.currentTimeMillis();
      doWUnionOperations(n);
      end = System.currentTimeMillis();
      double elapsedTime = (end - start) / 1000.0;

      double ratio = elapsedTime / previousElapsedTime;
      double lgRatio = Math.log(ratio) / Math.log(2);

      System.out.println(n + "\t" + elapsedTime + "\t" + ratio + "\t" + lgRatio + "\t");

      previousElapsedTime = elapsedTime;
    }
  }

  public static void testQuickUnion() {
    QuickUnionUF uf = new QuickUnionUF(5);

    uf.union(0, 4);
    uf.union(2, 3);

    assertEqual(uf.connected(2, 4), false);

    uf.union(3, 4);

    assertEqual(uf.connected(2, 4), true);

    double start = System.currentTimeMillis();

    doUUnionOperations(100);

    double end = System.currentTimeMillis();
    double previousElapsedTime = end - start;

    int START = 100;
    for(int n = START; n <= 1000000; n *= 2) {
      start = System.currentTimeMillis();
      doUUnionOperations(n);
      end = System.currentTimeMillis();
      double elapsedTime = (end - start) / 1000.0;

      double ratio = elapsedTime / previousElapsedTime;
      double lgRatio = Math.log(ratio) / Math.log(2);

      System.out.println(n + "\t" + elapsedTime + "\t" + ratio + "\t" + lgRatio + "\t");

      previousElapsedTime = elapsedTime;
    }
  }

  public static void testQuickFind() {
    QuickFindUF uf = new QuickFindUF(5);

    uf.union(0, 4);
    uf.union(2, 3);

    assertEqual(uf.connected(2, 4), false);

    uf.union(3, 4);

    assertEqual(uf.connected(2, 4), true);

    double start = System.currentTimeMillis();

    doFUnionOperations(100);

    double end = System.currentTimeMillis();
    double previousElapsedTime = end - start;

    int START = 100;
    for(int n = START; n <= 100000; n *= 2) {
      start = System.currentTimeMillis();
      doFUnionOperations(n);
      end = System.currentTimeMillis();
      double elapsedTime = (end - start) / 1000.0;

      double ratio = elapsedTime / previousElapsedTime;
      double lgRatio = Math.log(ratio) / Math.log(2);

      System.out.println(n + "\t" + elapsedTime + "\t" + ratio + "\t" + lgRatio + "\t");

      previousElapsedTime = elapsedTime;
    }
  }

  public static void main(String[] args) {
    testQuickFind();
    testQuickUnion();
    testWeighted();
  }

  private static void doWUnionOperations(int n) {
    WeightedQUPathCompressionUF uf = new WeightedQUPathCompressionUF(n);

    for(int i = 0; i < n; i++) {
      int p = (int) (Math.random() * n);
      int q = (int) (Math.random() * n);

      uf.union(p, q);

      p = (int) (Math.random() * n);
      q = (int) (Math.random() * n);

      uf.connected(p, q);
    }
  }

  private static void doUUnionOperations(int n) {
    QuickUnionUF uf = new QuickUnionUF(n);

    for(int i = 0; i < n; i++) {
      int p = (int) (Math.random() * n);
      int q = (int) (Math.random() * n);

      uf.union(p, q);

      p = (int) (Math.random() * n);
      q = (int) (Math.random() * n);

      uf.connected(p, q);
    }
  }

  private static void doFUnionOperations(int n) {
    QuickFindUF uf = new QuickFindUF(n);

    for(int i = 0; i < n; i++) {
      int p = (int) (Math.random() * n);
      int q = (int) (Math.random() * n);

      uf.union(p, q);

      p = (int) (Math.random() * n);
      q = (int) (Math.random() * n);

      uf.connected(p, q);
    }
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