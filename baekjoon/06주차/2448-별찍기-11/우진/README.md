# 2448 - 별 찍기_11

## 1. 개요

[https://www.acmicpc.net/problem/2448](https://www.acmicpc.net/problem/2448)

## 2. 과정

반복되는 규칙을 찾아도 도저히 해결할 방법이 안보여서 [https://rightbellboy.tistory.com/39](https://rightbellboy.tistory.com/39) 참고함

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.Math;

public class week06_5 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(input.readLine());
        String[] star = new String[N];
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";
        for(int i = 1; 3 * (int)Math.pow(2, i) <= N; i++)
            makeStar(i, star);

        for(int i = 0; i < N; i++)
            System.out.println(star[i]);
        input.close();
        output.flush();
        output.close(); 
    }

    public static void makeStar(int num, String[] star)
    {
        int bottom = 3 * (int)Math.pow(2, num);
        int middle = bottom / 2;
         
        for (int i = middle; i < bottom; i++)
            star[i] = star[i - middle] + " " + star[i -middle];
        
        String space = "";
        while (space.length() < middle)
            space += " ";
        
        for (int i = 0; i < middle; ++i)
            star[i] = space + star[i] + space;
    }    
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100434253-8c065e80-30df-11eb-824a-bc0cfa6e7f3c.png)