// name:Shivani Puli    date:9/16/18
import java.util.*;

public class LogMessageTest
{
   public static void main(String[] args)
   {
      String[] messages = {
         "CLIENT3:security alert - repeated login failures",
         "Webserver:disk offline",
         "SERVER1:file not found",
         "SERVER2:read error on disk DSK1",
         "SERVER1:write error on disk DSK2",
         "Webserver:error on /dev/disk",
         "True:disk",
         "True:error on disk",
         "True:error on /dev/disk disk",
         "True:error on disk DSK1",
         "False:DISK",
         "False:error on disk3",
         "False:error on /dev/disk",
         "False:diskette"};
   
    // Parts A and B
      for (String s : messages)
      {
         LogMessage msg = new LogMessage(s);
         System.out.println(msg.getMachineId() + ":" + msg.getDescription() + " ==> " + msg.containsWord("disk"));
      }
    
   // Part C
      SystemLog theLog  = new SystemLog(messages);
      LogMessage[] removed = theLog.removeMessages("disk");
        
      System.out.println();
    
      System.out.println("Removed messages:\n");
      for (LogMessage msg : removed)
         System.out.println(msg);
      System.out.println();
    
      System.out.println("Remaining messages:\n");
      System.out.println(theLog);
    
   }
}

class LogMessage
{
   private String machineId;
   private String description;

   /* Part (a) */
   public LogMessage(String message)
   {
      int t = message.indexOf(":");
      machineId=message.substring(0,t);
      description=message.substring(t+1);  
   }

   /* Part (b) */
   public boolean containsWord(String keyword)
   {
      //return (" " + description + " ").contains(" " + keyword + " ");
      int index=description.indexOf(keyword);
      int lastI=index+keyword.length();
      while(index!=-1)
      {
         if(index==0)
         {
            if(lastI==description.length())
               return true;
            else if(description.charAt(lastI)==' ')
               return true;
         }
         else if(description.charAt(index-1)==' ' )
         {
            if(lastI==description.length())
               return true;
            else if(description.charAt(lastI)==' ')
               return true;
         }
         index=description.indexOf(keyword,lastI);
         lastI=index+keyword.length();
         //return containsWord(keyword);
      }
      return false;
   }

   public String getMachineId()
   { 
      return machineId; 
   }

   public String getDescription()
   { 
      return description; 
   }

   public String toString()
   {
      return getMachineId() + ":" + getDescription();
   }
}

class SystemLog
{
   private LogMessage[] messageList;

   public SystemLog(String[] messages)
   {
      messageList = new LogMessage[messages.length];
      for (int i=0;i<messages.length; i++)
         messageList[i]=(new LogMessage(messages[i]));
   }

  /* Part (c) */

   public LogMessage[] removeMessages(String keyword)
   {
      String remove="";
      String keep="";
      for(int x=0; x < messageList.length; x++)
      {
         if(messageList[x].containsWord(keyword))
            remove+= messageList[x].toString()+"&";
         else
            keep+=messageList[x].toString() + "&";
      }
      String[] r= remove.split("&");
      String[] k=keep.split("&");
      LogMessage[] array=new LogMessage[r.length];
      for(int x=0; x<r.length; x++)
      {
         array[x]=new LogMessage(r[x]);
      }
      LogMessage[] arr=new LogMessage[k.length];
      for(int x=0; x<k.length; x++)
      {
         arr[x]=new LogMessage(k[x]);
      }
   
      messageList= arr;
      return array;       
       
   }    


   public String toString()
   {
      String s = "";
      for (LogMessage msg : messageList)
         s += msg + "\n";
      return s;
   }
}

 /**************** Sample output:

   // Parts a and b   

 CLIENT3:security alert - repeated login failures ==> false
 Webserver:disk offline ==> true
 SERVER1:file not found ==> false
 SERVER2:read error on disk DSK1 ==> true
 SERVER1:write error on disk DSK2 ==> true
 Webserver:error on /dev/disk ==> false
 True:disk ==> true
 True:error on disk ==> true
 True:error on /dev/disk disk ==> true
 True:error on disk DSK1 ==> true
 False:DISK ==> false
 False:error on disk3 ==> false
 False:error on /dev/disk ==> false
 False:diskette ==> false
 
 
 // Part c  
 
 Removed messages:
 
 Webserver:disk offline
 SERVER2:read error on disk DSK1
 SERVER1:write error on disk DSK2
 True:disk
 True:error on disk
 True:error on /dev/disk disk
 True:error on disk DSK1
 
 Remaining messages:
 
 CLIENT3:security alert - repeated login failures
 SERVER1:file not found
 Webserver:error on /dev/disk
 False:DISK
 False:error on disk3
 False:error on /dev/disk
 False:diskette
 
 ********************************************/    
 /**********************************************/
