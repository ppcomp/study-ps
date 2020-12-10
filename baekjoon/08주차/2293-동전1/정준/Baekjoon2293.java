import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];    //동전을 저장하는 배열  
        int[] dp = new int[k+1];    //DP테이블을 저장하는 배열(동전의 합 0~k)
        for(int i=0;i<n;i++) 
            coin[i] = Integer.parseInt(br.readLine());
        
        dp[0] = 1;  //0번째 인덱스는 1로 초기화
        for(int i=0;i<n;i++) {
            for(int j=0;j<=k;j++) {
                if(coin[i]<=j)  //동전의 합인 j가 i번째 동전보다 크거나 같을 때(동전의 합에 최소한 i번째 동전이 들어갈 수 있을 때)
                    dp[j] += dp[j-coin[i]];
            }
        }
        bw.write(dp[k]+"");
        bw.flush();
        
    }
}
