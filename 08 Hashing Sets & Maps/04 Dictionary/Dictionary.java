// Name: Shivani Puli
// Date: 3/13/19

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      TreeMap<String, Set<String>> dict=new TreeMap<String, Set<String>>();
      while(infile.hasNext())
         add(dict,infile.next(),infile.next());
      return dict;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      Set<String> t;
      if(dictionary.containsKey(word))
         t=dictionary.get(word);
      else
         t=new TreeSet<String>();
      t.add(translation);
      dictionary.put(word,t);
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      for(String key : m.keySet())
         System.out.println(key + m.get(key));
      System.out.println();
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> rev=new TreeMap<String, Set<String>>();
      for(String key : dictionary.keySet())
      {
         Iterator<String> it= dictionary.get(key).iterator();
         while(it.hasNext())
            add(rev,it.next(),key);
      }
      return rev;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/