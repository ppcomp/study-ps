import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week08_2 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(input.readLine());
        for(int t = 0; t < tc; t++)
        {
            int n = Integer.parseInt(input.readLine());
            int[][] arr = new int[2][n+1];
            int[][] stc = new int[2][n+1];

            for(int i = 0; i < 2; i++)
            {
                String[] temp = input.readLine().split(" ");
                for(int j = 1; j <= n; j++)
                    arr[i][j] = Integer.parseInt(temp[j-1]);
            }
            stc[0][0] = stc[1][0] = 0;
            stc[0][1] = arr[0][1];
            stc[1][1] = arr[1][1];
            for(int i = 2; i <= n; i++)
            {
                stc[0][i] = Math.max(stc[1][i-1], stc[1][i-2]) + arr[0][i];
                stc[1][i] = Math.max(stc[0][i-1], stc[0][i-2]) + arr[1][i];
            }
            output.write(Math.max(stc[0][n], stc[1][n]) + "");
            output.newLine();
        }
        output.flush();
        output.close();
        input.close();
    }
}
