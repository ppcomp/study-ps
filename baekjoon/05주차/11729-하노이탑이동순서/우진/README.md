# 11729 - 하노이 탑 이동 순서

## 1. 개요

[https://www.acmicpc.net/problem/11729](https://www.acmicpc.net/problem/11729)

## 2. 과정

알고리즘 수업중 외운 내용으로 해결함.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static int count = 0;
    static ArrayList<String> list = new ArrayList();
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(input.readLine());
        hanoi("1", "2", "3", n);
        output.write(count + "");
        
        for(int i = 0; i < list.size(); i++)
        {
            if(i % 2 == 0)
                output.newLine();
            output.write(list.get(i) + " ");
        }
        input.close();
        output.flush();
        output.close(); 
    }

    public static void hanoi(String from, String spare, String to, int ndisk){
        if(ndisk == 1)
        {
            list.add(from);
            list.add(to);
            count++;
        }
        else{
            hanoi(from, to, spare, ndisk-1);
            list.add(from);
            list.add(to);
            count++;
            hanoi(spare, from, to, ndisk-1);
        }
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433607-a55adb00-30de-11eb-9036-022dd1577aa6.png)