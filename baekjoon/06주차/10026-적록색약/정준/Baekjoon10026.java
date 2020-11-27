import java.io.*;

public class Baekjoon10026 {
    public static int n;
    public static char[][] paint;
    public static int count1, count2;
    public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        paint = new char[n][n];

        count1 = 0; //첫번째 탐색에서 카운트
        count2 = 0; //두번째 탐색에서 카운트

        //배열 초기화
        String temp;
        for(int i=0;i<n;i++) {
            temp = br.readLine();
            for(int j=0;j<n;j++) 
                paint[i][j] = temp.charAt(j);
        }

        //적록색약이 아닐 때 탐색
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                switch(paint[i][j]) {
                    case 'R':
                        paint[i][j] = '0';
                        dfs1(i,j,'R');
                        count1++;
                        break;
                    case 'G':
                        paint[i][j] = '0';
                        dfs1(i,j,'G');
                        count1++;
                        break;
                    case 'B':
                        paint[i][j] = '1';
                        dfs1(i,j,'B');
                        count1++;
                }
            } 
        }

        //적록색약일 때 탐색
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                switch(paint[i][j]) {
                    case '0':
                        paint[i][j] = '2';
                        dfs2(i,j,'0');
                        count2++;
                        break;
                    case '1':
                        paint[i][j] = '3';
                        dfs2(i,j,'1');
                        count2++;
                }
            }
        }
        bw.write(count1+" "+count2);
        bw.flush();
    }

    public static void dfs1(int row, int col, char color) {
        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];

            if(r < n && r >= 0 && c < n && c >=0) {
                if(paint[r][c] == color) {
                    switch(color) {
                    case 'R':
                        paint[r][c] = '0';
                        dfs1(r,c,color);
                        break;
                    case 'G':
                        paint[r][c] = '0';
                        dfs1(r,c,color);
                        break;
                    case 'B':
                        paint[r][c] = '1';
                        dfs1(r,c,color);
                    }
                }
            }
        }
    }

    public static void dfs2(int row, int col, char color) {
        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];

            if(r < n && r >= 0 && c < n && c >=0) {
                if(paint[r][c] == color) {
                    switch(color) {
                    case '0':
                        paint[r][c] = '2';
                        dfs2(r,c,color);
                        break;
                    case '1':
                        paint[r][c] = '3';
                        dfs2(r,c,color);
                    }
                }
            }
        }
    }
}