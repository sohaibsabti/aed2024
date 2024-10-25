package Semana10;

import Semana9.ST;

import java.util.Iterator;
import java.util.Objects;

public class LinearProbingHashST<Key, Value> {
  private int N;
  private int M;
  private Key[] keys;
  private Value[] values;

  @SuppressWarnings("unchecked")
  public LinearProbingHashST(int M) {
    this.M = M;
    this.keys = (Key[]) new Object[M];
    this.values = (Value[]) new Object[M];
  }

  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value value) {
    int i = hash(key);
    while(keys[i] != null && !key.equals(keys[i])) i = (i + 1) % M;

    keys[i] = key;
    values[i] = value;
    this.N++;

    if(this.N > this.M / 2) resize(2);
  }

  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("A chave de busca não pode estar em branco");

    for(int i = hash(key); keys[i] != null; i = (i + 1) % this.M)
      if(keys[i] == key) return values[i];

    return null;
  }

  private void resize(double factor) {
    if(factor <= 0)
      throw new IllegalArgumentException("O fator de redimensionamento tem que ser positivo");

    Key[] oldK = this.keys;
    Value[] oldV = this.values;

    int newM = (int) (this.M * factor);
    this.keys = (Key[]) new Object[newM];
    this.values = (Value[]) new Object[newM];

    this.N = 0;
    this.M = newM;

    for(int i = 0; i < oldK.length; i++)
      if(oldK[i] != null) put(oldK[i], oldV[i]);
  }

  public void delete(Key key) {
    if(key == null || !contains(key)) return;

    int i = hash(key);
    while(this.keys[i] != key) i = (i + 1) % this.M;
    this.keys[i] = null;
    this.values[i] = null;
    this.N--;

    if(this.N <= this.M / 8) {
      resize(0.5);
      return;
    }

    for(i = (i + 1) % this.M; this.keys[i] != null; i = (i + 1) % this.M) {
      if(hash(this.keys[i]) == i) continue;

      Key k = this.keys[i];
      Value v = this.values[i];
      this.keys[i] = null;
      this.values[i] = null;
      put(k, v);
    }
  }

  public boolean isEmpty() {
    return this.N == 0;
  }

  public int size() {
    return this.N;
  }

  public int space() {
    return this.M;
  }

  public boolean contains(Key k) {
    if(k == null) throw new IllegalArgumentException("A chave de busca não pode estar em branco");

    int j = hash(k);
    for(int i = j; i < this.M + j; i++)
      if(this.keys[i % this.M] == k) return true;

    return false;
  }

  @Override
  public String toString() {
    String top =  "index    =  ";
    String up =   "keys[]   = [";
    String down = "values[] = [";
    for (int i = 0; i < M; i++) {
      String keyStr = Objects.toString(keys[i]);
      String valueStr = Objects.toString(values[i]);
      int pad = keyStr.length() - valueStr.length();
      if (pad < 0)
        keyStr += " ".repeat(-pad);
      else
        valueStr += " ".repeat(pad);
      top += i + " ".repeat(keyStr.length() - String.valueOf(i).length() + 1);
      up += keyStr;
      down += valueStr;
      if (i < M - 1) {
        up += " ";
        down += " ";
      }
    }
    return top + "\n" + up + "]\n" + down + "]";
  }

  public Iterable<Key> keys(){
    Queue<Key> q = new Queue<>();

    for(int i = 0; i < this.M; i++)
      if(this.keys[i] != null) q.enqueue(this.keys[i]);

    return q;
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
