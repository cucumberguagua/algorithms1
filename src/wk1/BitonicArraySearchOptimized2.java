package wk1;

/**
 * This answer is from http://blog.csdn.net/fiveyears/article/details/11263381
 * The code is different from my BitonicArraySearchOptimized, but I think they are 
 * essentially the same. Need to prove. 
 *
 */
public class BitonicArraySearchOptimized2 {

   private int[] a;
   
   public boolean left_search(int key, int lo, int hi)
   {
       // Invariant: a[lo] <= key < a[hi]
       while (lo < hi-1) {
           int mid = (lo + hi) / 2; 
           if (key < a[mid]) hi = mid;
           else lo = mid;
       }
       return (key == a[lo]);
   }
       
   public boolean right_search(int key, int lo, int hi)
   {
       // Invariant: a[lo] > key >= a[hi]
       while (lo < hi-1) {
           int mid = (lo + hi) / 2; 
           if (key < a[mid]) lo = mid;
           else hi = mid;
       }
       return (key == a[hi]);
   }

   public boolean bitonic_search(int key) {
       int lo = 0, hi = a.length-1;
       // Invariants: a is bitonic from lo..hi,
       //             key >= a[lo]. and
       //             key >= a[hi]
       while (lo < hi-1) {
           int mid = (lo + hi) / 2;
           if (key < a[mid]) {
               return left_search(key, lo, mid) ||
                      right_search(key, mid, hi);
           } else {
               if (a[mid] < a[mid+1]) lo = mid;
               else hi = mid;
           }
       } 
       return (key == a[lo]) || (key == a[hi]);
   } 
   
   public static void main(String[] args) {
      int[] array = {2, 3, 5, 7, 9, 11, 13, 4, 1, 0};
      BitonicArraySearchOptimized ba = new BitonicArraySearchOptimized(array);
      int low = 0;
      int high = array.length - 1;
      System.out.println(ba.bitonicSearch(low, high, -1));
      System.out.println(ba.bitonicSearch(low, high, 0));
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
