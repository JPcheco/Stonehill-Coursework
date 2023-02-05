public class GrowthRate1 {
public static void main(String[] args) {

//y = rx(1-x)
double x = 0.6;
double y;
double r = 3.57;

//Becomes chaotic @ 3.5799999999999983
   System.out.println("");
   for(int i = 0; i < 20; ++i) {
      y = (r*x)*(1-x);
      System.out.println(i + ": " + y);
      x = y;
   }//End inner for
   System.out.println("========================");

x = 0.61;
System.out.println("");
   for(int i = 0; i < 20; ++i) {
      y = (r*x)*(1-x);
      System.out.println(i + ": " + y);
      x = y;
   }//End inner for

}//End main
}//End class