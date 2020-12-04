import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week07_3 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(input.readLine());
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
                star(i, j, N);
            System.out.println();
        }
    }
    public static void star(int i, int j, int N)
    {
        if((i / 3) % 3 == 1 && (j / 3) % 3 == 1)
            System.out.print(" ");
        else
        {
            if(N / 3 == 0)
                System.out.print("*");
            else
                star(i, j, N / 3);
        }
    }
}
