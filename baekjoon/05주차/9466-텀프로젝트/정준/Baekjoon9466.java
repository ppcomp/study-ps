import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon9466 {
    public static int count;
    public static int[] checked;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        for(int k=0;k<t;k++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            checked = new int[n];
            count = 0;
            
            st = new StringTokenizer(br.readLine()," ");
            //학생 번호는 사용하기 편하게 0부터 시작함
            for(int i=0;i<n;i++) 
                arr[i] = Integer.parseInt(st.nextToken())-1;

            for(int i=0;i<n;i++) {
                if(checked[i] == 0) //한번도 방문한 적 없는 노드만 탐색함
                    dfs(i,arr,checked);
            }
            bw.write(n-count+"\n");
        }
        bw.flush();
    }

    public static void dfs(int vector, int[] arr, int[] checked) {
        //checked {0: 방문안함, 1: 한번 방문함, -1: 탐색 끝남}
        checked[vector] = 1;
        int next = arr[vector];

        if(checked[next] == 0)
            dfs(next,arr,checked);
        else {
            if(checked[next] == 1) {    //다음 노드에 방문한 적 있음
                count++;
                for(int i=next;i != vector; i = arr[i]) //다음 노드와 연결된 다른 노드들도 같은 팀
                    count++;
            }
        }
        checked[vector] = -1;    //탐색 끝남
    }
}
