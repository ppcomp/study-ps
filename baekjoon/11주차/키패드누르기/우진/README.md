# 카카오인턴 - 키패드 누르기

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67256](https://programmers.co.kr/learn/courses/30/lessons/67256?language=java)

## 2. 과정

키패드의 각 숫자 위치 좌표를 배열에 저장한 후1, 4, 7은 왼손, 3, 6, 9는 오른손으로 고정 배정하고 2, 5, 8, 0은 좌표사이의 거리를 뺄셈으로 구해 작은 숫자가 해당하는 손으로 배정한다. 숫자가 같을 시, 사용하는 손에 따라 배정한다.

 

```java
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int tempL = 10, tempR = 11;
        String[] arr = {"31", "00", "01", "02", "10", "11", "12", "20", "21", "22", "30", "32"};
        for(int i = 0; i < numbers.length; i++)
        {
            int num = numbers[i];
            if(num == 1 || num == 4 || num == 7)
            {
                answer += "L";
                tempL = num;
            }
            else if(num == 3 || num == 6 || num == 9)
            {
                answer += "R";
                tempR = num;
            }

            else
            {
                int sumL = cal(arr, num, tempL);
                int sumR = cal(arr, num, tempR);
                if(sumL == sumR)
                {
                    if(hand.equals("left"))
                    {
                        answer += "L";
                        tempL = num;
                    }
                    else
                    {
                        answer += "R";
                        tempR = num;
                    }
                }
                else if(sumL < sumR)
                {
                    answer += "L";
                    tempL = num;
                }
                else
                {
                    answer += "R";
                    tempR = num;
                }
            }
        }
        return answer;
    }
    public static int cal(String[] arr, int num, int temp)
    {
        String[] rc = new String[2];
        String[] rcT = new String[2];
        rc = arr[num].split("");
        rcT = arr[temp].split("");
        int sum = Math.abs(Integer.parseInt(rc[0]) - Integer.parseInt(rcT[0])) + 
                Math.abs(Integer.parseInt(rc[1]) - Integer.parseInt(rcT[1]));
        return sum;
    }
}
```
![캡처_2021_01_04_18_55_49_862](https://user-images.githubusercontent.com/32921283/103523162-d341f500-4ebe-11eb-810f-80032f9dc8b7.png)
