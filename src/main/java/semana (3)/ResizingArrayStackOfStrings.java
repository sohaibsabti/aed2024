package Semana3;

public class ResizingArrayStackOfStrings {
  private String[] stack;
  private int top;

  public ResizingArrayStackOfStrings() {
    stack = new String[1];
    top = 0;
  }

  public ResizingArrayStackOfStrings(int capacity) {
    stack = new String[capacity];
    top = 0;
  }

  public void push(String item) {
    if(top == stack.length)
      resize(2 * stack.length);

    stack[top++] = item;
  }

  public String pop() {
    if(top == 0)
      throw new IllegalStateException("Stack underflow");

    String result = stack[--top];
    stack[top] = null;

    if(top == stack.length / 4)
      resize(stack.length / 2);

    return result;
  }

  private void resize(int newSize) {
    String[] newA = new String[newSize];

    for(int i = 0; i < top; i++)
      newA[i] = this.stack[i];

    this.stack = newA;
  }

  public boolean isEmpty() {
    return top == 0;
  }

  public int size() {
    return top;
  }

  public static void main(String[] args) {
    ResizingArrayStackOfStrings r = new ResizingArrayStackOfStrings();

    r.push("banana");
    r.push("banana");
    r.push("banana");
    r.push("banana");
    r.push("banana");
    r.push("banana");
    r.push("banana");
    r.push("banana");

    System.out.println(r.size());
  }
}
