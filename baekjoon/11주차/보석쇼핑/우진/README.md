# 카카오 인턴 - 보석 쇼핑

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67258](https://programmers.co.kr/learn/courses/30/lessons/67258?language=java)

## 2. 과정

보속의 갯수를 카운팅하고 배열의 처음을 기준으로 모든 보석이 해당하는 범위를 찾음

```java
import java.util.ArrayList;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        ArrayList bosuk = new ArrayList();
        for(int i = 0; i < gems.length; i++)
        {
            if(!bosuk.contains(gems[i]))
                bosuk.add(gems[i]);  
        }
        int size = bosuk.size();
        int[] num = new int[size];
        int sum = -1;
        int x = 0, y = 0;
        for(int i = 0; i < gems.length-size; i++)
        {
            for(int j = i; j < gems.length; j++)
            {
                for(int k = 0; k < size; k++)
                    if(bosuk.get(k).equals(gems[j]))
                        num[k] += 1;
                if(check(num))
                {
                    if(j-i < sum || sum == -1)
                    {
                        sum = j-i;
                        x = i;
                        y = j;
                    }
                }
            }
            clear(num);
        }
        answer[0] = x+1;
        answer[1] = y+1;
        return answer;
    }
    static boolean check(int[] num)
    {
        for(int i = 0; i < num.length; i++)
        {
            if(num[i] == 0)
                return false;
        }
        return true;
    }
    static void clear(int[] num)
    {
        for(int i = 0; i < num.length; i++)
            num[i] = 0;
    }
}
```
![캡처_2021_01_04_18_55_50_633](https://user-images.githubusercontent.com/32921283/103523151-cde4aa80-4ebe-11eb-80bc-7fcc1ccfb7ef.png)
