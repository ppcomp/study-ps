## 1. 개요

[https://www.acmicpc.net/problem/2294](https://www.acmicpc.net/problem/2294)

## 2. 과정

### (1) 재귀를 사용한 방법 (시간초과)

```jsx
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
        int[] coin = new int[n];
        int[] dp = new int[k + 1];
        
        for(int i = 0; i < n; i++)
            coin[i] = Integer.parseInt(input.readLine());  
        
        for(int i = 0; i <= k; i++)
            dp[i] = 10001;
        dp[0] = 0;
        for (int i = 0; i < n; i++) 
        { 
            for (int j = coin[i]; j <= k; j++) 
                dp[j] = Math.min(dp[j], dp[j - coin[i] + 1]);
        }
        if(dp[k] == 10001)
            System.out.println("-1");
        else
            System.out.println(dp[k]);
    }
}
```

### (2) DP를 활용한 방법

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week09_01_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] coin = new int[n + 1];
        int[][] result = new int[n+1][k+1];
        
        for(int i = 1; i <= n; i++)
            coin[i] = Integer.parseInt(input.readLine());   
        result[0][0] = 1;     

        for(int i = 1; i <= n; i++)
        {
            for(int j = 0; j <= k; j++)
            {
                if(j >= coin[i])
                    result[i][j] = result[i-1][j] + result[i][j-coin[i]];
                
                else
                    result[i][j] = result[i-1][j];
            }
        }
        output.write(result[n][k] + "");
        output.flush();
        output.close();
        input.close();
    }
}
```

점화식: DP[j] = MIN(DP[j], DP[j -coin[i]] + 1)