# 2146 - 다리만들기

## 1. 개요

[https://www.acmicpc.net/problem/2146](https://www.acmicpc.net/problem/2146)

## 2. 과정

4179 - 불! 문제를 응용해서 육지마다 번호를 지정해주고 Queue에 삽입하여 너비우선탐색을 통해  물위(0)가 아닌 다른번호를 찾으면 찾을때까지의 카운팅을 하여 반환하여 해결함.

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
    static ArrayList<Integer> arr = new ArrayList<Integer>();
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(input.readLine());
        int[][] maze = new int[size][size];
        Queue<pair> land = new LinkedList();
        int count = 0;
        int mark = 2;
        for(int i = 0; i < size; i++)
        {
            String[] temp = input.readLine().split(" ");
            for(int j = 0; j < size; j++)
                maze[i][j] = Integer.parseInt(temp[j]);                                               
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                Queue<pair> find = new LinkedList();
                if(maze[i][j] == 1)
                {
                    land.add(new pair(i, j));
                    bfs(maze, mark, land, find); 
                    findLand(maze, mark, find);
                    mark++;
                }
            }
        }
        arr.sort(null);
        output.write(arr.get(0)-1 + "");
        input.close();
        output.flush();
        output.close(); 
    }
    public static void print(int[][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr.length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }
    public static void findLand(int[][] maze, int mark, Queue<pair> find)
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[][] copy = new int[maze.length][maze.length];

        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze.length; j++)
                copy[i][j] = maze[i][j];
        }
        int flag = 0;
        int count = 0;
        while(!find.isEmpty())
        {
            int findSize = find.size();
            for(int i = 0; i < findSize; i++)
            {
                pair lp = find.poll();
                
                for(int j = 0; j < dx.length; j++)
                {
                    int nx = lp.x + dx[j];
			        int ny = lp.y + dy[j];
                    if(nx < 0 || nx >= maze.length || ny < 0 || ny >= maze.length) 
                        continue;
                    if(copy[nx][ny] == 0)
                    {
                        copy[nx][ny] = mark;
                        find.add(new pair(nx, ny));
                    }
                    else if(copy[nx][ny] != 0 && copy[nx][ny] != mark)
                    {
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1)
                    break;
            }
            count++;
            if(flag == 1)
            {
                arr.add(count);
                break;
            }
        } 
    }

    public static void bfs(int[][] maze, int mark, Queue<pair> land, Queue<pair> find)
    {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while(!land.isEmpty())
        {
            pair lp = land.poll();
            if(maze[lp.x][lp.y] != mark)
            {
                maze[lp.x][lp.y] = mark;
                find.add(new pair(lp.x, lp.y));
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
                    find.add(new pair(nx, ny));     
                }
            }
        } 
    }
}
```

## 3. 결과
![image](https://user-images.githubusercontent.com/32921283/100433304-3aa99f80-30de-11eb-8223-8edb2f9079c5.png)