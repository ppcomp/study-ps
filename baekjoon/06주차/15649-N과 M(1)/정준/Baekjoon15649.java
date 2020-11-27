import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon15649 {
    public static int n;
    public static int m;
    public static int[] seq;
    public static boolean[] checked;
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        seq = new int[m];
        checked = new boolean[n];
        
        dfs(0);
        bw.flush();
    }

    public static void dfs(int d) throws IOException {
        if(d == m) { //끝까지 탐색했을 때
            for(int i:seq) //배열에 담긴 수열 출력
                bw.write(i+" ");
            bw.newLine();
        }
        else {  //끝까지 탐색 못했을 때
            for(int i=0;i<n;i++) {
                if(checked[i] == false) {
                    checked[i] = true;  //현재 사용하는 숫자 true로 변경
                    seq[d] = i + 1;
                    dfs(d+1);   //한층 더 탐색

                    checked[i] = false; //d+1 탐색 끝나면 false로 변경
                }
            }
        }
    }
}