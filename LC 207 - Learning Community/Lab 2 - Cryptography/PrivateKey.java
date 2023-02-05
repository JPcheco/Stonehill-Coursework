import java.util.Scanner;
public class PrivateKey
{  

   public static boolean isPrime(int n) 
    {
        // Corner case
        if (n <= 1)
            return false;
  
        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }
    
   public static int cal(int p, int q)
   {
      return (p-1)*(q-1);
   }
   
   
   public static void main(String [] args)
   {
      Scanner scan= new Scanner(System.in);
      
      System.out.println("Enter exponent e:");
      int e= scan.nextInt();
      System.out.println("Enter modular number m:");
      int m= scan.nextInt();


       System.out.println("Public key: ("+e+", "+m+")");


      //break up m into p and q
       int p=0;
       int q=0;
      for(int i=2;i<7919;i++)//go until large number
      {
         //check if prime 
         if(isPrime(i))
         {
            //divide into m and see if remainder is 0 
            if(m%i==0 && isPrime(m/i))
            {
               p=i;
               q=m/i;
            }
         }
       }
      System.out.println("p: "+p);
      System.out.println("q: "+q);



     //calculate p-1 * q-1
     int m1=cal(p,q);     
     System.out.println("(p-1)*(q-1): "+ m1);

     
     //calculate inverse  
     GCD g= Euclids.GCDiv(e, m1);//using program 7 to calculate x,y, and reverses
     
     int d=g.x;
     //check if neg.
     if(d<0)
     {
      d +=m1;//adding trick 
     }
     
      System.out.println("Inverse: "+ d);
      
      System.out.println("Private key: ("+d+", "+m+")");
     
     
     
     System.out.println("Enter encrypted message (int): ");
     int message= scan.nextInt();
     
     int ans=  RSAEncode.fastMod(message,e,m);
     System.out.println("Decrypted message: "+ ans);
   }
   
}