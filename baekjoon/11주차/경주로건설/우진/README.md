# 카카오 인턴 - 경주로 건설

## 1. 개요

[https://programmers.co.kr/learn/courses/30/lessons/67259](https://programmers.co.kr/learn/courses/30/lessons/67259?language=java)

## 2. 과정

### (1) 깊이우선탐색

```java
class Solution {
    static int cost = 0;
    public int solution(int[][] board) {   
        int size = board.length;
        board[size-1][size-1] = 2;
        board[0][0] = 5;
        dfs(board, 0, 0, 0, 0);
        return cost;
    }
    static void dfs(int[][] board, int i, int j, int sum, int visit) 
    {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        if(board[i][j] == 2)
        {
            if(sum < cost || cost == 0)
                cost = sum;
            return;
        }
                
        else
        {
            for(int k = 0 ; k < 4; k++)
            {
                if(i == 0 && j == 0)
                {
                    if(0 <= j+dy[k] && j+dy[k] < board.length && 0 <= i+dx[k] && i + dx[k] < board.length && visit != k+2 && visit != k-2 && board[i+dx[k]][j+dy[k]] != 1 && board[i+dx[k]][j+dy[k]] != 5)
                    {
                        sum += 100;
                        board[i + dx[k]][ j + dy[k]] = 5;
                        dfs(board, i+dx[k], j + dy[k], sum, k);
                        sum -= 100;
                        board[i + dx[k]][ j + dy[k]] = 0;
                    }
                }
                else
                {
                    if(0 <= j+dy[k] && j+dy[k] < board.length && 0 <= i+dx[k] && i + dx[k] < board.length && visit != k+2 && visit != k-2 && board[i+dx[k]][j+dy[k]] != 1 && board[i+dx[k]][j+dy[k]] != 5)
                    {
                        if(visit == k)
                            sum += 100;
                        else
                            sum += 600;
                        if(board[i + dx[k]][ j + dy[k]] != 2)
                            board[i + dx[k]][ j + dy[k]] = 5;
                        dfs(board, i + dx[k], j + dy[k], sum, k);
                        if(board[i + dx[k]][ j + dy[k]] != 2)
                            board[i + dx[k]][ j + dy[k]] = 0;
                        if(visit == k)
                            sum -= 100;
                        else
                            sum -= 600;
                    }
                }
                    
            }
        }
    }
}
```
![캡처_2021_01_04_18_56_09_302](https://user-images.githubusercontent.com/32921283/103523097-b3aacc80-4ebe-11eb-9389-def37c3630c5.png)

### (2) 너비우선탐색

```java
import java.util.Queue;
import java.util.LinkedList;

class pairs
{
    int x;
    int y;
    int cost;
    int visit;
    pairs(int x, int y, int cost, int visit)
    {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.visit = visit;
    }
}

class Solution {
    static int answer = Integer.MAX_VALUE;;
    public int solution(int[][] board) { 
        
        Queue<pairs> way = new LinkedList();
        way.add(new pairs(0,0,0,-1));
        board[0][0] = 1;
        bfs(board, way);
        return answer;
    }
    static void bfs(int[][] board, Queue<pairs> way) 
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while(!way.isEmpty())
        {
            pairs lp = way.poll();
            if(lp.x == board.length-1 && lp.y == board.length-1)
            {
                if(lp.cost < answer)
                    answer = lp.cost;
                continue;
            }
            
            for(int k = 0; k < 4; k++)
            {
                int nx = lp.x + dx[k];
                int ny = lp.y + dy[k];
                if(0 <= ny && ny < board.length && 0 <= nx && nx < board.length 
                && board[nx][ny] != 1)
                {
                    int sum = 0;
                    if(lp.visit == -1 || lp.visit == k)
                        sum += 100 + lp.cost;
                    else
                        sum += 600 + lp.cost;
                        
                    if(board[nx][ny] == 0 || sum <= board[nx][ny])
                    {
                        board[nx][ny] = sum;
                        way.add(new pairs(nx, ny, sum, k));
                    }
                }
            }
        }
    }
}
```
![캡처_2021_01_04_18_55_51_346](https://user-images.githubusercontent.com/32921283/103523110-bb6a7100-4ebe-11eb-8038-99892e2613f2.png)
