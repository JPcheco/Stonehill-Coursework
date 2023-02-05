public class GrowthRate {
public static void main(String[] args) {

//y = rx(1-x)
double x = 0.5;
double y;

//Becomes chaotic @ 3.5799999999999983
for(double r = 3.5; r < 3.6; r += 0.01) {
   System.out.println("");
   System.out.println("r: " + r);
   for(int i = 0; i < 20; ++i) {
      y = (r*x)*(1-x);
      System.out.println(i + ": " + y);
      x = y;
   }//End inner for
   System.out.println("========================");
}//End outer for


}//End main
}//End class