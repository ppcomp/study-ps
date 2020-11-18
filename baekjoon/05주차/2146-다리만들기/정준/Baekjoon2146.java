import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2146 {
    public static Queue<int[]> q = new LinkedList<>();
    public static int[][] map;
    public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) 
                map[i][j] = Integer.parseInt(st.nextToken());   
        }

        int number = -1;

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(map[i][j] == 1) {
                    dfs(i,j,number--);
                }
            }
        }
        int[] temp, temp2;
        int min = 9999;
        Queue<int[]> q2 = new LinkedList<>();
        boolean[][] checked;

        while(!q.isEmpty()) {
            temp = q.poll();
            q2 = new LinkedList<>();
            checked = new boolean[n][n];
            q2.add(temp);
            map[temp[0]][temp[1]] = 0;
            checked[temp[0]][temp[1]] = true;
            loop:
            while(!q2.isEmpty()) {
                temp2 = q2.poll();
                for(int i=0;i<dir.length;i++) {
                    int row = temp2[0] + dir[i][0];
                    int col = temp2[1] + dir[i][1];
                    if(row >= 0 && row < map.length& col >= 0 && col < map.length) {
                        if(map[row][col] >= 0 && !checked[row][col]) {
                            q2.add(new int[] {row, col});
                            checked[row][col] = true;
                            map[row][col] = map[temp2[0]][temp2[1]] + 1;
                        }
                        else if(map[row][col] != temp[2] && !checked[row][col]) {
                            if(min > map[temp2[0]][temp2[1]])   //최소값만 저장함
                                min = map[temp2[0]][temp2[1]];
                            break loop;
                        }
                    }
                }
            }
            map[temp[0]][temp[1]] = temp[2];
        }

        bw.write(min+"\n");
        bw.flush();

    }

    public static void dfs(int r, int c, int number) {
        boolean insert = false;
        map[r][c] = number;
        for(int i=0;i<dir.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];

            if(row < map.length && row >= 0 && col < map.length && col >= 0) {
                if(map[row][col] == 1)
                    dfs(row,col,number);
                else if(map[row][col] == 0 && !insert) {
                    q.add(new int[]{r,c,number});
                    insert = true;
                }
            }
        }
    }
}
