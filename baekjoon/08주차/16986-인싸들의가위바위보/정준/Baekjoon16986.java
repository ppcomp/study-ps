import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon16986 {
    public static int n;
    public static int k;
    public static int[][] rule;
    public static int[] jiwoo;
    public static boolean[] usedHand;
    public static int[][] friends;
    public static int[] wins;
    public static int[] cursor;
    public static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        rule = new int[n][n];
        jiwoo = new int[n];         //지우의 내는 순서
        usedHand = new boolean[n];  //내는 순서 탐색시 중복 확인
        friends = new int[2][20];   //경희, 민호 내는 순서
        wins = new int[3];          //우승 수 카운트
        cursor = new int[3];        //내는 순서 커서
        result = 0;                 //결과

        for(int i=0;i<n;i++) {  //규칙 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                rule[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<20;i++)   //경희 내는 순서 입력
            friends[0][i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<20;i++)   //민호 내는 순서 입력
            friends[1][i] = Integer.parseInt(st.nextToken());
        
        dfs(0);
        bw.write(result+"");
        bw.flush();
    }

    public static void dfs(int d) {  //지우가 낼 수 있는 경우의 수 탐색
        if(d == n) { //끝까지 탐색했을 때
            wins = new int[3];
            cursor = new int[3];
            int r = game(0, 1);
            if(r == 0)
                result = 1;
        }
        else {  //끝까지 탐색 못했을 때
            for(int i=0;i<n;i++) {
                if(usedHand[i] == false) {
                    usedHand[i] = true;  //현재 사용하는 숫자 true로 변경
                    jiwoo[d] = i;
                    dfs(d+1);   //한층 더 탐색

                    usedHand[i] = false; //d+1 탐색 끝나면 false로 변경
                }
            }
        }
    }

    public static int game(int p1, int p2) {
        if(cursor[0]>=n)    //지우가 낼 수 있는 경우의 수 소진
            return -1;

        int win;
        if(p1 != 0 && p2 != 0)  //지우가 플레이어가 아닌 경우
            win = rule[friends[p1-1][cursor[p1]++]-1][friends[p2-1][cursor[p2]++]-1];
        else if(p1 == 0)        //첫번째 플레이어가 지우일 경우
            win = rule[jiwoo[cursor[p1]++]][friends[p2-1][cursor[p2]++]-1];
        else                    //두번째 플레이어가 지우일 경우
            win = rule[friends[p1-1][cursor[p1]++]-1][jiwoo[cursor[p2]++]];
        
        if(win == 2)        //p1의 승리
            win = p1;
        else if(win == 0)   //p2의 승리
            win = p2;
        else                //무승부일 경우 순서가 뒤인 사람 승리
            win = Math.max(p1, p2);
        wins[win]++;

        if(wins[win] == k)  //승수가 k에 도달하면 반환
            return win;
        
        return game(win, 3-p1-p2);
    }

}