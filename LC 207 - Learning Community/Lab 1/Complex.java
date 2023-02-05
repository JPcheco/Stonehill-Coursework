public class Complex
{
 private double  x;  // real part
 private double  y;  // imaginary part

 public Complex() // default constructor
 {
  x = y = 0;  //0 = 0 + 0i
 }

 public Complex (double real, double imaginary)
 {
  x = real;
  y = imaginary;
 }

 Complex add( Complex a)
 {
  double real = this.x + a.x;
  double imaginary = this.y + a.y;
  return new Complex(real, imaginary);
 }

 Complex sub( Complex a)
 {
  double real = this.x - a.x;
  double imaginary = this.y - a.y;
  return new Complex(real, imaginary);
 }

 Complex mul (Complex a)
 {
  double real = this.x*a.x - this.y*a.y;
  double imaginary = this.y *a.x + this.x*a.y;
  return new Complex(real, imaginary);
 }

 double abs()
 {
  return Math.sqrt( x*x + y*y);
 }

 double getReal()
 {
  return x;
 }
 double getImaginary()
 {
  return y;
 }

 public String toString()
 {
  if ( y > 0.0)
   return (x + " + "+ y + "i");
  else if (y <  0.0)
   return (x + " "+ y+"i");
  else return "0";

 }
 public static void main(String[] args)
 {
     Complex a = new Complex(1,2);
     Complex b = new Complex(3,4);
     Complex c = a.add(b);
     System.out.println(c);
     c = a.mul(b);
     System.out.println(c);
 }
}
