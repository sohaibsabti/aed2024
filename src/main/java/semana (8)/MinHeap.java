package Semana8;

public class MinHeap<T extends Comparable<T>> {
  private T[] pq;
  private int size;

  public MinHeap(int capacity) {
    this.pq = (T[]) new Comparable[capacity]; // Ugly cast bug
  }

  public boolean isEmpty() { return size == 0; };

  public void insert(T item) {
    this.pq[++size] = item;
    swim(size);
  }

  public T delMin() {
    T min = pq[1];

    exchange(1, size);
    this.pq[size--] = null;
    sink(1);

    return min;
  }

  private void exchange(int i, int j) {
    T aux = this.pq[i];
    this.pq[i] = this.pq[j];
    this.pq[j] = aux;
  }

  private boolean greaterOrEqual(int i, int j) {
    return this.pq[i].compareTo(this.pq[j]) >= 0;
  }

  private int left(int k) { return k * 2; }

  private int right(int k) { return 2 * k + 1; }

  private int parent(int k) { return k / 2; }

  private void swim(int k) {
    if(k == 1) return;

    if(greaterOrEqual(parent(k), k)) {
      exchange(parent(k), k);
      swim(parent(k));
    }
  }

  private void sink(int k) {
    while(left(k) <= size) {
      int child = left(k);
      if(child < size && greaterOrEqual(child, child + 1)) child = child + 1; // Escolhe o menor dos dois filhos (right = left + 1)

      if(!greaterOrEqual(k, child)) break;

      exchange(k, child);
      k = child;
    }
  }

  public static void main(String[] args) {
    MinHeap<Integer> heap = new MinHeap<>(10);

    for(int i = 9; i > 0; i--) {
      heap.insert((int) (i));
    }

    while(!heap.isEmpty())
      System.out.println(heap.delMin());
  }
}
