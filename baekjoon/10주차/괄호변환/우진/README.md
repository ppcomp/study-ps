# 2020KAKAO - 괄호 변환

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/60058](https://programmers.co.kr/learn/courses/30/lessons/60058?language=java)

## 2. 과정

```
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. - 8~9번째 라인
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. - 12~15번째 라인
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. - 17~18번째 라인
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. - 18번째 라인
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. - 19~27번째 라인 
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. - 21번째 라인
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. - 22번째 라인
  4-3. ')'를 다시 붙입니다. - 23번째 라인
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. - 24~25번째 라인
  4-5. 생성된 문자열을 반환합니다. - 26번째 라인
```

```java
class Solution {
    public String solution(String p) {
        String answer = func(p);
        return answer;
    }
    public static String func(String w)
    {
        if(w.length() == 0)
            return "";
        else
        {
            int division = dividing(w);
            String u, v;
            u = w.substring(0, division);
            v = w.substring(division, w.length());
           
            if(uCheck(u))
                return u + func(v);
            else
            {
                String empty = "(";
                empty += func(v);
                empty += ")";
                u = u.substring(1, u.length()-1);
                u = reverse(u);
                return empty + u;
            }
        }
    }

    public static int dividing(String w)
    {
        int open = 0, close = 0;
        int i;
        for(i = 0; i < w.length(); i++)
        {
            if(w.charAt(i) == '(')
                open++;
            else if(w.charAt(i) == ')')
                close++;
            if(open == close)
                break;
        }
        return i + 1;
    }
    public static boolean uCheck(String u)
    {
        int count = 0;
        for(int i = 0; i < u.length(); i++)
        {
            if(u.charAt(i) == '(')
                count++;
            else if(u.charAt(i) == ')')
                count--;
            if(count < 0)
                return false;
        }
        return true;
    }
    public static String reverse(String u)
    {
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < u.length(); i++)
        {
            if(u.charAt(i) == '(')
                str.append(")");
            else if(u.charAt(i) == ')')
                str.append("(");
        }
        return str.toString();
    }
}
```

## 3. 결과
![캡처_2020_12_28_17_55_26_972](https://user-images.githubusercontent.com/32921283/103202545-0cfe8300-4936-11eb-9008-dc0032031844.png)
