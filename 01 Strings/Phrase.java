/*int i=-1;
while(n>0)
{
i=currentPhrase.indexOf(str,i+1);
if(i<0)
return -1;
n--;
}
return i;*/

public class Phrase
{
private String currentPhrase;

public Phrase(String p)
{ currentPhrase=p; }
public int findNthOccurence(String str, int n)
{
int index=-1;
while(n!=0)
{
int b = currentPhrase.indexOf(str, index+1);
if(b<0)
return -1;
index=b;
n--;
}
return index;
}
public void replaceNthOccurence(String str, int n, String repl)
{
int t=findNthOccurence(str,n);
String begin = currentPhrase.substring(0,t);
String end = currentPhrase.substring(t+str.length());
currentPhrase=begin+repl+end;
}
}