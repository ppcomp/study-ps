# 4179 - 불!

## 1. 개요

[https://www.acmicpc.net/problem/4179](https://www.acmicpc.net/problem/4179)

## 2. 과정

미로에 테두리를 두른다음 테두리에 도착하면 탈출시간을 출력하도록 함. Queue를 사용하여 너비우선탐색으로 미로찾기를 함.

### 1) 불이 여러개일때 고려안함

### 2) 불이 없을때 고려안함

### 3)성공

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
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] size = input.readLine().split(" ");
        int col = Integer.parseInt(size[0]) + 2;
        int row = Integer.parseInt(size[1]) + 2;
        String[][] maze = new String[col][row];
        Queue<pair> fire = new LinkedList();
        Queue<pair> jihun = new LinkedList();
        String[] temp = new String[row];
        int count = 0;
        for(int i = 1; i < col-1; i++)
        {
            maze[i][0] = "G";
            maze[i][row-1] = "G";
            temp = input.readLine().split("");
            for(int j = 1; j < row-1; j++)
            {
                maze[0][j] = "G";
                maze[col-1][j] = "G";
                maze[i][j] = temp[j-1];
                if(maze[i][j].equals("F"))
                    fire.add(new pair(i, j));      
                else if(maze[i][j].equals("J"))
                    jihun.add(new pair(i, j));
            }
        }  
        pair fp = new pair(0,0), jp = new pair(0,0);
        boolean find = false;
        while(!find && (!fire.isEmpty() || !jihun.isEmpty()))
        {
            // for(int i = 0; i < col; i++)
            // {
            //     for(int j = 0; j < row; j++)
            //     {
            //         System.out.print(maze[i][j]);
            //     }
            //     System.out.println();
            // }
            // System.out.println("---------------------");
        
            //fire
            int fireSize = fire.size();
            for(int i = 0; i < fireSize; i++)
            {          
                fp = fire.poll();
                if(maze[fp.x+1][fp.y].equals(".") || maze[fp.x+1][fp.y].equals("J"))
                {
                    fire.add(new pair(fp.x+1, fp.y));
                    maze[fp.x+1][fp.y] = "F";
                }
                if(maze[fp.x-1][fp.y].equals(".") || maze[fp.x-1][fp.y].equals("J"))
                {
                    fire.add(new pair(fp.x-1, fp.y));
                    maze[fp.x-1][fp.y] = "F";
                }
                if(maze[fp.x][fp.y+1].equals(".") || maze[fp.x][fp.y+1].equals("J"))
                {
                    fire.add(new pair(fp.x, fp.y+1));
                    maze[fp.x][fp.y+1] = "F";
                }
                if(maze[fp.x][fp.y-1].equals(".") || maze[fp.x][fp.y-1].equals("J"))
                {
                    fire.add(new pair(fp.x, fp.y-1));
                    maze[fp.x][fp.y-1] = "F";
                }
            }
            count++;
            int jihunSize = jihun.size();
            for(int i = 0; i < jihunSize; i++)
            {
                jp = jihun.poll();
                //jihun goal
                if(maze[jp.x+1][jp.y].equals("G"))
                {
                   
                    find = true;
                    break;
                }
                if(maze[jp.x-1][jp.y].equals("G"))
                {
                   
                    find = true;
                    break;
                }
                if(maze[jp.x][jp.y+1].equals("G"))
                {
                    
                    find = true;
                    break;
                }
                if(maze[jp.x][jp.y-1].equals("G"))
                {
                   
                    find = true;
                    break;
                }
                //jihun move
                if(maze[jp.x+1][jp.y].equals("."))
                {
                    jihun.add(new pair(jp.x+1, jp.y));
                    maze[jp.x+1][jp.y] = "J";
                    
                }
                if(maze[jp.x-1][jp.y].equals("."))
                {
                    jihun.add(new pair(jp.x-1, jp.y));
                    maze[jp.x-1][jp.y] = "J";
                    
                }
                if(maze[jp.x][jp.y+1].equals("."))
                {
                    jihun.add(new pair(jp.x, jp.y+1));
                    maze[jp.x][jp.y+1] = "J";
                   
                }
                if(maze[jp.x][jp.y-1].equals("."))
                {
                    jihun.add(new pair(jp.x, jp.y-1));
                    maze[jp.x][jp.y-1] = "J";
                    
                }
            }
        }
        if(find)
            System.out.println(count);
        else
            System.out.println("IMPOSSIBLE");
        input.close();
        output.flush();
        output.close(); 
    }
}
```

## 3. 결과
![캡처_2020_11_13_10_10_14_873](https://user-images.githubusercontent.com/32921283/99015496-7c8ffd00-2598-11eb-8034-3c0dbd54bd54.png)