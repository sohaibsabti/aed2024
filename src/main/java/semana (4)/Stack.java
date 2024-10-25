package Semana4;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
  private class Node {
    public T item;
    public Node next;

    public Node(T item, Node next) {
      this.item = item;
      this.next = next;
    }
  }

  private Node first;
  private int count;

  public Stack() {
    this.first = null;
    this.count = 0;
  }

  public void push(T item) {
    this.first = new Node(item, this.first);
    this.count++;
  }

  public T pop() {
    if(isEmpty()) throw new IllegalStateException("Stack underflow");

    T result = this.first.item;
    this.first = this.first.next;
    this.count--;

    return result;
  }

  public int size() {
    return this.count;
  }

  public boolean isEmpty() { return this.first == null; }

  public Iterator<T> iterator() {
    return new StackIterator();
  }

  private class StackIterator implements Iterator<T> {
    private Node current;
    public StackIterator() {
      this.current = first;
    }

    public boolean hasNext() {
      return this.current != null;
    }

    public T next() {
      T item = this.current.item;
      this.current = this.current.next;
      return item;
    }
  }
}
