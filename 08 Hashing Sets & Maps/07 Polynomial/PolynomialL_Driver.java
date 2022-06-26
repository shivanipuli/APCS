// Name: Saahil Singh
 // Date: 3/18/19

import java.util.*;

public class PolynomialL_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial();   // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map:  " + poly3.getMap());
      // System.out.println("String:  " + poly3.toString());
   //       
      // Polynomial poly4 = new Polynomial();    // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map:  " + poly4.getMap());
      // System.out.println("String:  " + poly4.toString());
      // System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      // System.out.println("==========================="); 
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map:  " + poly5.getMap());  
      // System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> treem;
   public Polynomial()
   {
      treem = new TreeMap<Integer, Integer>();
   }
   public void makeTerm(Integer exp, Integer coef)
   {
      treem.put(exp,coef);    
   }
   public Map<Integer, Integer> getMap(){
      return treem; 
   }
   public double evaluateAt(double x){//evaluate x to power then times coefficient
      double total = 0.0; 
      for(int s : treem.keySet()){
         total += Math.pow(x, s) * treem.get(s);
      }
      return total;
   }
   public Polynomial add(Polynomial other){//adds two polynomials
      Polynomial temp = new Polynomial();
      for(int i : treem.keySet()){
         temp.makeTerm(i, treem.get(i));
      }
      for(int a : other.getMap().keySet()){
         if(temp.getMap().containsKey(a)){
            temp.makeTerm(a, other.getMap().get(a) + temp.getMap().get(a));
         }
         else
            temp.makeTerm(a, other.getMap().get(a));
      }
      return temp;
   }
   public Polynomial multiply(Polynomial other){//multiplies two polynomials
      Polynomial temp = new Polynomial();
      for(int i : treem.keySet()){ //two for loops multiply each term of poly1 with each term of poly2
      for(int a : other.getMap().keySet()){
      if(temp.getMap().isEmpty()) 
      temp.makeTerm(a + i, other.getMap().get(a) * treem.get(i)); //when multiplying two terms, coefficients  
      else if(temp.getMap().containsKey(a+i))                  // get multiplied and exponents are added         ex. 2x^6 * 4x^3 = 8x^9
      temp.makeTerm(a+i, other.getMap().get(a) * treem.get(i) + temp.getMap().get(a+i) ); //if temp already contains a number, 
      else                                                              //just add the coefficiants of the new term and the previous term
      temp.makeTerm(a + i, other.getMap().get(a) * treem.get(i));
      }
      }
      return temp;
   }
   public String toString(){//to string lol
      String complete = "";
      for(int i: treem.keySet()){
         if(i > 1){
            if(treem.get(i) == 1)
               complete = "x" + "^" + i + " + " + complete;
            else
               complete = treem.get(i) + "x" + "^" + i + " + " + complete;
         }
         if(i == 1)
            if(treem.get(i) == 1)
               complete = "x " + "+ " + complete;
            else
               complete = treem.get(i) + "x " + "+ " + complete;
         if(i == 0)
            complete = treem.get(i) + "";
      }
      
      return complete.substring(0,complete.length());
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/