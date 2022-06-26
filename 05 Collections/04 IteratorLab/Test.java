import java.io.*;
import java.util.*;

public class Test
{
public static void main(String[] args)
{

ArrayList<String> theList = new ArrayList(5);
theList.add("A");
theList.add("B");
theList.add("C");
theList.add("D");
theList.add("E");
    	ListIterator<String> it = theList.listIterator(); 
      it.next();
      it.next();
      it.remove();
      it.next();
      it.add("X");
      
ArrayList<String> myList = new ArrayList(2);
myList.add("A");
myList.add("B");
      ListIterator<String> lsit = myList.listIterator();
lsit.add("X");
lsit.next();
lsit.add("Y");
lsit.next();
lsit.add("Z");
lsit.next();
}
}