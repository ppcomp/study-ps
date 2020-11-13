# 4889 - 안정적인 문자열

## 1. 개요

[https://www.acmicpc.net/problem/4889](https://www.acmicpc.net/problem/4889)

## 2. 과정

### 1) 단순하게 왼쪽괄호, 오른쪽 괄호 카운트해서 2로 나눔

```java
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = 1;
        while(true)
        {
						String[] str = input.readLine().split("");
            int count = 0;
            int left = 0;
            int right = 0;
						int temp = 0;
						if (str[0].equals("-"))
							break;

            for (int i = 0; i < str.length; i++) 
            {
                if(i == 0 && str[i].equals("}"))
                    count++;
                else if(i == str.length - 1 && str[i].equals("{"))
                    count++;
                else if(str[i].equals("{") && i != 0 && i != str.length - 1)
                    left++;
                else if(str[i].equals("}") && i != 0 && i != str.length - 1)
                    right++;
            }
            temp = left - right;
            if(temp != 0)
            {
                if(temp > 0)
                    count = count + (temp / 2);
                else
                    count = count + (-temp / 2);
            }
            output.write(k + ". " + count + "\n");
            k++;
        }
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c2e83ff4-1d45-4d4c-9a59-b74eb097d507/_2020_11_06_14_57_50_343.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c2e83ff4-1d45-4d4c-9a59-b74eb097d507/_2020_11_06_14_57_50_343.png)

### 2) 스택을 사용하여 '{' 가 입력되면 push, '}'가 입력되면 pop을 함

```java
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = 1;
        while(true)
        {
            Stack stack = new Stack();
						String[] str = input.readLine().split("");
            int count = 0;
						if (str[0].equals("-"))
							break;

            for (int i = 0; i < str.length; i++) 
            {
                if(i == 0 && str[i].equals("}"))
                    count++;         
                else if(str[i].equals("{"))
                    stack.push("x");
                else if(str[i].equals("}"))
                    stack.pop();
            }
            if(stack.size() % 2 != 0)
                stack.push("x");
            count = count + (stack.size() / 2);
            output.write(k + ". " + count + "\n");
            k++;
        }
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e39dc0c6-f7ec-45fa-83e2-cce6276497fd/_2020_11_06_14_59_49_200.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e39dc0c6-f7ec-45fa-83e2-cce6276497fd/_2020_11_06_14_59_49_200.png)

### 3) 혹시 0을 나누는것이 문제될까 싶어 stack.size가 0이 아닐때만 나눔

```java
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = 1;
        while(true)
        {
            Stack stack = new Stack();
						String[] str = input.readLine().split("");
            int count = 0;
						if (str[0].equals("-"))
								break;

            for (int i = 0; i < str.length; i++) 
            {
                if(i == 0 && str[i].equals("}"))
                {
                    count++;
                    stack.push("x");      
                }
                else if(str[i].equals("{"))
                    stack.push("x");
                else if(str[i].equals("}"))
                    stack.pop();
            }
            
            if(stack.size() != 0)
                count = count + (stack.size() / 2);
            
            output.write(k + ". " + count + "\n");
            k++;
        }
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a1422b15-e256-4b2b-8a12-4d231eb6244c/_2020_11_06_15_01_26_921.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a1422b15-e256-4b2b-8a12-4d231eb6244c/_2020_11_06_15_01_26_921.png)

### 4) 처음 연속으로 '}'가 두번들어오면 빈stack에서 pop을 하는것을 발견하여 if조건을 수정함

### ex) input: '}}{{'

```java
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = 1;
        while(true)
        {
            Stack stack = new Stack();
						String[] str = input.readLine().split("");
            int count = 0;
						if (str[0].equals("-"))
							break;

            for (int i = 0; i < str.length; i++) 
            {
                if(stack.isEmpty() && str[i].equals("}"))
                {
                    count++;
                    stack.push("x");      
                }
                else if(str[i].equals("{"))
                    stack.push("x");
                else if(str[i].equals("}"))
                    stack.pop();
            }
            
            if(stack.size() != 0)
                count = count + (stack.size() / 2);
            
            output.write(k + ". " + count + "\n");
            k++;
        }
        input.close();
        output.flush();
				output.close(); 
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff65b6b7-21d3-49c8-befd-e088cfe84194/_2020_11_06_15_04_33_465.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff65b6b7-21d3-49c8-befd-e088cfe84194/_2020_11_06_15_04_33_465.png)

## 3. 결과

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d6b622cd-113d-4bd2-874e-3af43edfb588/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d6b622cd-113d-4bd2-874e-3af43edfb588/Untitled.png)