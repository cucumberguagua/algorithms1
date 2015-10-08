package wk1;

/**
 * Question 2
 * Search in a bitonic array. 
 * An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. 
 * Write a program that, given a bitonic array of N distinct integer values, determines whether a given integer is in the array.
 * Standard version: Use ∼3lgN compares in the worst case.
 * Signing bonus: Use ∼2lgN compares in the worst case (and prove that no algorithm can guarantee to perform fewer than 
 * ∼2lgN compares in the worst case).
 * <p>
 * Initially, I think it needs to first compare left, mid, right, then compare the number to be searched with them
 * But I didn't have a solution, since the comparisons are too complex. 
 * Hints:
 * Standard version. First, find the maximum integer using ∼1lgN compares—this divides the array into the increasing and decreasing pieces.
 * Signing bonus. Do it without finding the maximum integer.
 *
 * <ul>For the 3lgN solution, even after the hint, I don't know how to find the maximum number.
 * https://jznest.wordpress.com/2014/03/04/search-in-a-bitonic-array/
 * gives a clear idea on this: only when A[i-1] < A[i] > A[i+1], A[i] is the maximum number ("turning point")
 */
public class BitonicArraySearch {
   
   public boolean search(int[] array, int num) {
      //find the maximum number
      int maxIndex = findMax(array);
      System.out.println(maxIndex);
      //find if the number is in the left ascending array
      int ascLow = 0;
      int ascHigh = maxIndex;
      while(ascLow <= ascHigh) {
         int ascMid = (ascLow + ascHigh)/2;
         if(num == array[ascMid]) return true;
         else if(num > array[ascMid]) {
            ascLow = ascMid + 1;
         }
         else {
            ascHigh = ascMid - 1;
         }
      }
     
      //find if the number is in the right descending array
      int descLow = maxIndex + 1;
      int descHigh = array.length - 1;
      while(descLow <= descHigh) {
         int descMid = (descLow + descHigh)/2;
         if(num == array[descMid]) return true;
         else if(num > array[descMid]) {
            descHigh = descMid - 1;           
         }
         else {
            descLow = descMid + 1;
         }
      }
      return false;
   }
  
   /**
    * Find out the turning point: max number
    * @param array
    * @return
    */
    private int findMax(int[] array) {    
       int low = 0;
       int high = array.length - 1;
       while(low <= high) {
          int mid = (low + high)/2;
          if((array[mid-1] < array[mid]) && (array[mid] > array[mid+1])) return mid;
          if((array[mid-1] < array[mid]) && (array[mid] < array[mid+1])) {
             low = mid + 1;
          }
          if((array[mid-1] > array[mid]) && (array[mid] > array[mid+1])) {
             high = mid - 1;
          }
       }
       return -1;
   }
   public static void main(String[] args) {
      int[] array = {3, 4, 5, 8, 7, 6, 2, 1, 0};
      BitonicArraySearch bas = new BitonicArraySearch();
      System.out.println(bas.search(array, 9));  
      System.out.println(bas.search(array, 2));
      
   }
}
