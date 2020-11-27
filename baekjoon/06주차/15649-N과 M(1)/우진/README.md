# 15649 - N과 M (1)

## 1. 개요

[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)

## 2. 과정

9466 - 텀 프로젝트 문제에서 사용한 깊이우선탐색을 활용하여 해결함.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] line = input.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int[] visit = new int[N];
        int[] num = new int[M];
        dfs(N, M, 0, visit, num);
        input.close();
        output.flush();
        output.close(); 
    }
    
    static void dfs(int N, int M, int deep, int[] visit, int[] num)
    {
        if(deep == M)
        {
            for(int i =0; i < M; i++)
                System.out.print(num[i] + " ");
            System.out.println();
            return;
        }
        for(int i = 0; i < N; i++)
        {
            if(visit[i] != 1)
            {
                visit[i] = 1;
                num[deep] = i + 1;
                dfs(N, M, deep+1, visit, num);
                visit[i] = 0;
            }
        } 
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433868-0c788f80-30df-11eb-8a41-1dd903fa1799.png)