# 15649 - N과 M(1)

---

## 개요

---

백트래킹의 기초적인 문제

자연수 N과 M이 주어질 때

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 출력한다.

## 코드

---

```java
import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon15649 {
    public static int n;
    public static int m;
    public static int[] seq;
    public static boolean[] checked;
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        seq = new int[m];
        checked = new boolean[n]; //중복확인
        
        dfs(0);
        bw.flush();
    }

    public static void dfs(int d) throws IOException {
        if(d == m) { //끝까지 탐색했을 때
            for(int i:seq) //배열에 담긴 수열 출력
                bw.write(i+" ");
            bw.newLine();
        }
        else {  //끝까지 탐색 못했을 때
            for(int i=0;i<n;i++) {
                if(checked[i] == false) {
                    checked[i] = true;  //현재 사용하는 숫자 true로 변경
                    seq[d] = i + 1;
                    dfs(d+1);   //한층 더 탐색

                    checked[i] = false; //d+1 탐색 끝나면 false로 변경
                }
            }
        }
    }
}
```

## 해결 과정

---

m개의 사이즈를 갖는 int배열을 만들고

1부터 N까지의 숫자의 중복여부를 체크하기 위한 N개의 사이즈를 갖는 boolean 배열을 만든다.

백트래킹을 위해 재귀방식으로 코드를 구현하였는데 중복을 체크하는 방법은 함수에 들어갈 때 사용한 숫자를 true로 설정하고 재귀가 끝나면 false로 바꾸는 방식이다.

```java
public static void dfs(int d) throws IOException {
        if(d == m) { //끝까지 탐색했을 때
            for(int i:seq) //배열에 담긴 수열 출력
                bw.write(i+" ");
            bw.newLine();
        }
        else {  //끝까지 탐색 못했을 때
            for(int i=0;i<n;i++) {
                if(checked[i] == false) {
                    checked[i] = true;  //현재 사용하는 숫자 true로 변경
                    seq[d] = i + 1;
                    dfs(d+1);   //한층 더 탐색

                    checked[i] = false; //d+1 탐색 끝나면 false로 변경
                }
            }
        }
    }
```

## 결과

---
![image](https://user-images.githubusercontent.com/47655983/100425770-28763400-30d3-11eb-906a-d8db05bd77cd.png)