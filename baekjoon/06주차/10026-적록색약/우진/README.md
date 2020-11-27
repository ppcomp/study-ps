# 10026 - 적녹색약

## 1. 개요

[https://www.acmicpc.net/problem/10026](https://www.acmicpc.net/problem/10026)

## 2. 과정

2667 - 단지번호붙이기 문제와 유사하여 적록색약의 경우만 따로 추가하여 해결함.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

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
    static int countA = 0, countB = 0;
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(input.readLine());
        String[][] mazeA = new String[size][size];
        String[][] mazeB = new String[size][size];
        Queue<pair> rgb = new LinkedList();
        for(int i = 0; i < size; i++)
        {
            String[] temp = input.readLine().split("");
            for(int j = 0; j < size; j++)
            {
                mazeA[i][j] = temp[j];         
                mazeB[i][j] = temp[j];
            }                                      
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(!mazeA[i][j].equals("A"))
                {
                    rgb.add(new pair(i, j));
                    bfs_3(mazeA, rgb);
                }
                if(!mazeB[i][j].equals("A"))
                {
                    rgb.add(new pair(i, j));
                    bfs_2(mazeB, rgb); 
                }  
            }
        }
        System.out.print(countA + " " + countB);
        input.close();
        output.flush();
        output.close(); 
    }
   
    public static void bfs_3(String[][] maze, Queue<pair> rgb)
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        while(!rgb.isEmpty())
        {       
            pair lp = rgb.poll();
            String color = "";
            if(!maze[lp.x][lp.y].equals("A"))
            {
                color = maze[lp.x][lp.y];
                maze[lp.x][lp.y] = "A";
            }
            for(int j = 0; j < dx.length; j++)
            {
                int nx = lp.x + dx[j];
			    int ny = lp.y + dy[j];
                if(nx < 0 || nx >= maze.length || ny < 0 || ny >= maze.length) 
                    continue;
                if(maze[nx][ny].equals(color))
                    rgb.add(new pair(nx, ny));
            }
        }
        countA++;
    }

    public static void bfs_2(String[][] maze, Queue<pair> rgb)
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        while(!rgb.isEmpty())
        {       
            pair lp = rgb.poll();
            String color = "";
            if(!maze[lp.x][lp.y].equals("A"))
            {
                color = maze[lp.x][lp.y];
                maze[lp.x][lp.y] = "A";
            }
            for(int j = 0; j < dx.length; j++)
            {
                int nx = lp.x + dx[j];
			    int ny = lp.y + dy[j];
                if(nx < 0 || nx >= maze.length || ny < 0 || ny >= maze.length) 
                    continue;
                if(color.equals("R") || color.equals("G"))
                {
                    if(maze[nx][ny].equals("R") || maze[nx][ny].equals("G"))
                        rgb.add(new pair(nx, ny));
                }
                else if(maze[nx][ny].equals(color))
                    rgb.add(new pair(nx, ny));
            }
        }
        countB++;
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100434041-43e73c00-30df-11eb-8fa1-e22b8c462384.png)