import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

class ways
{
    int x;
    int y;
    ways(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class week04_2 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] size = input.readLine().split(" ");
        int col = Integer.parseInt(size[0]) + 2;
        int row = Integer.parseInt(size[1]) + 2;
        String[][] maze = new String[col][row];
        Queue<ways> way = new LinkedList();
        String[] temp = new String[row];
        int count = 1;
        for(int i = 1; i < col-1; i++)
        {
            maze[i][0] = "0";
            maze[i][row-1] = "0";
            temp = input.readLine().split("");
            for(int j = 1; j < row-1; j++)
            {
                maze[0][j] = "0";
                maze[col-1][j] = "0";
                maze[i][j] = temp[j-1];
            }
        }
        maze[1][1] = "S";
        maze[col-2][row-2] = "E";
        way.add(new ways(1, 1));

        ways wp = new ways(0,0);
        boolean find = false;
        while(!find && (!way.isEmpty()))
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
            count++;
            int waySize = way.size();
            for(int i = 0; i < waySize; i++)
            {
                wp = way.poll();
                //jihun goal
                if(maze[wp.x+1][wp.y].equals("E"))
                {
                    find = true;
                    break;
                }
                if(maze[wp.x-1][wp.y].equals("E"))
                {
                    find = true;
                    break;
                }
                if(maze[wp.x][wp.y+1].equals("E"))
                {
                    find = true;
                    break;
                }
                if(maze[wp.x][wp.y-1].equals("E"))
                {
                    find = true;
                    break;
                }
                //jihun move
                if(maze[wp.x+1][wp.y].equals("1"))
                {
                    way.add(new ways(wp.x+1, wp.y));
                    maze[wp.x+1][wp.y] = "X";
                    
                }
                if(maze[wp.x-1][wp.y].equals("1"))
                {
                    way.add(new ways(wp.x-1, wp.y));
                    maze[wp.x-1][wp.y] = "X";
                    
                }
                if(maze[wp.x][wp.y+1].equals("1"))
                {
                    way.add(new ways(wp.x, wp.y+1));
                    maze[wp.x][wp.y+1] = "X";
                   
                }
                if(maze[wp.x][wp.y-1].equals("1"))
                {
                    way.add(new ways(wp.x, wp.y-1));
                    maze[wp.x][wp.y-1] = "X";
                }
            }
        }
        System.out.println(count);
        input.close();
        output.flush();
        output.close(); 
    }
}
