# 2020KAKAO - 가사 검색

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/60060](https://programmers.co.kr/learn/courses/30/lessons/60060?language=java)

## 2. 과정

먼저 길이가 같은지 비교하고 각 자리가 같은지 비교하며 해결

```java
class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++)
        {
            int count = 0;
            for(int j = 0; j < words.length; j++)
            {
                int flag = 0;
                if(queries[i].length() != words[j].length())
                    continue;
                else
                {
                    for(int k = 0; k < words[j].length(); k++)
                    {
                        if(queries[i].charAt(k) != words[j].charAt(k) && queries[i].charAt(k) != '?')
                        {
                            flag = 1;
                            break;
                        }
                    }
                    if(flag == 0)
                        count++; 
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
```

## 3. 결과
![캡처_2020_12_28_17_55_28_592](https://user-images.githubusercontent.com/32921283/103202595-2ef80580-4936-11eb-95fe-02a826f89ded.png)
