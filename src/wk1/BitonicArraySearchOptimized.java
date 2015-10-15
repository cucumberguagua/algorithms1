package wk1;


/**
 * Question 2
 * Search in a bitonic array. 
 * An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. 
 * Write a program that, given a bitonic array of N distinct integer values, determines whether a given integer is in the array.

 * Signing bonus: Use ∼2lgN compares in the worst case (and prove that no algorithm can guarantee to perform fewer than 
 * ∼2lgN compares in the worst case).
 * 
 * Given a bitonic array, if we cut it into half by mid, then there are only 2 possibilities:
 * <ul>
 * <li> a monotonically increasing left half, and a bitonic right half </li>
 * <li> a bitonic left half, and a monotonically decreasing right half. So
 * <ul><li> If target > a[mid], we only need to search the bitonic half (i.e. the half where MAX lies)</li>
 * <li> If target < a[mid], target can appear in either left or right half: </li>
 *    <ul><li>For left half, we do ascending binary search (= binary search of an ascending array) </li>
 *    <li>For right half, we do descending binary search (= binary search of an descending array) </li>
 * <li>I think in this case we don't care about the "bitonic part" in the left half and right half, since
 * elements in this part would be larger than a[mid], so even larger than the target. 
 * So although its still a bitonic subarray, we can treat it as a sorted array and do binary search. </li></ul> 
 *</ul>
 * <p> "it might be surprising to do a binary search on a subarray that may be bitonic but it actually works 
 * because we know that the elements that are not in the good order are all bigger than the desired value."
 * http://stackoverflow.com/questions/19372930/given-a-bitonic-array-and-element-x-in-the-array-find-the-index-of-x-in-2logn/24098821#24098821
 * <p>
 * "If a is bitonic in the range [lo,hi), then we can divide up the range into [lo, k), [k, peak), [peak, hi) 
 * where k is the first index with a[k] > a[hi].  We know the answer can only fall into the first region, [lo,k)"
 * from http://blog.csdn.net/fiveyears/article/details/11263381
 */
public class BitonicArraySearchOptimized {

   private int[] array; 
   
   public BitonicArraySearchOptimized(int[] array) {
      this.array = array;
   }
   public boolean bitonicSearch(int low, int high, int target) {
      while(low <= high) {
         int mid = (low + high)/2;
         if(target == array[mid]) return true;
         else if(target > array[mid]) {//search in bitonic half
            if(mid == 0) {
               if(array[mid] > array[mid+1]) { //max in the left half
                  return bitonicSearch(low, mid, target);
               }
               else{ //max in right half
                  return bitonicSearch(mid+1, high, target);
               }
            }
            if(mid > 0) {
               if(array[mid] > array[mid-1]) { //max in the right half
                  return bitonicSearch(mid+1, high, target);
               }
               else{ //max in left half
                  return bitonicSearch(low, mid-1, target);
               }
            } 
         }
         else { //target < array[mid]
            return ascendBinarySearch(low, mid, target) || descendBinarySearch(mid+1, high, target);
         }        
      }  
      return false;
   }
   
   private boolean ascendBinarySearch(int low, int high, int target) {
      while(low <= high) {
         int mid = (low+high)/2;
         if(array[mid] == target) return true;
         else if(array[mid] > target) {
            high = mid-1;
         }
         else {
            low = mid+1;
         }
      }
      return false;
   }
   
   
   private boolean descendBinarySearch(int low, int high, int target) {
      while(low <= high) {
         int mid = (low+high)/2;
         if(array[mid] == target) return true;
         else if(array[mid] < target) {
            high = mid-1;
         }
         else {
            low = mid+1;
         }
      }
      return false;
   }
   
   
   
   public static void main(String[] args) {
      int[] array = {2, 3, 5, 7, 9, 11, 13, 4, 1, 0};
      BitonicArraySearchOptimized ba = new BitonicArraySearchOptimized(array);
      int low = 0;
      int high = array.length - 1;
      System.out.println(ba.bitonicSearch(low, high, 1));
      System.out.println(ba.bitonicSearch(low, high, 1));
      System.out.println(ba.bitonicSearch(low, high, 1));
      System.out.println(ba.bitonicSearch(low, high, 2));
      System.out.println(ba.bitonicSearch(low, high, 3));
      System.out.println(ba.bitonicSearch(low, high, 4));
      System.out.println(ba.bitonicSearch(low, high, 5));
      System.out.println(ba.bitonicSearch(low, high, 6));
      System.out.println(ba.bitonicSearch(low, high, 7));
      System.out.println(ba.bitonicSearch(low, high, 8));
      System.out.println(ba.bitonicSearch(low, high, 9));
      System.out.println(ba.bitonicSearch(low, high, 10));
      System.out.println(ba.bitonicSearch(low, high, 11));
      System.out.println(ba.bitonicSearch(low, high, 12));
      System.out.println(ba.bitonicSearch(low, high, 13));//
      System.out.println(ba.bitonicSearch(low, high, 14));
      System.out.println(ba.bitonicSearch(low, high, 15));
      
   }
}
