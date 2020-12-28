# 2020KAKAO - 자물쇠와 열쇠

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/60059](https://programmers.co.kr/learn/courses/30/lessons/60059?language=java)

## 2. 과정

자물쇠의 3배에 해당하는 배열 map을 만든 후 map의 중앙에 자물쇠를 넣고 map의 좌측 상단부터 key를 90도씩 회전해가며 map의 중앙 즉, 자물쇠가 있는 부분이 전부 1이 되는지 확인한다.

 

```java
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int[][] map = new int[lock.length * 3][lock.length * 3];
        for(int i = 0; i < map.length-key.length; i++)
        {
            for(int j = 0; j < map.length-key.length; j++)
            {
                answer = put(i, j, map, key, lock);
                if(answer == true)
                    break;
            }
            if(answer == true)
                break;
        }
        return answer;
    }
    public static boolean put(int row, int col, int[][] map, int[][] key, int[][] lock)
    {
        boolean bol = false;
        for(int k = 0; k < 4; k++)
        {
            int[][] rotate = new int[key.length][key.length];
           
            for(int i = 0; i < rotate.length; i++)
            {
                for(int j = 0; j < rotate.length; j++)
                {
                    if(k == 0)
                        rotate[i][j] = key[i][j];
                    else if(k == 1)
                        rotate[i][j] = key[rotate.length-1-j][i];
                    else if(k == 2)
                        rotate[i][j] = key[rotate.length-1-i][rotate.length-1-j];
                    else if(k == 3)
                        rotate[i][j] = key[j][rotate.length-1-i];
                }   
            }
            
            putIn(map, lock);
            for(int i = 0; i < rotate.length; i++)
            {
                for(int j = 0; j < rotate.length; j++)
                    if(map[row+i][col+j] == 0)
                        map[row+i][col+j] = rotate[i][j];
            }
            bol = check(map, map.length/3);

            if(bol == true)
                break;
            clear(map);      
        }
        return bol;
    }

    public static void clear(int[][] map)
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map.length; j++)
                map[i][j] = 0;
        }
    }

    public static void putIn(int[][] map, int[][] lock)
    {
        for(int i = lock.length; i < lock.length * 2; i++)
        {
            for(int j = lock.length; j < lock.length * 2; j++)
                map[i][j] = lock[i-lock.length][j-lock.length];
        }
    }

    public static boolean check(int[][] map, int length)
    {
        int flag = 0;
        for(int i = length; i < length*2; i++)
        {
            for(int j = length; j < length*2; j++)
            {
                if(map[i][j] == 0)
                {
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)
                break;
        }
        if(flag == 1)
            return false;
        else
            return true;
    }
}
```

## 3. 결과
![캡처_2020_12_28_17_55_27_963](https://user-images.githubusercontent.com/32921283/103202581-26073400-4936-11eb-9cb8-734e6171aa21.png)
