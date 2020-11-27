# 1074 - Z

## 1. 개요

[https://www.acmicpc.net/problem/1074](https://www.acmicpc.net/problem/1074)

## 2. 과정

처음 사분면을 나워야하는곳에서 계속 계산오류가 생기는것 같아

[https://m.blog.naver.com/PostView.nhn?blogId=puri8467&logNo=221442162808&proxyReferer=https:%2F%2Fwww.google.com%2F](https://m.blog.naver.com/PostView.nhn?blogId=puri8467&logNo=221442162808&proxyReferer=https:%2F%2Fwww.google.com%2F) 참고하여 찾아야하는 좌표가 마지막 한칸이 될때까지 배열을 나눠 해결함.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.Math;

public class Main {
    static int count = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int r = Integer.parseInt(line[1]);
        int c = Integer.parseInt(line[2]);
        Z(N, c, r);
        System.out.print(count);
        input.close();
        output.flush();
        output.close(); 
    }
    public static void Z(int n, int row, int col)
    {
        if(n == 0)
            return;
        int size = (int)Math.pow(2, n);
        int full = size * size;
        int half = size / 2;
        
        if(row < half && col < half)
            Z(n-1, row, col);
        else if(row >= half && col < half){
            count += (full / 4);
            Z(n-1, row - half, col);
        }
        else if(row < half && col >= half){
            count += (full / 4) * 2;
            Z(n-1, row, col - half);
        }
        else if(row >= half && col >= half){
            count += (full / 4) * 3;
            Z(n-1, row - half, col - half);
        }
    }
}
```

## 3. 과정
![image](https://user-images.githubusercontent.com/32921283/100434126-624d3780-30df-11eb-96ef-df17405ee09d.png)