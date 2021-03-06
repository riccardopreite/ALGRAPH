package Kruskal;

import java.util.*;
class UnionFind {
  private Vector<Integer> p, rank, setSize;
  private int numSets;


  public UnionFind(Integer vertex) {
    p = new Vector(vertex);
    rank = new Vector(vertex);
    setSize = new Vector(vertex);
    numSets = vertex;
    for (int i = 0; i < vertex; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }
  public int findSet(int i) {
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret; } }

      public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
      }

      public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
          numSets--;
          int x = findSet(i), y = findSet(j);
          // rank is used to keep the tree short
          if (rank.get(x) > rank.get(y)) {
            p.set(y, x);
            setSize.set(x, setSize.get(x) + setSize.get(y));
          } else {
            p.set(x, y);
            setSize.set(y, setSize.get(y) + setSize.get(x));
            if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1);
          }
        }
      }

    }
