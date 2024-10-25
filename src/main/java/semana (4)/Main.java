package Semana4;

public class Main {
  public static void main(String[] args) {
    Queue<String> s = new Queue<String>();

    s.enqueue("1");
    String a = s.dequeue();

    System.out.println(s.size());
    s.enqueue("2");
    s.enqueue("3");
    s.enqueue("4");
    System.out.println(s.size());
//    s.enqueue("2");
//    s.enqueue("3");
//    s.enqueue("4");
//    s.enqueue("5");
//    s.enqueue("6");
//
//    for(String i : s) {
//      System.out.println(i);
//    }
//    System.out.println("=======1");
//    System.out.println(s.size());
//    s.dequeue();
//    System.out.println("=======2");
//    for(String i : s) {
//      System.out.println(i);
//    }
//    System.out.println("=======3");
//    System.out.println(s.size());
//    System.out.println("=======4");
//    for(String i : s) {
//      System.out.println(i);
//    }
//
//    System.out.println("=======5");
//    s.shift();
//    for(String i : s) {
//      System.out.println(i);
//    }
//    System.out.println("=======6");
//    s.shift();
//    for(String i : s) {
//      System.out.println(i);
//    }
//    System.out.println("=======7");
//    s.shift();
//    for(String i : s) {
//      System.out.println(i);
//    }
//    s.dequeue();
//    System.out.println("=======8");
//    for(String i : s) {
//      System.out.println(i);
//    }
  }
}
