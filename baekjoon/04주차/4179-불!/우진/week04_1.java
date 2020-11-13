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

public class week04_1 {
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
