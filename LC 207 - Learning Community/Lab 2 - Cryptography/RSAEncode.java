import java.util.Scanner;
public class RSAEncode
{

   public static int fastMod(int w,int e,int m)
   {
      int ans;
      if(e==1)
         return w;
      
         if(e%2==0)
         {
            int x=fastMod(w,e/2, m);
            return ((x*x) %m);
         }
         else 
         {
            int x= fastMod(w, e-1,m);
            return ((x*w) %m);
         }
  }


   public static void main(String[] args)
   {
      Scanner scan= new Scanner(System.in);
      
      System.out.println("Enter message w (int):");
      int w= scan.nextInt();
      System.out.println("Enter exponent e:");
      int e= scan.nextInt();
      System.out.println("Enter modular number:");
      int m= scan.nextInt();
      
       
       long  start =System.nanoTime();
      //fast mod
      int encoded= fastMod(w,e,m);
      long  fast =System.nanoTime()-start;
      System.out.println("Fast Mod: "+w+ " ^ "+e+ " mod "+m+" = "+ encoded +"  Time: "+ fast);   
           
           
      long  start1 =System.nanoTime();
      //slow way
      int encoded1 = 1;
      for(int i=0; i<e;i++)
      {
         encoded1=encoded1*w;
         encoded1= encoded1%m;
      }
      long  slow =System.nanoTime()-start1;

      System.out.println("Slow Mod: "+w+ " ^ "+e+ " mod "+m+" = "+ encoded1 +"  Time: "+ slow);
      
     

   }
   
   
   
   
   
   
}