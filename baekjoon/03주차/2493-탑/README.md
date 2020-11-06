# 2493 - 탑

## 1. 개요

[https://www.acmicpc.net/problem/2493](https://www.acmicpc.net/problem/2493)

## 2. 과정

### 1) 이중for문을 사용함

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
        int num = Integer.parseInt(input.readLine());
        String[] top = input.readLine().split(" ");
        int[] ray = new int[num];
        for(int i = 0; i < num; i++)
        {
            int temp = Integer.parseInt(top[i]);
            int count = 0;
            for(int j = i-1; j >= 0 ; j--)
            {
                if(temp < Integer.parseInt(top[j]))
                {
                    count = j + 1;
                    break;
                }
            }
            ray[i] = count;
        }
        for(int i = 0; i < num; i++)
            output.write(ray[i] + " ");
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/369fee1b-e421-41c6-a685-8934c64064b9/_2020_11_06_14_44_17_148.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/369fee1b-e421-41c6-a685-8934c64064b9/_2020_11_06_14_44_17_148.png)

### 2) 출력하는 for문을 없앰

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
        int num = Integer.parseInt(input.readLine());
        String[] top = input.readLine().split(" ");
        
        for(int i = 0; i < num; i++)
        {
            int temp = Integer.parseInt(top[i]);
            int count = 0;
            for(int j = i-1; j >= 0 ; j--)
            {
                if(temp < Integer.parseInt(top[j]))
                {
                    count = j + 1;
                    break;
                }
            }
            output.write(count + " ");
        }        
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/30e8e4c1-6b4c-4503-8d0d-015902e8f144/_2020_11_06_14_44_58_500.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/30e8e4c1-6b4c-4503-8d0d-015902e8f144/_2020_11_06_14_44_58_500.png)

[3) 블로그의 그림을 참고하여 스택을 사용함](https://www.notion.so/d448a9ae3fe6452b9a0dbe25074e76b1)

출처. [https://m.blog.naver.com/gkfla1017/221583283807](https://m.blog.naver.com/gkfla1017/221583283807)

```java
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week03_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(input.readLine());
        String[] top = input.readLine().split(" ");
        Stack<Integer> stack = new Stack();
        Stack<Integer> list = new Stack();
        int count = 0;
        stack.push(Integer.parseInt(top[count]));
        list.push(1);
        output.write("0 ");
        for(int i = 2; i <= num; i++)
        {
            count++;
            while(true)
            {
                if(!stack.isEmpty())
                {
                    if(stack.peek() < Integer.parseInt(top[count]))
                    {   
                        stack.pop();
                        list.pop();                            
                    }
                    else
                    {
                        output.write(list.peek() + " ");
                        stack.push(Integer.parseInt(top[count]));
                        list.push(i);
                        break;
                    }   
                }
                else
                {
                    output.write("0 ");
                    stack.push(Integer.parseInt(top[count]));
                    list.push(i);
                    break;
                }
            }
        }
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3a3e36f4-64db-4325-8531-f995fc8ef02b/_2020_11_06_15_08_39_511.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3a3e36f4-64db-4325-8531-f995fc8ef02b/_2020_11_06_15_08_39_511.png)

## 3. 결과

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/58827b5f-68f4-4da0-abaf-6ec46e346af5/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/58827b5f-68f4-4da0-abaf-6ec46e346af5/Untitled.png)