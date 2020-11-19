# 11729 - 하노이탑이동순서

---

## 개요

---

재귀의 대표적인 알고리즘

## 코드

---

```java
import java.io.*;

public class Baekjoon11729 {
    public static int count = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hanoi(n, 1, 2, 3);
        System.out.println(count);
        System.out.println(sb.toString());
    }

    public static void hanoi(int n, int one, int two, int three) {
        count++;
        if(n==1)
            sb.append(one+" "+three+"\n");
        else {
            hanoi(n-1, one, three, two);
            sb.append(one+" "+three+"\n");
            hanoi(n-1, two, one, three);
        }    
    }
}
```

## 해결 과정

---

1개의 원반을 옮기기 위해선

```
1 3
```

2개의 원반을 옮기기 위해선

```
1 2 - (2번 말뚝에 1원반 위치)
1 3 - 여기부터는 3번말뚝에 쌓는 과정
2 3
```

3개의 원반을 옮기기 위해선

```
1 3
1 2
3 2 - (2번 말뚝에 1,2원반 위치)
1 3 - 여기부터는 3번말뚝에 쌓는 과정
2 1
2 3
1 3
```

위와 같은 이동이 필요하다.

그러면

n개의 원반을 3번 말뚝으로 옮기기 위해서는 

2번 말뚝에 n-1개의 원반을 옮기고 n번째 원반을 3번으로 옮거야 한다는 일반화가 나온다.

이것을 재귀방식으로 푼 것이 위에 코드이다.

```java
public static void hanoi(int n, int one, int two, int three) {
        count++;
        if(n==1)
            sb.append(one+" "+three+"\n");
        else {
            hanoi(n-1, one, three, two);
            sb.append(one+" "+three+"\n");
            hanoi(n-1, two, one, three);
        }    
    }
```

## 결과

---
![image](https://user-images.githubusercontent.com/47655983/99699760-ac2d9080-2ad5-11eb-927a-45549d45821a.png)