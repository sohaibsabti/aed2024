package Semana10;

import java.util.Objects;

import static Utils.Test.assertEquals;

public class Main {
  public static void main(String[] args) {
    LinearProbingHashST<String, String> st = new LinearProbingHashST<>(2);
    System.out.println(st.toString());
    st.put("1", "banana");
    assertEquals(st.space(), 2);
    assertEquals(st.size(), 1);
    assertEquals(st.contains("3"), false);
    System.out.println(st.toString());
    st.put("3", "nanica");
    assertEquals(st.size(), 2);
    assertEquals(st.space(), 4);
    assertEquals(st.contains("3"), true);
    st.put("pipoca", "com manteiga");
    assertEquals(st.size(), 3);
    assertEquals(st.space(), 8);
    assertEquals(st.contains("pipoca"), true);
    System.out.println(st.toString());
    for(Object o : st.keys()) System.out.println(o);

    st.delete("pipoca");
    assertEquals(st.size(), 2);
    assertEquals(st.space(), 8);
    assertEquals(st.contains("pipoca"), false);
    st.delete("3");
    assertEquals(st.size(), 1);
    assertEquals(st.space(), 4);
    assertEquals(st.contains("3"), false);
    System.out.println(st.toString());
  }
}
