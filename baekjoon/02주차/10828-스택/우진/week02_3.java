import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week02_3 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < num; i++)
        {
            String[] cmd = input.readLine().split(" ");
            if(cmd[0].equals("push"))
            {
                stack.push(cmd[1]);
            }
            else if(cmd[0].equals("pop"))
            {
                if(stack.empty())
                    output.write("-1\n");
                else
                    output.write(stack.pop() + "\n");
            }
            else if(cmd[0].equals("size"))
                output.write(stack.size() + "\n");           
            else if(cmd[0].equals("empty"))
            {
                if(stack.empty())
                    output.write("1\n");
                else
                    output.write("0\n");               
            }
            else if(cmd[0].equals("top"))
            {
                if(stack.empty())
                    output.write("-1\n");
                else
                    output.write(stack.peek() + "\n");
            }
        }
        input.close();
		output.close(); 
    }
}
