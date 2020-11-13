import java.util.LinkedList;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week02_2 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] num = input.readLine().split(" ");
        
        int k = Integer.parseInt(num[0]);
        int n = Integer.parseInt(num[1]);
        String[] arr = new String[k];
        int size = 0;
        
        LinkedList<String> list = new LinkedList<>();
        for(int i = 0; i < k; i++)
        {
            int temp = i + 1; 
            list.add(Integer.toString(temp));
        }
        ListIterator<String> it = list.listIterator();
        
        while(!list.isEmpty())
        {
            for(int i = 0; i < n; i++)
            {
                if(i >= n-1)
                {
                    if(it.hasNext())
                    {
                        arr[size] = it.next();
                        it.remove();
                        size++;
                    }
                    else
                    {
                        while(it.hasPrevious())
                            it.previous();
                        
                        arr[size] = list.get(0);
                        it.remove();
                        size++;
                    }
                }
                else
                {
                    if(it.hasNext())
                        it.next();
                    else
                    {
                        while(it.hasPrevious())
                            it.previous();
                        it.next();
                    }
                }    
            }            
        }
        output.write("<");
        for(int i = 0; i < arr.length; i++)
        {
            if(i != arr.length - 1)
                output.write(arr[i] + ", ");
            else
                output.write(arr[i]);
        }
        output.write(">");
        input.close();
		output.close(); 
    }
    
}
