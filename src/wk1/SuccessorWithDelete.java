package wk1;

/**
 * Week 1 Interview Question 3: 
 * Successor with delete. Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:
 * <ul> Remove x from S </ul>
 * <ul>Find the successor of x: the smallest y in S such that y≥x.</ul>
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 * 
 * <p>This is a difficult question. Given that I know it should be solved by Union-Find,
 * I didn't think of any solution.
 * Inspired by coursera hint and 
 * http://advancedxy.com/blog/2014/02/13/interview-questions-union-find-solutions/#section-5
 * this question is the same as CanonicalUF, and I coded it out. 
 * 
 * Note that another solution has different opinion: use QuickUnion with path compression. 
 * http://tech-wonderland.net/blog/interview-questions-successor-with-delete.html
 * @author 
 *
 */
public class SuccessorWithDelete {
   private int[] parents;
   private int[] sizes;
   private int[] max;
   
   public SuccessorWithDelete (int N){
      parents = new int[N];
      sizes = new int[N];
      max = new int[N];
      for(int i = 0; i < N; i++) {
         parents[i] = i;
         sizes[i] = 1;
         max[i] = i;
      }
   }
   
   public void delete(int x) {
      union(x, x+1);
   }
   
   
   public int findSuccessor(int x) {
      validate(x);
      int rootX = root(x);
      return max[rootX];
   }
   
   public void union(int p, int q) {
      int rootP = root(p);
      int rootQ = root(q);
      if(sizes[rootP] < sizes[rootQ]) {
         parents[rootP] = rootQ;
         sizes[rootQ] += sizes[rootP];
         max[rootQ] = Math.max(max[rootP], max[rootQ]);
      }
      else{
         parents[rootQ] = rootP;
         sizes[rootP] += sizes[rootQ];
         max[rootP] = Math.max(max[rootP], max[rootQ]);
      }
   }
   
   public int root(int p) {
      validate(p);
      while(parents[p]!=p) {
        parents[p] = parents[parents[p]]; //path compression 
        p = parents[p];
      }
      return p;
   }
   
   private void validate(int p) {
      int N = parents.length;
      if(p < 0 || p >= N) {
         throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
      }
   }
   
   public static void main(String[] args) {
      int N = 6;
      SuccessorWithDelete sw = new SuccessorWithDelete(N);
      sw.delete(0);
      sw.delete(1);
      System.out.println(sw.findSuccessor(0));
      
      sw.delete(3);
      System.out.println(sw.findSuccessor(2)); //one's successor can be itself
      System.out.println(sw.findSuccessor(4));
   }
}
