# 9466 - 텀 프로젝트

## 1. 개요

[https://www.acmicpc.net/problem/9466](https://www.acmicpc.net/problem/9466)

## 2. 과정

깊이우선탐색문제라서 너비우선탐색처럼 Queue대신 Stack 넣고 풀어봤는데 해결이 안보여서 찾아보다가 재귀DFS로 해결함.

[https://bcp0109.tistory.com/32](https://bcp0109.tistory.com/32) 참고

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(input.readLine());
        int cnt = 0;
        while(cnt < tc){
            int n = Integer.parseInt(input.readLine());
            int[] arr = new int[n+1];
            int[] visit = new int[n+1];
            int[] finish = new int[n+1];
            count = 0;
            String[] strNum = input.readLine().split(" ");
            
            for(int i=1; i<n+1; i++) 
                arr[i] = Integer.parseInt(strNum[i-1]);
            
            for(int i=1; i<n+1; i++)
                dfs(i, arr, visit, finish);
            
            System.out.println(n - count);

            cnt++;
        }
        input.close();
    }

    static void dfs(int now, int[] arr, int[] visit, int[] finish) { 
        if(visit[now] == 1)
            return;
        
            visit[now] = 1;
        int next = arr[now];
        
        if(visit[next] != 1)
            dfs(next, arr, visit, finish);
        else {
            if(finish[next] != 1) {
                count++;
                for(int i=next; i != now; i = arr[i])
                    count++;
            }
        }
        finish[now] = 1;
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433709-ca4f4e00-30de-11eb-9a3b-eb6119a25376.png)