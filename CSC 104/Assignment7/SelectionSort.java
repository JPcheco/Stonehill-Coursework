public class SelectionSort
{ 
   public static void sort(Comparable[] x, int size)
   {
      Comparable max; // the data stored in x[]
      int maxIndex; // an index is an int
      for (int i=size-1; i>=1; i--)
      {
         // Find the maximum in the x[0..i]
         max = x[i]; // the "current" maximum is x[i]
         maxIndex = i; // index of "current" max
         
         for (int j=i-1; j>=0; j--) 
         {
            if (max.compareTo( x[j]) <0) // max < x[i]
            {
               max = x[j]; // a "new" maximum
               maxIndex = j;
            }
         }
         
         if (maxIndex != i) 
          // place the maximum in its proper position
         {
            x[maxIndex] = x[i];
            x[i] = max;
         }
      }
   }
}