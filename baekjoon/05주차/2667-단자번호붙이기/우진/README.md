# 2667 - 단지번호붙이기

## 1. 개요

[https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667)

## 2. 과정

2146 - 다리만들기와 같이 단지마다 번호를 지정한 후 같은 번호를 너비우선탐색을 통해 카운팅한 후 반환하여 해결함.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class pair
{
    int x;
    int y;
    pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(input.readLine());
        int[][] maze = new int[size][size];
        Queue<pair> land = new LinkedList();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int mark = 2;
        for(int i = 0; i < size; i++)
        {
            String[] temp = input.readLine().split("");
            for(int j = 0; j < size; j++)
                maze[i][j] = Integer.parseInt(temp[j]);                                               
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(maze[i][j] == 1)
                {
                    land.add(new pair(i, j));
                    bfs(maze, mark, land, arr);
                    mark++;
                }  
            }
        }
        arr.sort(null);
        System.out.println(mark - 2);
        for(Integer i : arr) { 
            System.out.println(i);
        }
        input.close();
        output.flush();
        output.close(); 
    }
   
    public static void bfs(int[][] maze, int mark, Queue<pair> land, ArrayList<Integer> arr)
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int count = 0;
        
        while(!land.isEmpty())
        {       
            pair lp = land.poll();
            if(maze[lp.x][lp.y] != mark)
            {
                maze[lp.x][lp.y] = mark;
                count++;
            }
            for(int j = 0; j < dx.length; j++)
            {
                int nx = lp.x + dx[j];
			    int ny = lp.y + dy[j];
                if(nx < 0 || nx >= maze.length || ny < 0 || ny >= maze.length) 
                    continue;
                if(maze[nx][ny] == 1)
                {
                    land.add(new pair(nx, ny));
                    maze[nx][ny] = mark;
                    count++;      
                }
            }
        }
        arr.add(count);   
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433478-7f353b00-30de-11eb-9c8c-6e8ba5214fa8.png)