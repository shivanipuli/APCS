//name:Shivani Puli      date:9/12/18  
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
     /*String[] original = {"--??pig","latin","this","strange","bcdfg","apple","eye","question","squeeze","yes","rhyme","try","Thomas","Jefferson","McDonald","Arthurmcay","BUBBLES","What?","Oh!","\"hello\"","\"Hello????\""};
     String[] correct = {"ig--??pay", "atinlay","isthay","angestray","**** NO VOWEL ****","appleway","eyeway","estionquay","eezesquay","esyay","ymerhay","ytray","Omasthay","Effersonjay","OnaldmcDay","Arthurmcay","UBBLESbay","Atwhay?","Ohway!","\"ellohay\"","\"Ellohay????\"","on'tday","ell-mellpay"};
   
   for(int x=0; x<original.length; x++)
   {
   if(pig(original[x]).equals(correct[x]))
   System.out.print("true \t");
   else
   System.out.print("false \t");
   System.out.println(pig(original[x])+ "\t" + correct[x]);
   }*/
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
   }
 
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }     
   }
   public static String pig(String s)
   {
      //enter your code here
      int firstv;
      String output="";
      boolean caps=isCaps(s);
      boolean quotes=false;
      String punct="";
   
      if(s.charAt(0)=='"')
      {
         quotes=true;
         s=s.substring(1,s.length()-1);
      }
      while(s.charAt(s.length()-1)=='"'||s.charAt(s.length()-1)=='!'||s.charAt(s.length()-1)=='?'||s.charAt(s.length()-1)=='.'||s.charAt(s.length()-1)==','||s.charAt(s.length()-1)==':'||s.charAt(s.length()-1)==';')
      {
         punct=s.charAt(s.length()-1)+punct;
         s=s.substring(0,s.length()-1);
      }
   
      firstv=findFirst(s);
   
      if(caps)
         s=Character.toLowerCase(s.charAt(0))+s.substring(1);
   
      if(firstv==-1)
         output="**** NO VOWEL ****";
      else if(firstv==0&&s.charAt(0)=='y')
         output=yFirst(s);
      else if(firstv==0)
         output=vowelPig(s);
      else if(s.charAt(firstv)=='u')
         output=uPig(s,firstv);
      else
         output=normalPig(s,firstv);
   
      //output=pigReverse(output);
   
      if(caps)
         output=Character.toUpperCase(output.charAt(0))+output.substring(1);
       
      output=fixPunct(output,punct,quotes);
   
      return output;
   }
   public static String fixPunct(String s, String p, boolean q)
   {
      s+=p;
      if(q)
         s='"'+s+'"';
      return s;
   }
   public static String yFirst(String p)
   {
      int t=findFirst(p.substring(1))+1;
      return normalPig(p, t);
   }
   public static boolean isCaps(String b)
   {
      boolean f=false;
      for(int x=0; x < b.length(); x++)
      {
         if(Character.isUpperCase(b.charAt(x)))
         {
            f=true;
            break;
         }
      }
      return f;
   }
   public static String normalPig(String g,int first)
   {
      return g.substring(first)+g.substring(0,first)+"ay";
   }
   public static String uPig(String g, int first)
   {
      String f;
      if(g.charAt(first-1)=='q')
         f=g.substring(first+1)+g.substring(0,first+1)+"ay";
      else
         f=normalPig(g,first);
      return f;
   }
   public static String vowelPig(String g)
   {
      return (g+"way");
   }
   public static boolean isVowel(char first)
   {
      if(first=='a'||first=='e'||first=='i'||first=='o'||first=='u'||first=='y'||first=='A'||first=='E'||first=='I'||first=='O'||first=='U'||first=='Y')
         return true;
      else
         return false;
   }
   public static int findFirst(String l)
   {
      int d=-1;
      for(int x=0; x<l.length();x++)
         if(isVowel(l.charAt(x)))
         {
            d=x;
            break;
         }
      return d;
   }
 
   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }
   public static String pigReverse(String m)
   {
      String temp="";
      for(int x=m.length()-1; x>=0; x--)
      {
         temp=temp+String.valueOf(m.charAt(x));
      }
      return temp;
   }
/****************************** 
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   
      /*  Enter your code here.  Try to preserve lines and paragraphs. ***/
      infile.useDelimiter("\\Z");
      String[] array=infile.next().split("\\r\\n");
   
      for(int x=0; x<array.length;x++)
      {
         if(!array[x].isEmpty())
         {
            String arr[]=array[x].split(" ");
            array[x]="";
            for(int y=0; y<arr.length;y++)
            {
               arr[y]=pig(arr[y]);
               array[x]+=arr[y]+" ";
            }
         }
      }
   
      for(int x=0; x<array.length; x++)
         outfile.println(array[x]);
   
   
   
   
      outfile.close();
      infile.close();
   }
}
