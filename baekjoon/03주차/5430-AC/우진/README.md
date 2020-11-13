# 5430 - AC

## 1. 개요

[https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

## 2. 과정

### 1) Deque를 사용하여 reverse일땐 뒤에서 삭제, 아닐땐 앞에서 삭제하는 방법

```java
import java.util.Deque;
import java.util.ArrayDeque;
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
        for(int i = 0; i < num; i++)
        {      
            String[] cmd = input.readLine().split("");
            int arrNum = Integer.parseInt(input.readLine());
            String str = input.readLine();
            str = str.substring(1, str.length()-1);
            String[] arr = str.split(",");
            Deque deque = new ArrayDeque();
            int reverse = 0, error = 0;
            for(int j = 0; j < arrNum; j++)
                deque.add(arr[j]);      
            for(int j = 0; j < cmd.length; j++)
            {
                if(cmd[j].equals("R"))
                {
                    if(reverse == 0)
                        reverse = 1;
                    else
                        reverse = 0;
                }
                else if(cmd[j].equals("D"))
                {
                    if(deque.isEmpty())
                    {
                        error = 1;
                        break;
                    }
                    else
                    {
                        if(reverse == 0)
                            deque.pollFirst();
                        else
                            deque.pollLast();
                    }
                }
            }
            if(error != 0)
                output.write("error\n");
            else{
                output.write("[");
                if(reverse == 0)
                {
                    output.write(deque.pollFirst() +"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollFirst()+"");
                    }
                }
                else
                {
                    output.write(deque.pollLast()+"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollLast()+"");
                    }
                    
                }
                output.write("]\n");
            }
            
        }
        output.flush();
				output.close(); 
        input.close();   
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5f316d40-3701-4d4b-b8cc-edbe973cd801/_2020_11_06_15_11_30_705.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5f316d40-3701-4d4b-b8cc-edbe973cd801/_2020_11_06_15_11_30_705.png)

### 2) Deque가 비어있을때 출력을하면 'null' 이 출력되는것을 확인하여 수정

### ex) input: 1    D    1    [1]

### output: [null]

```java
import java.util.Deque;
import java.util.ArrayDeque;
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
        for(int i = 0; i < num; i++)
        {      
            String[] cmd = input.readLine().split("");
            int arrNum = Integer.parseInt(input.readLine());
            String str = input.readLine();
            str = str.substring(1, str.length()-1);
            String[] arr = str.split(",");
            Deque deque = new ArrayDeque();
            int reverse = 0, error = 0;
            for(int j = 0; j < arrNum; j++)
                deque.add(arr[j]);
            
            for(int j = 0; j < cmd.length; j++)
            {
                if(cmd[j].equals("R"))
                {
                    if(reverse == 0)
                        reverse = 1;
                    else
                        reverse = 0;
                }
                else if(cmd[j].equals("D"))
                {
                    if(deque.isEmpty())
                    {
                        error = 1;
                        break;
                    }
                    else
                    {
                        if(reverse == 0)
                            deque.pollFirst();
                        else
                            deque.pollLast();
                    }
                }
            }
            if(error != 0)
                output.write("error\n");
            else{
                output.write("[");
                if(reverse == 0)
                {
                    if(!deque.isEmpty())
                        output.write(deque.pollFirst() +"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollFirst()+"");
                    }
                }
                else
                {
                    if(!deque.isEmpty())
                        output.write(deque.pollLast()+"");
                    while(!deque.isEmpty())
                    {
                        output.write(",");
                        output.write(deque.pollLast()+"");
                    }
                    
                }
                output.write("]\n");
            }
            
        }
        output.flush();
		output.close(); 
        input.close();
        
    }
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/45dbcc4d-b1bd-4552-98f1-8450a091507a/_2020_11_06_15_16_40_456.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/45dbcc4d-b1bd-4552-98f1-8450a091507a/_2020_11_06_15_16_40_456.png)

## 3. 결과

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fb61237d-84ff-4471-997b-7faaf614a21d/_2020_11_06_15_16_54_158.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fb61237d-84ff-4471-997b-7faaf614a21d/_2020_11_06_15_16_54_158.png)