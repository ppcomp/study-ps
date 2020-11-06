import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
public class week02_4 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        Queue<String> queue = new LinkedList<>();
        int back = -1;
        for(int i = 0; i < num; i++)
        {
            String[] cmd = input.readLine().split(" ");
            if(cmd[0].equals("push"))
            {
                queue.offer(cmd[1]);
                back = Integer.parseInt(cmd[1]);
            }
            else if(cmd[0].equals("pop"))
            {
                if(queue.isEmpty())
                {
                    output.write("-1\n");
                    back = -1;
                }
                else
                    output.write(queue.poll() + "\n");
            }
            else if(cmd[0].equals("size"))
                output.write(queue.size() + "\n");
            else if(cmd[0].equals("empty"))
            {
                if(queue.isEmpty())
                    output.write("1\n");
                else
                    output.write("0\n");               
            }
            else if(cmd[0].equals("front"))
            {
                if(queue.isEmpty())
                    output.write("-1\n");
                else
                    output.write(queue.peek() + "\n");
            }
            else if(cmd[0].equals("back"))
            {
                if(queue.isEmpty())
                    output.write("-1\n");
                else
                    output.write(back + "\n");
            }
        }
        input.close();
		output.close(); 
    }
}
