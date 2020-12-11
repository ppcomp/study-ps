# 2293 - 동전1

## 1. 개요

[https://www.acmicpc.net/problem/2293](https://www.acmicpc.net/problem/2293)

## 2. 과정

이전 크기 동전까지만 사용한 경우와 해당동전까지 사용한 경우의 합을 구하면 해결

D = [i][j] = D[i-1][j] + D[i][j - i번째 동전크기]

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week08_1 {
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

### 3. 결과
![image](https://user-images.githubusercontent.com/32921283/101889308-9a29a400-3be2-11eb-9784-957fe257b5e6.png)