package week_4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] box = new int[n][m];
        int[][] check = new int[n][m];  //방문한지 확인하는 2차원 배열
        Queue<int[]> q = new LinkedList<>();
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        //box init
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1)
                    q.add(new int[] {i,j});
            }
        }
        //박스 탐색
        int[] temp;
        int finishDay = 0;
        while(!q.isEmpty()) {
            temp = q.poll();
            check[temp[0]][temp[1]] = 1;
            for(int i=0;i<dir.length;i++) {
                int row = temp[0] + dir[i][0];
                int col = temp[1] + dir[i][1];
                if(row >= 0 && row < n && col >= 0 && col < m) {
                    if(check[row][col] == 0 && box[row][col] == 0) {
                        q.add(new int[] {row, col});
                        check[row][col] = 1;
                        box[row][col] = box[temp[0]][temp[1]] + 1;
                        if(finishDay < box[row][col]-1)   //최대값만 저장함
                            finishDay = box[row][col]-1;  //박스에서 토마토값이 1부터 시작하므로 -1 해줌
                    }
                }
            }
        }

        //안익은 토마토가 있는지 확인
        loop:
        for(int i=0;i<box.length;i++) {
            for(int j=0;j<box[0].length;j++) {
                if(box[i][j] == 0) {
                    finishDay = -1;   //안익었으면 -1
                    break loop; //2중for문 탈출
                }
            }
        }

        bw.write(finishDay+"");
        bw.flush();
        bw.close();
    }
}
