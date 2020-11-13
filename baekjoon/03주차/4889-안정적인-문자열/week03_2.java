import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week03_2 {
    public static void main(String[] args)throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = 1;
        while(true)
        {
            Stack stack = new Stack();
			String[] str = input.readLine().split("");
            int count = 0;
			if (str[0].equals("-"))
				break;

            for (int i = 0; i < str.length; i++) 
            {
                if(stack.isEmpty() && str[i].equals("}"))
                {
                    count++;
                    stack.push("x");      
                }
                else if(str[i].equals("{"))
                    stack.push("x");
                else if(str[i].equals("}"))
                    stack.pop();
            }
            
            if(stack.size() != 0)
                count = count + (stack.size() / 2);
            
            output.write(k + ". " + count + "\n");
            k++;
        }
        input.close();
        output.flush();
		output.close(); 
    }
}