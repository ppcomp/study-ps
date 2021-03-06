# 9663 - N-Queen

---

## 개요

---

크기가 N x N인 체스판 위에 퀸 N개를 서로 공격할 수 없는 위치에 두는 문제

## 코드

---

```java
import java.io.*;

public class Baekjoon9663 {
    public static int n;
    public static int count;
    public static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n];
        count = 0;
        
        for(int i=0;i<n;i++) {
            board[0] = i;
            queen(1);
        }

        bw.write(count+"");
        bw.flush();

    }

    public static boolean isPromising(int c) {
        for(int i=0;i<c;i++) {
            //같은 열에 위치하거나 같은 대각선상에 있으면 false
            if(board[i] == board[c] || Math.abs(i-c) == Math.abs(board[i]-board[c]))
                return false;
        }
        return true;
    }

    public static void queen(int r) {
        if(r == n) {
            count++;
            return;
        }
        for(int i=0;i<n;i++) {
            board[r] = i;
            if(isPromising(r)) {    //유효성 판단
                queen(r+1);
            }
        }
    }
}
```

## 해결 과정

---

백트래킹 방식으로 구현하였는데 일단 백트래킹 방식은 탐색을 할 때 탐색하는 노드의 유망성(Promising)을 따져서 유망하지 않으면 이전 단계로 돌아간다.

퀸의 적절한 위치를 찾는 함수 queen()안에서 유망성을 확인하는 함수 isPromising()를 호출해 다음 단계로 넘어갈지 선택하는 것이 메인이다.

유망한지 확인하는 방법은 같은 열에 위치하거나 같은 대각선 상에 없으면 퀸을 놓을 수 있으므로 이 조건에서 유망하다.

같은 대각선 상에 있는지 확인하는 법은 두 좌표의 행과 열을 각각 뺀 절대값이 같으면 대각선 상에 위치한다.

```java
public static boolean isPromising(int c) {
        for(int i=0;i<c;i++) {
            //같은 열에 위치하거나 같은 대각선상에 있으면 false
            if(board[i] == board[c] || Math.abs(i-c) == Math.abs(board[i]-board[c]))
                return false;
        }
        return true;
    }
```

## 결과

---
![image](https://user-images.githubusercontent.com/47655983/100427201-7429dd00-30d5-11eb-9ee6-4807e28006bf.png)