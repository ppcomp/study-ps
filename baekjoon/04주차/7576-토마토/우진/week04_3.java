import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

class tomato
{
    int x;
    int y;
    tomato(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class week04_3 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] size = input.readLine().split(" ");
        int row = Integer.parseInt(size[0]) + 2;
        int col = Integer.parseInt(size[1]) + 2;
        String[][] maze = new String[col][row];
        Queue<tomato> tomato = new LinkedList();
        String[] temp = new String[col];
        int count = -1;
        int find = 0;
        for(int i = 1; i < col-1; i++)
        {
            maze[i][0] = "-1";
            maze[i][row-1] = "-1";
            temp = input.readLine().split(" ");
            for(int j = 1; j < row-1; j++)
            {
                maze[0][j] = "-1";
                maze[col-1][j] = "-1";
                maze[i][j] = temp[j-1];
                if(temp[j-1].equals("0"))
                    find++;
                if(maze[i][j].equals("1"))
                    tomato.add(new tomato(i, j));     
            }
        }
        tomato tp = new tomato(0,0);
        while(!tomato.isEmpty())
        {
            count++;
            if(find == 0)
                break;
            int tomatoSize = tomato.size();
            for(int i = 0; i < tomatoSize; i++)
            {
                tp = tomato.poll();
                if(maze[tp.x+1][tp.y].equals("0"))
                {
                    tomato.add(new tomato(tp.x+1, tp.y));
                    maze[tp.x+1][tp.y] = "X";
                    
                }
                if(maze[tp.x-1][tp.y].equals("0"))
                {
                    tomato.add(new tomato(tp.x-1, tp.y));
                    maze[tp.x-1][tp.y] = "X";
                    
                }
                if(maze[tp.x][tp.y+1].equals("0"))
                {
                    tomato.add(new tomato(tp.x, tp.y+1));
                    maze[tp.x][tp.y+1] = "X";
                   
                }
                if(maze[tp.x][tp.y-1].equals("0"))
                {
                    tomato.add(new tomato(tp.x, tp.y-1));
                    maze[tp.x][tp.y-1] = "X";
                }
            }
        }
        int findX = 0;
        for(int i = 1; i < col-1; i++)
        {
            for(int j = 1; j < row-1; j++)
            {
                if(findX != 0)
                    break;
                if(maze[i][j].equals("0"))
                    findX++;
            }
        }
        if(findX != 0)
            System.out.println("-1");
        else
            System.out.println(count);
        input.close();
        output.flush();
        output.close(); 
    }
}
