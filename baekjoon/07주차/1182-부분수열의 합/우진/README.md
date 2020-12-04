# 1182 - 부분수열의 합

## 1. 개요

[https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)

## 2. 과정

### 1) 단순하게 접근 tc = O

```java
if(i == N)
	return;
if(sum == S)
{
	count++;
	return;
}
func(i + 1, sum + Integer.parseInt(arr[i]));
```

### 2)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week07_2 {
    static int N, S;
    static String[] arr;
    static int count = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] line1 = input.readLine().split(" ");
        N = Integer.parseInt(line1[0]);
        S = Integer.parseInt(line1[1]);
        arr = input.readLine().split(" ");
        func(0, 0);
        System.out.println(count);
    }
    public static void func(int i, int sum)
    {
        if(i >= N)
            return;
        sum += Integer.parseInt(arr[i]);
        if(sum == S)
            count++;       
        func(i + 1, sum - Integer.parseInt(arr[i]));
        func(i + 1, sum);
    }
}
```

## 3. 결과
![캡처_2020_12_04_18_54_39_305](https://user-images.githubusercontent.com/32921283/101149762-b8335980-3662-11eb-9d8a-43c91d175288.png)
