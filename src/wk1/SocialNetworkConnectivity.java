package wk1;

import java.io.BufferedReader;
import java.io.FileReader;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Q: Why complexity = Mlog(N)?
 * A: 
 * https://class.coursera.org/algs4partI-009/forum/thread?thread_id=94
 * If you assume that the only friends in the log file were in the set of N friends, 
 * then you can just loop through the log file and do uf.union(friend1, friend2) until uf.count() is 1
 * everyone is in the same component.  M unions is M log(N) per the slides.  
 * The count() call is a constant since it's just reading a counter stored in the uf instance.
 * If you assume that the log file can contain other friends, 
 * then you can do a log(N) search (like binary search) to make sure the friends are in the list.
**/
public class SocialNetworkConnectivity {
   
   private int N; //N members
   private String logFile;
   private WeightedQuickUnionUF uf;
   
   
   
   public SocialNetworkConnectivity (int n, String logFile) {
      this.N = n;
      this.logFile = logFile;
      uf = new WeightedQuickUnionUF(N);  
   }
   
   public String findWhenAllConnected() {
      String line;
      String [] arr;
      int member1;
      int member2;
      String timestamp = "";
     
      try {
         BufferedReader br = new BufferedReader(new FileReader(logFile));
         while(uf.count() > 1) {
            line = br.readLine();
            arr = line.split("\t");
            member1 = Integer.parseInt(arr[0])-1;
            member2 = Integer.parseInt(arr[1])-1;
            timestamp = arr[2];
            uf.union(member1, member2);
         }
         br.close();
      } catch (Exception e) {
         e.printStackTrace();
      }   
      return timestamp;
      
   }
   
   public static void main(String[] args) {
      SocialNetworkConnectivity sns = new SocialNetworkConnectivity(5, "snslog.txt");
      String allConnectedTime = sns.findWhenAllConnected();
      System.out.println(allConnectedTime);
      
   }
}
