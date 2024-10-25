package Semana2;

public class WeightedQUPathCompressionUF {
  private int[] id;
  private int[] sz;

  public WeightedQUPathCompressionUF(int n) {
    id = new int[n];
    sz = new int[n];

    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  public int root(int p) {
    int i = p;

    while(i != id[i]) {
      i = id[i];
    }
    while(p != i) {
      int pReplacement = id[p];
      id[p] = i;
      p = pReplacement;
    }

    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);

    if(pRoot == qRoot) return;

    if(sz[qRoot] < sz[pRoot]) {
      id[qRoot] = pRoot;
      sz[pRoot] += sz[qRoot];
    } else {
      id[pRoot] = qRoot;
      sz[qRoot] += sz[pRoot];
    }
  }
}
