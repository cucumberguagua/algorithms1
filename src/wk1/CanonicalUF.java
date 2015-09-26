package wk1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * week1 Interview Question Q2 
 * Union-find with specific canonical element
 * Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. 
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 * For example, if one of the connected components is {1,2,6,9}, 
 * then the find() method should return 9 for each of the four elements in the connected components because 9 is larger 1, 2, and 6.
 *
 */
public class CanonicalUF {
   private int[] parents;
   private int[] sizes; // size[i] = number of sites in subtree rooted at i
   private int count; //number of connected components
   private int[] max; // max[i] = largest elements in subtree rooted at i
   
   public CanonicalUF(int N) {
      count = N;
      parents = new int[N];
      sizes = new int[N];
      max = new int[N];
      for(int i = 0; i < N; i++) {
         parents[i] = i;
         sizes[i] = 1;
         max[i] = i;
      }   
   }
   
   public void union(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);     
      if(sizes[rootP] <= sizes[rootQ]) {
         parents[rootP] = rootQ;
         sizes[rootQ] += sizes[rootP];
         max[rootQ] = Math.max(max[rootP], max[rootQ]);
      }
      else{
         parents[rootQ] = rootP;
         sizes[rootP] += sizes[rootQ];
         max[rootP] = Math.max(max[rootP], max[rootQ]);
      }
      count--;
   }
   
   /**
    * Return the component index (root) of the component containing site i
    * @param i
    * @return
    */
   public int find(int i) {
      validate(i);
      while(parents[i] != i) {
         i = parents[i];
      }
      return i;
   }
   
  
   private void validate(int p) {
      int N = parents.length;
      if(p < 0 || p >= N) {
         throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
      }
   }
   
   public boolean connected(int p, int q) {
      return find(p) == find(q);
   }
   
   public int findMax(int p) {
      int rootP = find(p);
      return max[rootP];
   }
   //don't use this, since this is NlogN, not logN
//   public int findMax(int j) {
//      int maxIndex = Integer.MIN_VALUE;
//      for(int i = 0; i < parents.length; i++) {
//         if(connected(i, j)) {
//            if(i > maxIndex) {
//               maxIndex = i;
//            }
//         }
//      }
//      return maxIndex;
//   }
   /**
    * @return The number of connected components
    */
   public int count() {
      return count;
   }
   

   /** Use Ctrl+D to exit -- to satisfy StdIn.isEmpty() */
   public static void main(String[] args) {
      int N = StdIn.readInt();
      CanonicalUF cuf = new CanonicalUF(N);
      while(!StdIn.isEmpty()) {
         int p = StdIn.readInt();
         int q = StdIn.readInt();
         if(cuf.connected(p, q)) {
            continue;
         }
         cuf.union(p, q);
         StdOut.println(p + " " + q);
      }
      for(int i = 0; i < N; i++){
         StdOut.println("Max index for component of " + i + "=" + cuf.findMax(i));
      }
      
   }
   
}
