import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon1074 {
    public static int count = 0;
    public static int r;
    public static int c;
    public static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        z(n,0,0);

        bw.write(result+"\n");
        bw.flush();
        
    }

    public static void z(int n, int row, int col) {
        int N = (int)Math.pow(2, n);
        if(r == row && c == col) {  //답에 도달했으면 카운트값 저장
            result = count;
            return;
        }

        if(r >= row && r < row+N && c >= col && c < col+N) { 
            z(n-1, row, col);               //왼쪽 상단
            z(n-1, row, col+(N/2));         //오른쪽 상단
            z(n-1, row+(N/2), col);         //왼쪽 하단
            z(n-1, row+(N/2), col+(N/2));   //오른쪽 하단
        }
        else {  //이 범위안에 해당좌표가 없으면
            count += N*N;
        }
    }
}