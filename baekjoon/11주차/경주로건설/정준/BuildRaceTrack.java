import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BuildRaceTrack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        BuildRaceTrack b = new BuildRaceTrack();
        int[][] board = new int[3][3];
        int result = b.solution(board);
        bw.write(result+"");
        bw.flush();
    }

    public int n;
    public int[][] track;
    public int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public int solution(int[][] board) {
        n = board.length;
        track = board;
        int minCost = bfs(0, 0);

        return minCost;
    }

    public int bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {row,col,-1,0});    //(행,열,이전방향,코스트)
        int cost;
        int prev;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            cost = temp[3]; //코스트
            prev = temp[2]; //이전방향

            for(int i=0;i<dir.length;i++) { // i = 0:상 1:하 2:좌 3:우
                int r = dir[i][0] + temp[0];
                int c = dir[i][1] + temp[1];
    
                if(r < 0 || c < 0 || r >= n || c >= n) //배열의 범위를 벗어나는 경우
                    continue;

                if(track[r][c] != 1) {  //벽이 아닌 경우
                    int nextCost = (prev != -1 && prev != i) ? cost+600 : cost+100; //다음칸 코스트 계산
                    if(track[r][c] == 0 || track[r][c] >= nextCost) {   //처음방문하거나 기존코스트보다 계산한 코스트가 낮은 경우
                        track[r][c] = nextCost;
                        q.add(new int[] {r,c,i,nextCost});
                    }            
                }   
            }
        }
        return track[n-1][n-1];  //도착지점 코스트 저장
    }
}
