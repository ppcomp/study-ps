import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week03_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        String[] top = input.readLine().split(" ");
        Stack<Integer> stack = new Stack();
        Stack<Integer> list = new Stack();
        int count = 0;
        stack.push(Integer.parseInt(top[count]));
        list.push(1);
        output.write("0 ");
        for(int i = 2; i <= num; i++)
        {
            count++;
            while(true)
            {
                if(!stack.isEmpty())
                {
                    if(stack.peek() < Integer.parseInt(top[count]))
                    {   
                        stack.pop();
                        list.pop();                            
                    }
                    else
                    {
                        output.write(list.peek() + " ");
                        stack.push(Integer.parseInt(top[count]));
                        list.push(i);
                        break;
                    }   
                }
                else
                {
                    output.write("0 ");
                    stack.push(Integer.parseInt(top[count]));
                    list.push(i);
                    break;
                }
            }
        }
        input.close();
        output.flush();
		output.close(); 
    }
}