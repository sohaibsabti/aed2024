package Semana9;

import java.util.Iterator;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
  private class Node {
    public Key key;
    public Value value;

    public Node left, right;

    public Node(Key k, Value v) {
      this.key = k;
      this.value = v;
    }
  }

  private Node root;

  public void put(Key k, Value v) {
    this.root = put(this.root, k, v);
  }

  private Node put(Node i, Key k, Value v) {
    if(i == null) return new Node(k, v);

    int cmp = k.compareTo(i.key);

    if(cmp == 0) {
      i.value = v;
      return i;
    }

    if(cmp < 0)
      i.left = put(i.left, k, v);
    else
      i.right = put(i.right, k, v);

    return i;
  }

  public Value get(Key k) {
    return get(this.root, k);
  }

  private Value get(Node i, Key k) {
    if (i == null) return null;

    int cmp = k.compareTo(i.key);
    if(cmp == 0) return i.value;

    if(cmp < 0)
      return get(i.left, k);
    else
      return get(i.right, k);
  }

  public boolean isEmpty() {
    return this.root == null;
  }

  public Key min() {
    if(isEmpty()) return null;
    return min(this.root).key;
  }

  private Node min(Node i) {
    if(i == null) return null;
    if(i.left == null) return i;

    return min(i.left);
  }

  public Key max() {
    if(isEmpty()) return null;

    return max(this.root).key;
  }

  private Node max(Node i) {
    if(i.right == null) return i;

    return max(i.right);
  }

  public int size(Key lo, Key hi) {
    return size(this.root, lo, hi);
  }

  private int size(Node i, Key lo, Key hi) {
    if(i == null) return 0;

    int left = i.left == null ? 0 : size(i.left, lo, hi);
    int right = i.right == null ? 0 : size(i.right, lo, hi);

    int countThisNode = i.key.compareTo(lo) >= 0 && i.key.compareTo(hi) <= 0 ? 1 : 0;

    return countThisNode + left + right;
  }

  private int size(Node i) {
    return size(i, min(i).key, max(i).key);
  }

  public int size() {
    return size(this.root, min(), max());
  }

  public int height() {
    return height(this.root);
  }

  private int height(Node i) {
    if(i == null) return 0;

    return Math.max(height(i.left), height(i.right)) + 1;
  }

  public Key floor(Key key) {
    Node r = floor(key, this.root);

    if(r == null) return null;

    return r.key;
  }

  private Node floor(Key key, Node i) {
    if(i == null) return null;

    int cmp = key.compareTo(i.key);

    if(cmp == 0) return i;

    Node left = floor(key, i.left);
    Node right = floor(key, i.right);

    if(cmp < 0) {
      return left;
    }

    if(right != null) return right;

    return i;
  }

  public Key ceiling(Key key) {
    Node r = ceiling(key, this.root);

    if(r == null) return null;

    return r.key;
  }

  private Node ceiling(Key key, Node i) {
    if(i == null) return null;

    int cmp = key.compareTo(i.key);

    if(cmp == 0) return i;

    Node left = ceiling(key, i.left);
    Node right = ceiling(key, i.right);

    if(cmp > 0) {
      return right;
    }

    if(left != null) return left;

    return i;
  }

  public int rank(Key key) { return rank(key, this.root); }

  private int rank(Key key, Node i) {
    if(i == null) return 0;

    return (
      (i.key.compareTo(key) < 0 ? 1 : 0)
        + rank(key, i.left)
        + rank(key, i.right)
    );
  }

  public boolean contains(Key k) {
    return contains(k, this.root);
  }

  private boolean contains(Key k, Node i) {
    if(i == null) return false;

    return i.key.compareTo(k) == 0 || contains(k, i.left) || contains(k, i.right);
  }

  public void deleteMin() {
    this.root = deleteMin(this.root);
  }

  private Node deleteMin(Node i) {
    if (i.left == null) return i.right;
    i.left = deleteMin(i.left);

    return i;
  }

  public void deleteMax() {
    this.root = deleteMax(this.root);
  }

  private Node deleteMax(Node i) {
    if (i.right == null) return i.left;
    i.right = deleteMax(i.right);

    return i;
  }

  /* Para o Iterator */
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> q = new Queue<>();

    inorder(this.root, q, lo, hi);

    return q;
  }

  public Iterable<Key> keys(){
    Queue<Key> q = new Queue<>();

    inorder(this.root, q, min(), max());

    return q;
  }

  private void inorder(Node i, Queue<Key> q, Key lo, Key hi) {
    if(i == null) return;

    inorder(i.left, q, lo, hi);
    if(i.key.compareTo(lo) >= 0 && i.key.compareTo(hi) <= 0) q.enqueue(i.key);
    inorder(i.right, q, lo, hi);
  }

  public Iterator<Key> iterator() { return keys().iterator(); }


  class Queue<T> implements Iterable<T> {
    private class QueueNode {
      public T item;
      public QueueNode next, previous;
      private int count;

      public QueueNode(T item, QueueNode previous, QueueNode next) {
        this.item = item;
        this.next = next;
        this.previous = previous;
      }
    }

    private QueueNode first, last;
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
      private QueueNode current;
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

    public QueueNode first() {
      return this.first;
    }

    public void enqueue(T item) {
      QueueNode newLast = new QueueNode(item, this.last, null);
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
      QueueNode s = this.last;

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
}