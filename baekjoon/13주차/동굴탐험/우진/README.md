# 카카오 블라인드 - 동굴 탐험

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67260](https://programmers.co.kr/learn/courses/30/lessons/67260)

## 2. 과정

처음시작인 0번방과 이어져있는 방들을 큐에 넣고 하나씩 뽑아내어 갈수 있는곳을 너비우선탐색함. 아직 못가는 방은 큐에 다시 넣음.

못가는방을 다시 큐에 넣을때 영원히 전부탐색을 못하는 경우 무한루프에 빠질 수 있어 못가는 방을 다시 큐에 넣는 행위를 10만번 반복하면 모든 행위를 종료하고 false를 반환. 모든방을 탐색하면 true를 반환.

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        int[] number = new int[n];
        int[][] maze = new int[n][n];
        for(int i = 0; i < path.length; i++)
        {
            
            maze[path[i][0]][path[i][1]] = 1;
            maze[path[i][1]][path[i][0]] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int count = 0;
        int check = 0;
        while(!queue.isEmpty())
        {
            check++;
            if(100000 < check)
                break;
            int conti = 0;
            int num = queue.remove();
            for(int i = 0; i < order.length; i++)
            {
                if(order[i][0] == num)
                {
                    order[i][0] = -1;
                    order[i][1] = -1;
                }
            }
            if(number[num] == 1)
                continue;
            for(int i = 0; i < order.length; i++)
            {
                if(order[i][1] == num)
                    conti = 1;
            }
            if(conti == 1)
            {
                queue.add(num);
                continue;
            }
            check = 0;
            number[num] = 1;
            for(int i = 0; i < n; i++)
            {
                if(maze[num][i] == 1)
                    queue.add(i);
            }
            count++;
        }
        int flag = 0;
        for(int i = 0; i < number.length; i++)
        {
            if(number[i] == 0)
                flag = 1;
        }
        if(flag == 1)
            answer = false;
        else
            answer = true;
    
        return answer;
    }
}
```
![image](https://user-images.githubusercontent.com/32921283/104899630-70705380-59be-11eb-8eca-14706f03943d.png)
