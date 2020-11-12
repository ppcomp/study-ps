package week_4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon4179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<int[]> q = new LinkedList<>();
        int R, C;
        int[] J = new int[2];   //지훈이 좌표
        String temp;
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[][] maze = new int[R][C];
        int[][] check = new int[R][C];
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

        //미로 초기화 (숫자로 치환)
        for(int i=0;i<R;i++) {
            temp = br.readLine();
            for(int j=0;j<C;j++) {
                switch (temp.charAt(j)) {
                    case '#':
                        maze[i][j] = -2;
                        break;
                    case '.':
                        maze[i][j] = -1;
                        break;
                    case 'J':
                        maze[i][j] = -1;
                        J[0] = i;
                        J[1] = j;
                        break;
                    case 'F':
                        maze[i][j] = 0;
                        q.add(new int[] {i,j}); //큐에 불 좌표를 넣음
                }
            }
        }

        int[] p;
        //불 탐색
        while(!q.isEmpty()) {   //큐에 불 좌표가 들어있음
            p = q.poll();
            check[p[0]][p[1]] = 1;
            for(int i=0;i<dir.length;i++) {
                int row = p[0] + dir[i][0];
                int col = p[1] + dir[i][1];
                if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
                    if(maze[row][col] == -1) {  //지나갈 수 있는 공간이면
                        q.add(new int[] {row, col});
                        check[p[0]][p[1]] = 1;
                        maze[row][col] = maze[p[0]][p[1]] + 1;
                    }
                }
            }
        }

        q.add(J);   //큐에 지훈이 좌표를 넣음
        int time = -1;
        maze[J[0]][J[1]] = 0;   //지훈이 좌표 마킹
        check = new int[R][C];  //방문 배열 초기화
        //지훈이 탐색
        loop:
        while(!q.isEmpty()) {
            p = q.poll();
            check[p[0]][p[1]] = 1;
            for(int i=0;i<dir.length;i++) {
                int row = p[0] + dir[i][0];
                int col = p[1] + dir[i][1];

                if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
                    if(check[row][col] == 0) {
                        if(maze[row][col] > maze[p[0]][p[1]]+1 || maze[row][col] == -1) {
                            //불이 번져있는 좌표의 숫자보다 작거나 빈공간(-1)일 경우
                            q.add(new int[] {row, col});
                            check[p[0]][p[1]] = 1;
                            maze[row][col] = maze[p[0]][p[1]] + 1;
                        }
                    }
                }
                else {  //미로의 범위를 벗어나면 (탈출)
                    time = maze[p[0]][p[1]] + 1;
                    break loop;
                }
            }
        }
        bw.write((time != -1)?(time+""):"IMPOSSIBLE");
        bw.flush();
        bw.close();
    }
}
