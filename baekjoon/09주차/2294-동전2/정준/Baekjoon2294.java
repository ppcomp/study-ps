import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2294 {
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
        Arrays.sort(coin);
        for(int i=1;i<dp.length;i++)
            dp[i] = 10001; //dp 테이블 최대값으로 초기화

        for(int i=0;i<n;i++) {
            for(int j=1;j<=k;j++) {
                if(coin[i]<=j)  {//동전의 합인 j가 i번째 동전보다 크거나 같을 때(동전의 합에 최소한 i번째 동전이 들어갈 수 있을 때)
                    dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
                }
            }
        }
        int min;
        if(dp[k] == 10001)  //최소값이 없으면 -1
            min = -1;
        else
            min = dp[k];
        bw.write(min+"");
        bw.flush();
        
    }
}
