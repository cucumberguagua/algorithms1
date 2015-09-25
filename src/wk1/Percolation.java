package wk1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
  
   //to save memory, change grid type from int to boolean
   //private static final int OPEN = 1; //remove this
   private boolean[] sites;
   private int size;
   private WeightedQuickUnionUF uf;
   //note2: need to add another WQU to avoid backwash problems @8:47pm @9.23.2015
   private WeightedQuickUnionUF backwash;
   
   // create N-by-N grid, with all sites blocked
   public Percolation(int N) {
      if (N <= 0) {
         throw new java.lang.IllegalArgumentException();
      }
      size = N;
      int num = size*size + 2;
      sites = new boolean[num];
      uf = new WeightedQuickUnionUF(num);  
      backwash = new WeightedQuickUnionUF(num);  
      //note3: don't connect, only connect if these sites are open//why?
      //connect virtual top node to all 1st row sites
      //connect virtual bottom node to all last row sites
//      for(int j = 1; j <= N; j++) {
//         uf.union(0, xyTo1D(1, j));
//         uf.union(num - 1, xyTo1D(N, j));
//      }     
   }
       
   // open site (row i, column j) if it is not open already
   public void open(int i, int j) {
      checkRange(i, j);
      if (isOpen(i, j)) return;
      int index = xyTo1D(i, j);
      sites[index] = true;
      int upIndex, downIndex, leftIndex, rightIndex;
      //note1: don't forget to connect the first and last row to virtual top and bottom node
      if (i == 1) {
         uf.union(0, index);
         backwash.union(0, index);
      }
      if (i == size) {
         backwash.union(index, size*size + 1); //only backwash union, uf doesn't
      }
      if ((i > 1) && isOpen(i-1, j)) {   
         upIndex = xyTo1D(i-1, j);
         uf.union(index, upIndex);
         backwash.union(index, upIndex);
      }
      if ((j > 1) && isOpen(i, j-1)) {
         leftIndex = index - 1;
         uf.union(index, leftIndex);
         backwash.union(index, leftIndex);    
      }
      if ((i < size) && isOpen(i+1, j)) {
         downIndex = index + size;
         uf.union(index, downIndex);
         backwash.union(index, downIndex);       
      }
      if ((j < size) && isOpen(i, j+1)) {       
         rightIndex = index + 1;
         uf.union(index, rightIndex);
         backwash.union(index, rightIndex); 
      } 
   }
   
   // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
       checkRange(i, j);
       int index = xyTo1D(i, j);
       return sites[index] == true;
    }
   
   // is site (row i, column j) full?     
    public boolean isFull(int i, int j) {
       checkRange(i, j);
       int index = xyTo1D(i, j);
       return uf.connected(0, index);
    }
   
   // does the system percolate?   
    public boolean percolates() {
       return backwash.connected(0, sites.length-1);
    }

    private int xyTo1D(int i, int j) {
       checkRange(i, j);
       return (i-1) * size + j;
    }
    private void checkRange(int i, int j) {
       if (i < 1 || i > size || j < 1 || j > size) 
             throw new java.lang.IndexOutOfBoundsException();
    }
    
    // test client (optional)
    public static void main(String[] args) {
       Percolation perco = new Percolation(2);
       perco.open(1, 1);
       perco.open(2, 2);
       perco.open(1, 2);
       for (int i = 1; i <= 4; i++) {
          for (int j = i+1; j <= 4; j++) {
             System.out.print("i = " + i + "j = " + j + "\t");
             System.out.print(perco.uf.connected(i, j));
          }  
          System.out.println();
       }
//      System.out.println(perco.isFull(1, 1));
//      System.out.println(perco.isFull(1, 2));
        System.out.println(perco.isFull(2, 1));
//      System.out.println(perco.isFull(2, 2));
       

//       boolean twoConnected = perco.uf.connected(perco.xyTo1D(1, 1), perco.xyTo1D(1, 2));
//       System.out.println(twoConnected);
       
    }
    
}
