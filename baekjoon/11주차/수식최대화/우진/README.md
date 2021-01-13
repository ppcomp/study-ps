# 카카오 인턴 - 수식 최대화

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67257](https://programmers.co.kr/learn/courses/30/lessons/67257)

## 2. 과정

숫자와 연산자를 분리하여 각각의 연결리스트에 입력

연산자는 숫자연결리스트의 연산자리스트내 연산자의 위치 와 연산자리스트내 연산자의 위치 +1 의 연산을 수행함

연산이 된건 삭제를 함.

```java
import java.util.LinkedList;

class Solution {
    public long solution(String expression) {
        long answer = 0;
        String[] num = expression.split("\\*|\\+|\\-");
        LinkedList<Long> numList = new LinkedList();
        LinkedList<String> operList = new LinkedList();
        LinkedList<Long> numListT = new LinkedList();
        LinkedList<String> operListT = new LinkedList();
        String[] order = new String[6];
        order[0] = "*+-";
	    order[1] = "*-+";
	    order[2] = "+*-";
	    order[3] = "+-*";
	    order[4] = "-*+";
        order[5] = "-+*";

        for(int i = 0; i < num.length; i++)
            numList.add(Long.parseLong(num[i]));
        
        for(int i = 0; i < expression.length(); i++)
        {
            if(expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-')
                operList.add(Character.toString(expression.charAt(i)));
        }
        
        for(int i = 0; i < order.length; i++)
        {
            numListT.addAll(numList);
            operListT.addAll(operList);
            for(int j = 0; j < 3; j++)
            {
                for(int k = 0; k < operListT.size(); k++)
                {
                    if(Character.toString(order[i].charAt(j)).equals(operListT.get(k)))
                    {
                        long temp = 0;
                        if(operListT.get(k).equals("*"))
                             temp = numListT.get(k) * numListT.get(k+1);
                           
                        else if(operListT.get(k).equals("+"))
                            temp = numListT.get(k) + numListT.get(k+1);
                            
                        else
                            temp = numListT.get(k) - numListT.get(k+1);
                        numListT.remove(k);
                        numListT.remove(k);
                        numListT.add(k, temp);
                        operListT.remove(k);
                        k = k-1;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(numListT.get(0)));
            numListT.remove(0);
        }
        return answer;
    }
}
```
![캡처_2021_01_11_10_45_43_718](https://user-images.githubusercontent.com/32921283/104141481-69fc3d80-53fa-11eb-96c6-35eda569f2ad.png)
