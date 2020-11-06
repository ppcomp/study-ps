# 5430 - AC

---

## 개요

---

앞뒤로 삽입 삭제가 가능한 자료구조를 이용해 푸는 문제이다.

## 코드

---

```java
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        LinkedList<String> list;
        int t = Integer.parseInt(br.readLine());
        String func;
        String inputArr;
        Boolean error;
        Boolean reverse;

        for(int i=0;i<t;i++) {
            func = br.readLine();
            br.readLine();  //not use
            inputArr = br.readLine();
            error = false;
            reverse = false;
            list = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(inputArr,",[]");

            while(st.hasMoreTokens())
                list.add(st.nextToken());

            for(int j=0;j<func.length();j++) {
                if(func.charAt(j) == 'R')
                    reverse = !reverse;
                else {  // 'D'
                    if(list.isEmpty()) {
                        error = true;
                        bw.write("error\n");
                        break;
                    }
                    else {
                        if(reverse)
                            list.removeLast();
                        else
                            list.removeFirst();
                    }
                }
            }
            if(!error) {
                bw.write("[");
                while(!list.isEmpty()) {
                    if(reverse)
                        bw.write(list.removeLast()+"");
                    else
                        bw.write(list.removeFirst()+"");
                    if(list.size() > 0)
                        bw.write(",");
                }
                bw.write("]\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
```

## 해결과정

---

이문제를 해결하면서 힘들었던 부분은

 

1. 입력을 받는 [x1,...,xn]과 같은 형태로 배열에 들어있는 수를 전처리해주는 것
2. 어떻게 배열을 거꾸로 할 때 시간복잡도를 줄일 것인지

이 두가지였다.

1번은 정규식, split을 사용해서 해보려 했지만 생각만큼 잘 되지 않았고 결국 StringTokenizer를 사용하는 방식으로 처리해주었다.

```java
StringTokenizer st = new StringTokenizer(inputArr,",[]");

while(st.hasMoreTokens())
		list.add(st.nextToken());
```

2번 조건이 시행착오를 많이 겪었는데 처음에는 연결리스트를 하나 더 만들어서 거기에 역순으로 넣는 방식으로 구현했지만 'D'연산이 많이나오고 배열의 사이즈가 클수록 시간복잡도가 커졌기 때문에 시간초과가 났다.

그래서 다시 생각해보니 자바에서 연결리스트는 앞 뒤로 모두 삽입, 삭제가 가능한 걸 생각해서 부울린 변수 reverse 를 선언해 거꾸로 했을 때 다시 거꾸로 했을 때로 연산을 나누어 계산하였다.

덱이라는 자료구조가 이와 같다.

하지만 자바에선 왠만한 자료구조가 연결리스트로 다되기 떄문에 연결리스트를 사용하였다.

## 결과

---