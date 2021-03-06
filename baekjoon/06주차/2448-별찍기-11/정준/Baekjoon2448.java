import java.io.*;

public class Baekjoon2448 {
    public static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int row = n;
        int col = 2*n-1;
        arr = new char[row][col];
        //배열 공백으로 초기화
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
            arr[i][j] = ' ';
            }
        }
        
        star(n,0,col/2);
        
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                bw.write(arr[i][j]);
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static void star(int n, int row, int col) {
        if(n == 3) {
            arr[row][col] = '*';

            arr[row+1][col-1] = '*';
            arr[row+1][col+1] = '*';

            arr[row+2][col-2] = '*';
            arr[row+2][col-1] = '*';
            arr[row+2][col] = '*';
            arr[row+2][col+1] = '*';
            arr[row+2][col+2] = '*';
        }
        else {
            star(n/2,row,col);
            star(n/2,row+(n/2),col-(n/2));
            star(n/2,row+(n/2),col+(n/2));
        }
    }
}