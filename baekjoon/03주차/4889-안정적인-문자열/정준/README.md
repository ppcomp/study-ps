# 4889 - 안정적인 문자열

---

## 개요

---

이문제는 스택을 이용하면 쉽게 풀리는 문제이다.

## 코드

---

```java
import java.io.*;
import java.util.*;

public class Baekjoon4889 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> s;
        int line = 1;
        int changeCount = 0;

        String str = br.readLine();

        while(!str.contains("-")) {
            s = new Stack<>();
            for(int i=0; i<str.length();i++) {
                if(!s.isEmpty()) {
                    if(str.charAt(i) == '{')
                        s.push(str.charAt(i));
                    else
                        s.pop(); // 짝이 맞으면 내보냄
                }
                else {  //스택이 비어있을 경우
                    if(str.charAt(i) == '{')
                        s.push(str.charAt(i));
                    else {  // '}' 일 경우
                       s.push('{'); //방향을 바꿔서 스택에 삽입
                       changeCount++;
                    }
                }
            }
            if(!s.isEmpty())
                changeCount += s.size()/2;  //스택에 쌓여있는 '{' 절반을 바꿔줌

            bw.write(line++ +". "+changeCount+"\n");
            str = br.readLine();
            changeCount = 0;
        }
        bw.flush();
        bw.close();
    }
}
```

## 해결과정

---

생각해야 할 조건이 몇가지 있었다.

1. 스택에는 '{'만 넣는다
2. 끝까지 진행하고 스택에 '{'이 남아있는 경우 절반을 '}'로 바꾸는 연산을 해준다
3. 스택이 비어있는 상태에서 '}'를 만나면 '{'로 바꾸는 연산을 해준다.
4. 스택이 비어있지 않은 상태에서 '}'를 만나면 pop()을 해준다.

1~4번 조건들을 코드에 녹여내니 금방 해결되었다.

그리고 바꾸는 연산을 따로 구현하지 않고 간단하게 연산하는 부분을 카운트 하는 방식으로 코드를 구현해 단순화 시켰다.

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/98333220-98008280-2043-11eb-9c15-1d11f4338fb5.png)