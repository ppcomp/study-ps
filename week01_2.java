import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class week01_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String num = sc.nextLine();
        int linenum = line.length();
        if(linenum <= 100000 && 1 <= Integer.parseInt(num) && Integer.parseInt(num) <= 500000)
        {
            LinkedList<String> list = new LinkedList<String>();
            for(int i = 0; i < linenum; i++)            
                list.add(Character.toString(line.charAt(i)));           
            
            ListIterator<String> it = list.listIterator();    
            while(it.hasNext())         
                it.next();         

            for(int i = 0; i < Integer.parseInt(num); i++)  
            {
                String[] cmd = sc.nextLine().split(" ");
                if(cmd[0].equals("L"))
                {
                    if(it.hasPrevious())
                        it.previous();
                }

                else if(cmd[0].equals("D"))
                {
                    if(it.hasNext())
                        it.next();
                }

                else if(cmd[0].equals("P"))
                    it.add(cmd[1]);
                
            
                else if(cmd[0].equals("B"))
                {
                    if(it.hasPrevious())
                    {
                        it.previous();
                        it.remove();
                    }
                }
            }
            for(String i : list)        
                System.out.print(i); 
            
        }
        sc.close();     
    }
}
