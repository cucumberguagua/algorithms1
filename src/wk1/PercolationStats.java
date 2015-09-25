package wk1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  
   private double[] thresholdArr;
   private int t;
   
   // perform T independent experiments on an N-by-N grid
   public PercolationStats(int N, int T) {
      if (N <= 0 || T <= 0) {
         throw new java.lang.IllegalArgumentException();
      }
      thresholdArr = new double[T];
      t = T;
      int num = N*N;
      int row;
      int col;
      for (int i = 0; i < T; i++) {
         Percolation percolation = new Percolation(N); 
         int openSiteNum = 0;
         while (!percolation.percolates()) {
            row = StdRandom.uniform(N) + 1;
            col = StdRandom.uniform(N) + 1;
//            System.out.println("row = " + row + "col = " + col);
            if (!percolation.isOpen(row, col)) {  
               percolation.open(row, col);
               openSiteNum++;
            }
         } 
         thresholdArr[i] = (double) openSiteNum / num;
       }
    }

   
   // sample mean of percolation threshold
   public double mean() {
      return StdStats.mean(thresholdArr);
   }
   
   // sample standard deviation of percolation threshold
   public double stddev() { 
      return StdStats.stddev(thresholdArr);
   }
   
   // low  endpoint of 95% confidence interval
   public double confidenceLo() {
      return mean() - 1.96*stddev()/Math.sqrt(t);
   }
   
   // high endpoint of 95% confidence interval
   public double confidenceHi() {
      return mean() + 1.96*stddev()/Math.sqrt(t);
   }

   // test client (described below)
   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);
      System.out.println("% java PercolationStats " + N + " " + T);
      PercolationStats ps = new PercolationStats(N, T);
      //note:move this part to constructor. Why?
//      int num = N*N;
//      int row;
//      int col;
//      int randomNum = 0;
//      for (int i = 0; i < T; i++) {
//         Percolation percolation = new Percolation(N);   
//         while (!percolation.percolates()) {
//            randomNum = StdRandom.uniform(num);
//            row = randomNum / N + 1;
//            if (randomNum % N == 0) {
//               col = N;
//            }
//            else {
//               col = randomNum % N;
//            }
//            percolation.open(row, col);
//         }
//         double openSiteNum = 0;
//         for (int index = 0; index < num; index++) {
//            row = index / N + 1;
//            if (randomNum % N == 0) {
//               col = N;
//            }
//            else {
//               col = randomNum % N;
//            }
//            if (percolation.isOpen(row, col)) {
//               openSiteNum++;
//            }
//         }
//         System.out.println("open = " + openSiteNum);//open = 0
//         ps.thresholdArr[i] = openSiteNum / num;      
//      }
//      for(int i = 0; i < thresholdArr.length; i++) {//all = 0
//         System.out.println(thresholdArr[i]);
//      }
      System.out.printf("%20s=" + ps.mean(), "mean");
      System.out.printf("%20s=" + ps.stddev(), "stddev");
      System.out.printf("%20s=" + ps.confidenceLo() + ", " + ps.confidenceHi(), "95% confidence interval");
   }
   
   
   
}
