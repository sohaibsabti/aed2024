package Semana5;

import java.util.Iterator;
import java.util.Objects;

public class List<T> implements Iterable<T> {
  public class Node {
    public T item;
    public Node next, previous;

    public Node(T item, Node next, Node previous) {
      this.item = item;
      this.next = next;
      this.previous = previous;
    }
  }

  private Node head, tail;

  private int size;

  public List() {
    this.tail = new Node(null, null, null);
    this.head = new Node(null, this.tail, null);

    this.tail.previous = this.head;

    this.size = 0;
  }

  private class ListIterator implements Iterator<T> {
    private Node current;
    public ListIterator() {
      this.current = head.next;
    }

    public boolean hasNext() {
      return this.current != tail;
    }

    public T next() {
      T item = this.current.item;
      this.current = this.current.next;
      return item;
    }
  }
  public Iterator<T> iterator() {
    return new ListIterator();
  }

  public boolean isEmpty() {
    return this.head.next == this.tail;
  }

  public int size() {
    return size;
  }

  public void add(T item) {
    Node where = this.tail.previous;
    where.next = new Node(item, this.tail, this.tail.previous);
    tail.previous = where.next;
    this.size++;
  }

  public T get(int index) {
    if(index > this.size || index < 1) throw new IllegalArgumentException("Stack overflow :'(");

    Node current = head.next;
    while(index > 1) {
      current = current.next;
      index--;
    }

    return current.item;
  }

  public T remove(int index) {
    if(index > this.size || index < 1) throw new IllegalArgumentException("Stack overflow :'(");

    Node current = head.next;
    while(index > 1) {
      current = current.next;
      index--;
    }
    T t = current.item;

    current.previous.next = current.next;
    current.next.previous = current.previous;

    this.size--;
    return t;
  }

  public boolean removeFirst(T item) {
    Node current = this.head.next;

    while(current != this.tail) {
      if(current.item == item) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current = null;

        this.size--;
        return true;
      }

      current = current.next;
    }

    return false;
  }

  public boolean removeLast(T item) {
    Node current = this.tail.previous;
    while(current != this.head) {
      if(current.item == item) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current = null;

        this.size--;
        return true;
      }

      current = current.previous;
    }

    return false;
  }

  public boolean removeAll(T item) {
    boolean removed = false;

    for(Node current = head.next; current != tail; current = current.next)
      if(Objects.equals(current.item, item)) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
        removed = true;

        this.size--;
      }

    return removed;
  }

  public boolean contains(T item) {
    for(Node current = this.head.next; current != this.tail; current = current.next) {
      if(Objects.equals(current.item, item)) {
        return true;
      }
    }

    return false;
  }

  public boolean isPalindrome() {
    Node upwards = head.next;
    Node downwards = tail.previous;

    while(upwards != tail && downwards != head) {
      if (!Objects.equals(upwards.item, downwards.item)) return false;
      upwards = upwards.next;
      downwards = downwards.previous;
    }
    return true;
  }
}
