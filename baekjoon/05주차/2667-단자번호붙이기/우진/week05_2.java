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

public class week05_2 {
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
