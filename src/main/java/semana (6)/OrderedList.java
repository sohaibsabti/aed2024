package Semana6;

import java.util.Iterator;
import java.util.Objects;

public class OrderedList<T extends Comparable<T>> implements Iterable<T> {
  public class Node {
    public T item;
    public Node next;
    public Node previous;

    public Node(T item, Node next, Node previous) {
      this.item = item;
      this.next = next;
      this.previous = previous;
    }

    public boolean lessThan(Node other) {
      return this.item.compareTo(other.item) < 0;
    }
  }

  private Node head, tail;

  private int size;

  public OrderedList() {
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

  public boolean contains(T item) {
    for(Node current = this.head.next; current != this.tail; current = current.next) {
      if(Objects.equals(current.item, item)) {
        return true;
      }
    }

    return false;
  }

  public void sort() {
    for(Node i = this.head.next; i != this.tail; i = i.next)
      for(Node j = i.previous; j != this.head && j.next.lessThan(j); j = j.previous)
        swap(j, j.next);
  }

  private void swap(Node a, Node b) {
    T temp = b.item;

    b.item = a.item;
    a.item = temp;
  }

  public boolean isSorted() {
    for(Node i = this.tail.previous; i != this.head.next && i != this.head; i = i.previous)
      if(i.lessThan(i.previous)) return false;

    return true;
  }

  public String toString() {
    String a = "";

    for(Node i = this.head.next; i != this.tail; i = i.next)
      a = a.concat(i.item.toString());

    return a;
  }

  public void shuffle() {
    for(Node i = this.head.next; i != this.tail; i = i.next)
      for(Node j = i.previous; j != this.head && Math.random() < 0.5; j = j.previous)
        swap(j, j.next);
  }
}
