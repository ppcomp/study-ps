import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon9465 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int n;
        int[][] sticker;    //스티커 점수 저장
        int[][] dp;         //DP 테이블

        for(int i=0;i<t;i++) {
            n = Integer.parseInt(br.readLine());
            sticker = new int[2][n];
            for(int j=0;j<2;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<n;k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dp = new int[2][n+1];

            dp[0][1] = sticker[0][0];   //DP 테이블 초기값 설정
            dp[1][1] = sticker[1][0];   //DP 테이블 초기값 설정
            for(int j=2;j<=n;j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + sticker[0][j-1];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + sticker[1][j-1];
            }
            bw.write(Math.max(dp[0][n], dp[1][n])+"\n");
        }
        bw.flush();
    }
}