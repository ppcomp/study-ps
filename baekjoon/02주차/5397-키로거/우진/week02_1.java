import java.util.LinkedList;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week02_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        for(int i = 0; i < num; i++)
        {
            String pw = input.readLine();      
            LinkedList<String> list = new LinkedList<>();
            ListIterator<String> it = list.listIterator();
            
            for(int j = 0; j < pw.length(); j++)
            {
                if(Character.toString(pw.charAt(j)).equals("<"))
                { 
                    if(it.hasPrevious())
                        it.previous();
                }
                else if(Character.toString(pw.charAt(j)).equals(">"))
                {  
                    if(it.hasNext())
                        it.next();
                }
                else if(Character.toString(pw.charAt(j)).equals("-"))
                {
                    if(it.hasPrevious())      
                    {   
                        it.previous();
                        it.remove();  
                    }           
                }
                else
                    it.add(Character.toString(pw.charAt(j)));                
            }
            ListIterator<String> iter = list.listIterator();
            while(iter.hasNext())
                output.write(iter.next()); 
            output.newLine();         
        }
        input.close();
		output.close(); 
    }
}
