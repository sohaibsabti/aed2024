package Semana4;

import java.util.Iterator;
public class Queue<T> implements Iterable<T> {
  private class Node {
    public T item;
    public Node next, previous;
    private int count;

    public Node(T item, Node previous, Node next) {
      this.item = item;
      this.next = next;
      this.previous = previous;
    }
  }

  private Node first, last;
  private int count;

  public Queue() {
    this.first = null;
    this.last = null;
    this.count = 0;
  }

  public int size() {
    return this.count;
  }

  private class QueueIterator implements Iterator<T> {
    private Node current;
    public QueueIterator() {
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

  public Iterator<T> iterator() {
    return new QueueIterator();
  }

  public void enqueue(T item) {
    Node newLast = new Node(item, this.last, null);
    if (this.first == null) this.first = newLast;
    if(this.last != null) this.last.next = newLast;
    this.last = newLast;

    this.count++;
  }

  public T dequeue() {
    if (this.first == null) throw new IllegalStateException("Stack underflow");
    T item = this.first.item;
    this.first = this.first.next;
    if(this.first != null) this.first.previous = null;

    this.count--;

    return item;
  }

  public boolean isEmpty() {
    return (this.first == null);
  }

  public void shift() {
    if(isEmpty()) return;

    // Aponta para o ultimo nó
    Node s = this.last;

    this.last = s.previous;

    // Atualiza o next do nó anterior
    s.previous.next = s.next;

    // Atualiza o primeiro nó
    this.first.previous = s;

    // Atualiza o nós em si
    s.next = this.first;
    s.previous = null;

    // Atualiza o ponteiro do começo da fila
    this.first = s;
  }
}