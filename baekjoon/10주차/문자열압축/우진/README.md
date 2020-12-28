# 2020KAKAO - 문자열 압축

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/60057](https://programmers.co.kr/learn/courses/30/lessons/60057)

## 2. 과정

압축할 문자 갯수를 하나씩 증가시켜가며 문자열이 가장 짧을 때의 길이를 반환.

압축할 문자열을 변수 std 에 저장하고 압축할 문자열인지 확인하는 문자열을 변수 temp에 저장하여 비교한 후 처리한다.

```java
class Solution {
    public int solution(String line) {
        int min = line.length();
        String newLine = "";
        int split = 1;
        while(split < line.length())   
        {
            newLine = "";
            int count = 1;
            for(int i = 0; i < line.length(); i += count*split)
            {
                if(line.length() < i + split)
                {
                    newLine += line.substring(i, line.length());
                    break;
                }
                String std = line.substring(i, i + split);
                count = 1;
                for(int j = i; j < line.length(); j += split) 
                {
                    
                    if(line.length() < j + split)
                    {
                        break;
                    }
                    String temp = line.substring(j, j + split);
                    if(std.equals(temp))
                        count++;
                    else
                        break;
                }
                count--;
                if(count != 1)
                    newLine += count + std;     
                else
                    newLine += std;  
            }
            if(newLine.length() < min)
                min = newLine.length();
            split++; 
        }
        return min;
    }
}
```

## 3. 결과
![캡처_2020_12_28_17_55_26_135](https://user-images.githubusercontent.com/32921283/103202501-f35d3b80-4935-11eb-98cd-a1c5524b6e61.png)
