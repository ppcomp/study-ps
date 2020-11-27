import java.io.*;

public class Baekjoon9663 {
    public static int n;
    public static int count;
    public static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n];
        count = 0;
        
        for(int i=0;i<n;i++) {
            board[0] = i;
            queen(1);
        }

        bw.write(count+"");
        bw.flush();

    }

    public static boolean isPromising(int c) {
        for(int i=0;i<c;i++) {
            //같은 열에 위치하거나 같은 대각선상에 있으면 false
            if(board[i] == board[c] || Math.abs(i-c) == Math.abs(board[i]-board[c]))
                return false;
        }
        return true;
    }

    public static void queen(int r) {
        if(r == n) {
            count++;
            return;
        }
        for(int i=0;i<n;i++) {
            board[r] = i;
            if(isPromising(r)) {    //유효성 판단
                queen(r+1);
            }
        }
    }
}