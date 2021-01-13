# 카카오 인턴 - 보석 쇼핑

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67258](https://programmers.co.kr/learn/courses/30/lessons/67258?language=java)

## 2. 과정

### (1) 단순 2중for문을 활용

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
        for(int i = 0; i <= gems.length-size; i++)
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
![캡처_2021_01_11_10_40_04_513](https://user-images.githubusercontent.com/32921283/104141457-43d69d80-53fa-11eb-9062-3d85b33b2791.png)
### (2) HashSet에 모든종류의 보석을 집어넣고 Queue와 HashMap에 배열의 보석들을 하나씩 입력한 후 만약 큐의 첫번째 값이 복수개면 삭제하는 반복문을 거쳐서 HashSet과 HashMap의 크기가 같으면 해당하는 길이를 저장하는 방법

 

```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Queue<String> queue = new LinkedList<String>();
        HashSet<String> hs = new HashSet(); 
        HashMap<String, Integer> hm = new HashMap();
        int start = 0;
        answer[1] = Integer.MAX_VALUE;
        for(int i = 0; i < gems.length; i++)
            hs.add(gems[i]);

        for(int i = 0; i < gems.length; i++) 
        {
            if(!hm.containsKey(gems[i])) 
                hm.put(gems[i], 1);
            else 
                hm.put(gems[i], hm.get(gems[i]) + 1);
            queue.add(gems[i]);
            while(true) {
                String temp = queue.peek();
                if(1 < hm.get(temp))
                {
                    hm.put(temp, hm.get(temp) - 1);
                    queue.poll();
                    start++;
                }
                else
                    break;
            }
            if(hm.size() == hs.size() && queue.size() < answer[1]) 
            {
                answer[0] = start;
                answer[1] = queue.size();
            }
        }
        answer[0] += 1;
        answer[1] += answer[0] - 1;
        return answer;
    }
}
```
![캡처_2021_01_11_10_39_41_62](https://user-images.githubusercontent.com/32921283/104141468-55b84080-53fa-11eb-8805-18920ce6667b.png)
