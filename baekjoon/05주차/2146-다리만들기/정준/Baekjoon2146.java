import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2146 {
    public static Queue<int[]> q = new LinkedList<>();  //다리길이를 탐색할 좌표 저장(바다와 인접한 좌표)
    public static int[][] map;
    public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        //지도 초기화
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) 
                map[i][j] = Integer.parseInt(st.nextToken());   
        }
        //대륙의 숫자를 매겨주는 깊이우선탐색, 음수로 매김
        int number = -1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(map[i][j] == 1) {
                    dfs(i,j,number--);
                }
            }
        }
        //
        int[] temp, temp2;
        int min = 9999;
        Queue<int[]> q2 = new LinkedList<>();   //q에서 뽑아낸 좌표로 너비우선탐색진행할 큐
        boolean[][] checked;    //방문했는지 확인하는 배열

        while(!q.isEmpty()) {
            temp = q.poll();    //바다에 인접한 좌표 저장
            q2 = new LinkedList<>();
            checked = new boolean[n][n];
            q2.add(temp);   //너비우선탐색을 위해 q2에 삽입
            map[temp[0]][temp[1]] = 0;  //음수로 표현된 대륙을 임시로 0으로 저장
            checked[temp[0]][temp[1]] = true;
            loop:
            while(!q2.isEmpty()) {
                temp2 = q2.poll();
                for(int i=0;i<dir.length;i++) {
                    int row = temp2[0] + dir[i][0];
                    int col = temp2[1] + dir[i][1];
                    if(row >= 0 && row < map.length& col >= 0 && col < map.length) {
                        if(map[row][col] >= 0 && !checked[row][col]) {  //탐색한 좌표가 바다일 때
                            q2.add(new int[] {row, col});
                            checked[row][col] = true;
                            map[row][col] = map[temp2[0]][temp2[1]] + 1;
                        }
                        else if(map[row][col] != temp[2] && !checked[row][col]) { //탐색한 좌표가 다른 대륙일 때
                            if(min > map[temp2[0]][temp2[1]])   //최소값만 저장함
                                min = map[temp2[0]][temp2[1]];
                            break loop;
                        }
                    }
                }
            }
            map[temp[0]][temp[1]] = temp[2]; //0으로 바꾼 대륙을 기존 음수로 다시 변경
        }

        bw.write(min+"\n");
        bw.flush();

    }

    public static void dfs(int r, int c, int number) {
        boolean insert = false; //한 좌표에서 큐에 넣는 건 한개로 제한하기 위한 변수
        map[r][c] = number;
        for(int i=0;i<dir.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];

            if(row < map.length && row >= 0 && col < map.length && col >= 0) {
                if(map[row][col] == 1)  //대륙이면 깊이우선탐색 진행
                    dfs(row,col,number);
                else if(map[row][col] == 0 && !insert) {    //(r,c)좌표에 붙어있는 좌표가 바다일 때 
                    q.add(new int[]{r,c,number});   //큐에 삽입
                    insert = true;
                }
            }
        }
    }
}
