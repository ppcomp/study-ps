import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon1799 {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static int n;
    public static int[][] board;
    public static int[] crossLeft;
    public static int[] crossRight;
    public static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        crossLeft = new int[2*n-1];
        crossRight = new int[2*n-1];
        StringTokenizer st;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) 
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        max = 0;
        bishop(0,WHITE,0);  //흰색 블럭 검사
        int whiteMax = max;

        max = 0;
        bishop(0,BLACK,0);  //검은색 블럭 검사
        int blackMax = max;

        bw.write((whiteMax+blackMax)+"");
        bw.flush();
    }

    public static void bishop(int r, int color, int count) {
        for(int i=r;i<n;i++) {
            for(int j=(i%2==color?1:0);j<n;j+=2) {  //체스판 색마다 열의 시작지점 다름
                if(isPromising(i, j)) {
                    board[i][j] = 2;
                    crossLeft[i+j]++;   // '/'대각선 카운트
                    crossRight[i-j+n-1]++;// '\'대각선 카운트
                    bishop(i,color,count+1);
                    crossLeft[i+j]--;
                    crossRight[i-j+n-1]--;
                    board[i][j] = 1;
                }
            }
        }
        if(max < count) //최대 비숍값 갱신
            max = count;
    }

    public static boolean isPromising(int r, int c) {
        if(board[r][c] != 1) 
            return false;   //놓을 수 없는 자리
    
        if(crossLeft[r+c] > 0 || crossRight[r-c+n-1] > 0)   
            return false;   //같은 대각선에 비숍이 있을 때

        return true;
    }
}
