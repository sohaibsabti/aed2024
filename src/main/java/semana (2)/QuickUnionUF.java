package Semana2;

public class QuickUnionUF {
  int[] id;

  public QuickUnionUF(int N) {
    this.id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  public int root(int i) {
    while (i != this.id[i])
      i = this.id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rp = root(p);
    int rq = root(q);
    id[rp] = rq;
  }
}
