package week_4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n][m];
        int[][] check = new int[n][m];  //방문한지 확인하는 2차원 배열
        String temp;
        // maze init
        for(int i=0;i<n;i++) {
            temp = br.readLine();
            for(int j=0;j<m;j++) {
                maze[i][j] = Character.getNumericValue(temp.charAt(j));
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};  //인접좌표 순회에 사용하는 배열
        q.add(new int[] {0,0}); //시작좌표 삽입
        int[] tmp;

        while(!q.isEmpty()) {
            tmp = q.poll();
            check[tmp[0]][tmp[1]] = 1;
            for(int i=0;i<dir.length;i++) {
                int row = tmp[0] + dir[i][0];
                int col = tmp[1] + dir[i][1];
                //범위 안에 있을 때만 순회
                if(row < maze.length && col < maze[0].length && row >= 0 && col >= 0) {
                    //벽이 아니고 방문한적 없으면 큐에 삽입
                    if(check[row][col] == 0 && maze[row][col] != 0) {
                        q.add(new int[] {row,col});
                        check[row][col] = 1;
                        maze[row][col] = maze[tmp[0]][tmp[1]] + 1;  //인접 좌표의 숫자 + 1
                    }
                }
            }
        }
        bw.write(maze[n-1][m-1]+"");    //n,m 좌표값 저장
        bw.flush();
        bw.close();
    }
}
