import java.util.Deque;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week03_3 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        for(int i = 0; i < num; i++)
        {      
            String[] cmd = input.readLine().split("");
            int arrNum = Integer.parseInt(input.readLine());
            String str = input.readLine();
            str = str.substring(1, str.length()-1);
            String[] arr = str.split(",");
            Deque deque = new ArrayDeque();
            int reverse = 0, error = 0;
            for(int j = 0; j < arrNum; j++)
                deque.add(arr[j]);  
            for(int j = 0; j < cmd.length; j++)
            {
                if(cmd[j].equals("R"))
                {
                    if(reverse == 0)
                        reverse = 1;
                    else
                        reverse = 0;
                }
                else if(cmd[j].equals("D"))
                {
                    if(deque.isEmpty())
                    {
                        error = 1;
                        break;
                    }
                    else
                    {
                        if(reverse == 0)
                            deque.pollFirst();
                        else
                            deque.pollLast();
                    }
                }
            }
            if(error != 0)
                output.write("error\n");
            else{
                output.write("[");
                if(reverse == 0)
                {
                    if(!deque.isEmpty())
                        output.write(deque.pollFirst() +"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollFirst()+"");
                    }
                }
                else
                {
                    if(!deque.isEmpty())
                        output.write(deque.pollLast()+"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollLast()+"");
                    }    
                }
                output.write("]\n");
            }
        }
        output.flush();
		output.close(); 
        input.close();
        
    }
}
