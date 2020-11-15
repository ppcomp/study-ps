import java.io.*;
import java.util.LinkedList;

public class Baekjoon2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        LinkedList<Integer> list = new LinkedList<>();

        for(int i=0;i<n;i++) {
            String temp = br.readLine();
            for(int j=0;j<n;j++) {
                    map[i][j] =temp.charAt(j)-'0';
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(map[i][j] == 1) {
                    map[i][j] = -1;
                    list.add(dfs(i,j,1,map));
                }
                    
            }
        }
        list.sort(null);
        bw.write(list.size()+"\n");
        while(!list.isEmpty())
            bw.write(list.poll()+"\n");
        bw.flush();

    }

    public static int dfs(int row, int col, int count, int[][] map) {
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];
            if(r < map.length && c < map.length && r >= 0 && c >= 0) {
                if(map[r][c] == 1) {
                    map[r][c] = -1;
                    count = dfs(r,c,++count,map);
                }
            }
        }
        return count;
    }
}
