public class Test
{
public static void main(String[] args)
{
Phrase phrase1 = new Phrase("mymymymy");
System.out.println(phrase1.findNthOccurence("at", 1));
System.out.println(phrase1.findNthOccurence("my",3));
System.out.println(phrase1.findNthOccurence("bat",2));
}
}