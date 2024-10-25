package Semana3;

public class ResizingArrayQueueOfStrings {
  private String[] queue;
  private int first, last;

  public ResizingArrayQueueOfStrings(){
    this.queue = new String[1];
    this.first = -1;
    this.last = -1;
  }

  public String toString() {
    String result = "";

    for(int i = 0; i < this.queue.length; i++) {
      result += this.queue[i % this.queue.length] + " | ";
    }

    return result;
  }

  public void shift() {
    String lastItem = this.queue[last];
    resize(this.queue.length);

    for(int i = this.last; i > this.first; i--)
      this.queue[i] = this.queue[i - 1];
    this.queue[0] = lastItem;
  }

  private int size() {
    if(isEmpty()) return 0;

    if(this.last >= this.first)
      return this.last - this.first + 1;
    else
      return this.queue.length - this.first + 1 + this.last;
  }

  private int next(int position) {
    return (position + 1) % this.queue.length;
  }

  public void enqueue(String item) {
    if(this.first == next(this.last))
      resize(2 * this.queue.length);

    this.last = next(this.last);
    // Funciona porque o criterio de avaliação de vazio é first = -1; Se mudar lá, tem que mudar aqui
    if (isEmpty()) this.first = 0;
    this.queue[this.last] = item;
  }

  public String dequeue() {
    if(isEmpty())
      throw new IllegalStateException("Queue underflow!");

    String result = this.queue[this.first];
    this.queue[this.first] = null;

    this.first = next(this.first);

    if(this.queue[this.first] == null) this.first = -1;

    if(isEmpty()) resize(1);
    if(this.size() <= this.queue.length / 4)
      resize(Math.max(this.queue.length / 2, 1));

    return result;
  }

  public boolean isEmpty() {
    return this.first == -1;
  }

  private void resize(int newSize) {
    String[] newA = new String[newSize];

    for (int i = Math.max(this.first, 0); i < this.queue.length + this.size() - 1; i++) {
      if(this.queue[i % this.queue.length] == null) break;
      newA[i - this.first] = this.queue[i % this.queue.length];
    }
    this.queue = newA;

    if(this.queue[0] == null) {
      this.first = -1;
      this.last = -1;
    } else {
      this.last = this.last - this.first;
      this.first = 0;
    }
  }
}
