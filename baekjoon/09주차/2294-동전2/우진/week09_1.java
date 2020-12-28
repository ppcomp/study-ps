import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week09_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] coin = new int[n + 1];
        int[] dp = new int[k + 1];
        
        for(int i = 1; i <= n; i++)
            coin[i] = Integer.parseInt(input.readLine());  
        
        for(int i = 1; i <= k; i++)
            dp[i] = 10001;
        dp[0] = 0;
        for (int i = 1; i <= n; i++) 
        { 
            for (int j = coin[i]; j <= k; j++) 
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
        }
        if(dp[k] == 10001)
            System.out.println("-1");
        else
            System.out.println(dp[k]);
    }
}
