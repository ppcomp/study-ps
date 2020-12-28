# 괄호 변환

---

## 개요

---

입력으로 균형잡힌 문자열(여는 괄호, 닫는 괄호 갯수가 동일)이 주어지면 이를 올바른 괄호 문자열(괄호의 짝이 모두 맞는 경우)로 수정하는 문제

## 코드

---

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class ParenthesesConversion {    
    public String solution(String p) {
        if(p.length() == 0)
            return "";
        
        String answer = "";
        Queue<Character> s = new LinkedList<Character>();
        int open = 0, close = 0;    //괄호 갯수 카운트
        StringBuilder sb = new StringBuilder();
        String u = "", v = "";
        for(int i=0;i<p.length();i++) {
            s.offer(p.charAt(i));
            if(p.charAt(i) == '(')
                open++;
            else 
                close++;

            if(open != 0 && close != 0 && open == close) {  //더이상 분리할 수 없는 균형잡힌 문자열
                while(!s.isEmpty()) 
                    sb.append(s.poll());
                
                u = sb.toString();  //지금까지 나왔던 문자열은 u에 저장, 이후의 문자열은 v

                if(open+close >= p.length())    //v가 빈 문자열인 경우
                    v = "";
                else
                    v = p.substring(open+close);    //u 다음 문자열부터는 v

                break;
            }
        }

        if(isCorrect(u)) {  //u가 올바른 괄호이면 v를 위의 알고리즘 다시 수행하고 u에 이어붙임
            u += solution(v);
            return u;
        }
        else {  //u가 올바른 괄호가 아니면
            answer += "(" + solution(v) + ")";  //v를 재귀적으로 수행한 결과에 괄호를 붙임
            answer += convertPrnth(u);  //u의 첫번째, 마지막 문자 제거하고 나머지 문자열 괄호 방향 바꾸고 뒤에 붙임

            return answer;
        }
        
    }

    public boolean isCorrect(String p) {    //올바른 괄호 판단
        Stack<Character> s = new Stack<>();

        for(int i=0;i<p.length();i++) {
            if(p.charAt(i) == '(')
                s.push(p.charAt(i));
            else {
                if(s.isEmpty())
                    return false;
                else
                    s.pop();
            }
        }
        return true;
    }

    public String convertPrnth(String p) {  //문자열 괄호 변환
        String result = "";
        if(p.length() <= 2)
            return result;
        else {
            result = p.substring(1,p.length()-1);   //앞 뒤 문자열 제거
            result = result.replace("(", "O");  //괄호 방향 변경
            result = result.replace(")","C");
            result = result.replace("O", ")");
            result = result.replace("C","(");
            return result;
        }
    }
    
}
```

## 해결 과정

---

문제에서 주어지는 올바른 괄호 문자열 변환 알고리즘은 다음과 같다.

```
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
  4-3. ')'를 다시 붙입니다. 
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
  4-5. 생성된 문자열을 반환합니다.
```

이 알고리즘을 토대로 코드를 작성하는데 중요한 것은 2번의 

**"u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다."** 

라는 문구이다. u와 v를 나눌 때  u는 균형잡힌 최소 괄호 문자열이 되어야하고 v는 빈 문자열이 될 수 있다는 부분을 조심해서 그대로 구현하면 위의 코드가 된다.

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/103122570-1e405900-46c4-11eb-95ef-a1a66bb128ee.png)