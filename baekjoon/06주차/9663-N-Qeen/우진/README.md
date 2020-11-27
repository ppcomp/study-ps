# 9663 - N_Queen

## 1. 개요

[https://www.acmicpc.net/problem/9663](https://www.acmicpc.net/problem/9663)

## 2. 과정

백트래킹에 대한 감이 안잡혀서 관련 자료를 찾아보는데 기본 예제로 9663번문제가 계속나와서 참고하여 해결함.

[https://medium.com/@jeongdowon/알고리즘-backtracking-이해하기-13492b18bfa1](https://medium.com/@jeongdowon/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-backtracking-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-13492b18bfa1) 참고

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    static int num;
    static int count = 0;
    static int[] arr;
    static boolean[] visit;
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        num = Integer.parseInt(input.readLine());
        arr = new int[num];
        nQueen(0);
        System.out.println(count);
    }

    public static void nQueen(int deep) {
		if (deep == num) {
			count++;
			return;
		}
 
		for (int i = 0; i < num; i++) {
            arr[deep] = i;
            boolean flag = true;
            for (int j = 0; j < deep; j++) 
            {
                if (arr[deep] == arr[j])
                    flag = false;
                else if (Math.abs(deep - j) == Math.abs(arr[deep] - arr[j]))
                    flag = false;
            }
			if (flag)
				nQueen(deep + 1);
		}
 
	}
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433987-2f0aa880-30df-11eb-89ba-d9a1ff96250b.png)