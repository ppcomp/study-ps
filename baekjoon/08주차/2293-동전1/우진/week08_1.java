import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week08_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] coin = new int[n + 1];
        int[][] result = new int[n+1][k+1];
        
        for(int i = 1; i <= n; i++)
            coin[i] = Integer.parseInt(input.readLine());   
        result[0][0] = 1;     

        for(int i = 1; i <= n; i++)
        {
            for(int j = 0; j <= k; j++)
            {
                if(j >= coin[i])
                    result[i][j] = result[i-1][j] + result[i][j-coin[i]];
                
                else
                    result[i][j] = result[i-1][j];
            }
        }
        output.write(result[n][k] + "");
        output.flush();
        output.close();
        input.close();
    }
}
