import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week06_1 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] line = input.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int[] visit = new int[N];
        int[] num = new int[M];
        dfs(N, M, 0, visit, num);
        input.close();
        output.flush();
        output.close(); 
    }
    
    static void dfs(int N, int M, int deep, int[] visit, int[] num)
    {
        if(deep == M)
        {
            for(int i =0; i < M; i++)
                System.out.print(num[i] + " ");
            System.out.println();
            return;
        }
        for(int i = 0; i < N; i++)
        {
            if(visit[i] != 1)
            {
                visit[i] = 1;
                num[deep] = i + 1;
                dfs(N, M, deep+1, visit, num);
                visit[i] = 0;
            }
        } 
    }
}
