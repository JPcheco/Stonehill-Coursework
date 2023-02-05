//Jacob Pacheco
//CSC 104-A
//Assignment 7.1.5

public class BinarySearch
{
   public BinarySearch()
   {

   }
 
   public static int search(Comparable [] x , int n, Comparable key)
   // pre-condition: x is a sorted array of n objecta ; key has an integer value
   // x is sorted in ascending order;
   // returns: the position of key in x or -1 if key is not found
   {
      int lo = 0; // lowest index of the array
      int hi = n-1; // highest index
      int mid; // middle index
      while (hi >= lo)
      {
         mid = (hi + lo)/2; // get the middle index
         if (x[mid].compareTo(key) == 0 )
            return mid; //key found --exit
      
         if ( x[mid].compareTo(key) > 0 )
            hi = mid - 1; //eliminate x[mid] thru x[hi]
         else
            lo = mid + 1; // eliminate x[lo] thru x[mid]
      }
      return -1; // key not found
   } 

//   @Override
//   public int equals(Object o)
//   {
//      return 0;
//   }
   
//   @Override  
//   public int compareTo (Object o)
//   {
//      return 0;
//   }
}