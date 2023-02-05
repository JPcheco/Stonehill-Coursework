//Jacob Pacheco
//CSC 104-A
//Assignment 6.1.3

public class TestIntegerSet
{
   public static void main(String[] args)
   {
      int[] list = {1,2,3,4,5,5,5};
      IntegerSet s = new IntegerSet(list,7);
      IntegerSet t = new IntegerSet(); //empty set
      
      for (int i = 10; i >= 3; i--)
         t.add(i);
         
      System.out.println("The elements of set s are ");
      System.out.println(s);
      System.out.println();
      System.out.println("The elements of set t are ");
      System.out.println(t);
      System.out.println();
      
      System.out.println("3 is an element of s "+ s.elementOf(3));
      System.out.println("9 is an element of s "+ s.elementOf(9));
      System.out.println();
      
      Object set1; // notice the type is Object
      set1 = s.union(t);
      System.out.println("The union of s and t : ");
      System.out.println(((IntegerSet)set1)); // notice the downcast
      System.out.println();
      
      int[] a1 = { 1,2,3,4,5,6,6,2};
      int[] b2 = { 2,3,6,3};
      IntegerSet a = new IntegerSet(a1,8);
      IntegerSet b = new IntegerSet(b2,4);
      System.out.println("Set a : "+ a);
      System.out.println("Set b : "+ b);
      System.out.println();
      
      Object set2;
      set2 = a.intersection(b);
      System.out.println("The intersection of a and b : ");
      System.out.println(((IntegerSet)set2));
      System.out.println();
      
      int [] list1 = {1,2,3,4,5,5};
      IntegerSet w = new IntegerSet(list1,6);
      System.out.println("s equals w: " +s.equals(w));
      System.out.println("s equals t: " +s.equals(t)); 
   }
}