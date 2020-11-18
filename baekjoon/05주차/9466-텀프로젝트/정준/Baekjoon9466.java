import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon9466 {
    public static int count;
    public static int[] checked;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //어디든 순환함
        int t = Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        for(int k=0;k<t;k++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            checked = new int[n];
            count = 0;
            
            st = new StringTokenizer(br.readLine()," ");
            for(int i=0;i<n;i++) 
                arr[i] = Integer.parseInt(st.nextToken())-1;

            for(int i=0;i<n;i++) {
                if(checked[i] == 0)
                    dfs(i,arr,checked);
            }
            bw.write(n-count+"\n");
        }
        bw.flush();
    }

    public static void dfs(int vector, int[] arr, int[] checked) {
        //visited {0: 방문안함, 1: 방문함, 2: 방문해서 싸이클을 만듬}
        checked[vector] = 1;
        int next = arr[vector];

        if(checked[next] == 0)
            dfs(next,arr,checked);
        else {
            if(checked[next] == 1) {
                count++;
                for(int i=next;i != vector; i = arr[i])
                    count++;
            }
        }

        checked[vector] = 2;
    }
}
