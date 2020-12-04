import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week07_2 {
    static int N, S;
    static String[] arr;
    static int count = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] line1 = input.readLine().split(" ");
        N = Integer.parseInt(line1[0]);
        S = Integer.parseInt(line1[1]);
        arr = input.readLine().split(" ");
        func(0, 0);
        System.out.println(count);
    }
    public static void func(int i, int sum)
    {
        if(i == N)
            return;
            
        if(sum == S)
        {
	        count++;
	        return;
        }
        func(i + 1, sum + Integer.parseInt(arr[i]));
        // if(i >= N)
        //     return;
        // sum += Integer.parseInt(arr[i]);
        // if(sum == S)
        //     count++;       
        // func(i + 1, sum - Integer.parseInt(arr[i]));
        // func(i + 1, sum);
    }
}
