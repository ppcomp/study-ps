# 16986 - 인싸들의 가위바위보

## 1. 개요

[https://www.acmicpc.net/problem/16986](https://www.acmicpc.net/problem/16986)

## 2. 과정

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week08_3 {
    static boolean[] select;
    static int[] win = new int[4];
    static int[][] arr;
    static int[][] player;
    static int[] count = new int[4];
    static int K, N;
    static int flag = 0;
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = input.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        arr = new int[N+1][N+1];
        player = new int[4][20];
        select = new boolean[N+1];
        for(int i = 1; i <= N; i++)
        {
            String[] temp = input.readLine().split(" ");
            for(int j = 1; j <= N; j++)
                arr[i][j] = Integer.parseInt(temp[j-1]);
        }

        for(int i = 2; i < 4; i++)
        {
            String[] temp = input.readLine().split(" ");
            for(int j = 0; j < 20; j++)
                player[i][j] = Integer.parseInt(temp[j]);      
        }
        dfs(1, N, player);
        System.out.println(flag);
    }
    private static void dfs (int a, int n, int[][] player)
    {
        if(flag == 1)
            return;
        if(n+1 <= a)
        {
            play_game(1, 2);
            return;
        }
        for(int i = 1; i <= n; i++)
        {
            if(flag == 1) 
                return;
            if(select[i] == false) 
                continue;
            
            select[i] = true;
            player[1][a] = i;
            dfs(a + 1, n, player);
            player[1][a] = 0;
            select[i] = false;
            
        }
        
    }

    private static void play_game(int player1, int player2)
    {
        if(win[1] >= K)
        {
            System.out.println("!!!!!");
            flag = 1;
            return;
        }
        if(N < count[1] || K <= win[2] || K <= win[3])
        {
            return;
        }
        // System.out.println("player1: " + player1 + "  player2: " + player2);
        // System.out.print(player[player1][count[player1]] + " ,\t");
        // System.out.println(player[player2][count[player2]]);
        // System.out.println("Arr" + arr[player[player1][count[player1]]][player[player2][count[player2]]]);
        int ch1 = player[player1][count[player1]];
        int ch2 = player[player2][count[player2]];
        if(ch1 == 0)
            ch1 = 1;
        else if(ch2 == 0)
            ch2 = 1;
        if(arr[ch1][ch2] == 2)
        {
            win[player1]++;
            count[player1]++;
            count[player2]++;
            play_game(player1, 6 - player1 - player2);
            if(flag == 1)
                return;
            win[player1]--;
        }
        else
        {
            win[player2]++;
            count[player1]++;
            count[player2]++;
            play_game(player2, 6 - player1 - player2);
            if(flag == 1)
                return;
            win[player1]--;
        } 
    }
}
```