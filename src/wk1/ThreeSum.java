package wk1;

import edu.princeton.cs.algs4.Merge;
/**
 * Week1 Interview Questions: Analysis of Algorithms
 * Question 1
 * 3-SUM in quadratic time. 
 * Design an algorithm for the 3-SUM problem that takes time proportional to N2 in the worst case. 
 * You may assume that you can sort the N integers in time proportional to N2 or better.

 * @author
 * I think my solution is correct. Didn't find answer specifically for this question of Coursera
 *
 */
public class ThreeSum {
   
   
   public int count(Integer[] numbers) {
      Merge.sort(numbers);
      int count = 0;
      for(int i = 0; i < numbers.length; i++) {
         int n1 = numbers[i];
         int j = i+1;
         int k = numbers.length - 1;
         while(j < k) {
            if(numbers[j] + numbers[k] + n1 == 0) {
               count ++;
               System.out.println(n1 +  "\t" + numbers[j] + "\t" + numbers[k]);
               j++;
               k--;
            }
            else if (numbers[j] + numbers[k] + n1 < 0) {
               j++;
            }
            else {
               k--;
            }
         }
      }
      return count;
   }

   public static void main(String[] args) {
      ThreeSum threeSum = new ThreeSum();
      Integer[] array = {1, 2, 0, -3, -2};
      int count = threeSum.count(array);
      System.out.println("count = " + count);
      
   }
}
