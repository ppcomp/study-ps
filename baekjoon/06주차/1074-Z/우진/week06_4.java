import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.Math;

public class week06_4 {
    static int count = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int r = Integer.parseInt(line[1]);
        int c = Integer.parseInt(line[2]);
        Z(N, c, r);
        System.out.print(count);
        input.close();
        output.flush();
        output.close(); 
    }
    public static void Z(int n, int row, int col)
    {
        if(n == 0)
            return;
        int size = (int)Math.pow(2, n);
        int full = size * size;
        int half = size / 2;
        
        if(row < half && col < half)
            Z(n-1, row, col);
        else if(row >= half && col < half){
            count += (full / 4);
            Z(n-1, row - half, col);
        }
        else if(row < half && col >= half){
            count += (full / 4) * 2;
            Z(n-1, row, col - half);
        }
        else if(row >= half && col >= half){
            count += (full / 4) * 3;
            Z(n-1, row - half, col - half);
        }
    }
}
