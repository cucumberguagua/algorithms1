package wk1;

/**
 * wk1 Interview Question 4
 * Union-by-size. (height)
 * Develop a union-find implementation that uses the same basic strategy as weighted quick-union 
 * but keeps track of tree height and always links the shorter tree to the taller one. 
 * Prove a lgN upper bound on the height of the trees for N sites with your algorithm.

 * O(logN) proof: http://advancedxy.com/blog/2014/02/13/interview-questions-union-find-solutions/
 * 设函数h(n)表示该算法下高度为n的最小数目的sites.那么最初始的值为:
 * h(0) = 1 // 高度为0, 也即只有一个节点..
 * h(1) = 2 // 高度为1,至少有两个节点
 * 由于只有在两个节点所代表的树高度一致时才会进行高度的更新,且高度只加一,故 h(n+1)=h(n)+h(n)=2h(n)=2∗2∗h(n−1)=...=2n+1
 * 也即高度为n的树包含的sites至少为2n,也即对于N个sites的树来说,高度最高为log2N.得证!

 * @author
 *
 */
public class UnionByHeight {

   private int[] parents;
   private int[] height; //height[i] = height of subtree rooted at i
   
   
   public UnionByHeight(int N) {
      height = new int[N];
      parents = new int[N];
      for(int i = 0; i < N; i++) {
         parents[i] = i;
         height[i] = 0; //Note: height should be initialized to 0 instead of 1
      }
   }
   
   public boolean connected(int p, int q) {
      return root(p) == root(q);
   }
   
   //Note that height is not added up
   //if not clear, draw it
   //only when the two subtrees rooted at p and q have same height, 
   //the height is added by 1
   public void union(int p, int q) {
      int rootP = root(p);
      int rootQ = root(q);
      if(rootP == rootQ) return;
      if(height[rootP] < height[rootQ]) {
         parents[rootP] = rootQ;
//         height[rootQ] += height[rootP];
      }
      else {
         parents[rootQ] = rootP;
//         height[rootP] += height[rootQ];
         if(height[rootP] == height[rootQ]) {
            height[rootP] += 1;
         }
      }
   }
   
   private int root(int p) {
      while(parents[p] != p) {
         p = parents[p];
      }
      return p;
   }
}
