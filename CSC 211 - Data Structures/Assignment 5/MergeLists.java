// Jacob Pacheco
// CSC 211
// Assingment 5.3.1

import java.util.*;

public class MergeLists extends LList<Integer>
{
   private LList<Integer> list1, list2, mergedList;
   
   public MergeLists() // constructor
   {
      list1 = new LList<Integer>();
      list2 = new LList<Integer>();
      
      mergedList = new LList<Integer>();
      mergedList.add(0); // a dummy first node
      
      list1.add(2);
      list1.add(3);
      list1.add(5);
      list1.add(8);
      list1.add(13);
      list1.add(27);
      list1.add(60);
      
      list2.add(4);
      list2.add(9);
      list2.add(11);
      list2.add(12);
      list2.add(15);
      
      merge();
   }
   
   public void merge()
   {
      // merges list1 and list2 into mergedList
      int length = list1.size() + list2.size() + 1; // total size of both lists combined
      int currentlength1 = list1.size(); // tracks current size of list1
      int currentlength2 = list2.size(); // tracks current size of list2
      
      int list1Index = 0;
      int list2Index = 0;
      
      for(int i = 0; i < length; i++)
      {
         if(currentlength1 == 0) // checks to see if list1 is empty and dumps the remaining into mergedlist
         {
            for(int a = list2Index; a < list2.size(); a++)
            {
               mergedList.add(list2.get(a));
               list2Index++;
            }
            return;
         }
         else if(currentlength2 == 0) // checks to see if list2 is empty and dumps the remaining into mergedlist
         {
            for(int a = list1Index; a < list1.size(); a++)
            {
               mergedList.add(list1.get(a));
               list1Index++;
            }
            return;
         }
         else
         {
            if(list1.get(list1Index) <= list2.get(list2Index)) // if list1 value < list2 value than add list 1 value onto queue
            {
               mergedList.add(list1.get(list1Index));
               currentlength1--;
               list1Index++;
            }
            else
            {
               mergedList.add(list2.get(list2Index));
               currentlength2--;
               list2Index++;
            }
         }
      }
   }
   
   public void display()
   {
      //prints the contents of mergedList
      System.out.print("Printing out the contents of mergedList... ");
      for(int i = 0; i < mergedList.size(); i++)
      {
         System.out.print(mergedList.get(i));
         System.out.print(" ");
      }
   }    
   
   public static void main(String[] args)
   {      
      MergeLists m = new MergeLists();
      m.display();
   }
}