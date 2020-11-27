
import java.io.BufferedReader;
import java.io.InputStreamReader;

class week05_4 {
    static int count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(input.readLine());
        int cnt = 0;
        while(cnt < tc){
            int n = Integer.parseInt(input.readLine());
            int[] arr = new int[n+1];
            int[] visit = new int[n+1];
            int[] finish = new int[n+1];
            count = 0;
            String[] strNum = input.readLine().split(" ");
            
            for(int i=1; i<n+1; i++) 
                arr[i] = Integer.parseInt(strNum[i-1]);
            
            for(int i=1; i<n+1; i++)
                dfs(i, arr, visit, finish);
            
            System.out.println(n - count);

            cnt++;
        }
        input.close();
    }

    static void dfs(int now, int[] arr, int[] visit, int[] finish) { 
        if(visit[now] == 1)
            return;
        
            visit[now] = 1;
        int next = arr[now];
        
        if(visit[next] != 1)
            dfs(next, arr, visit, finish);
        else {
            if(finish[next] != 1) {
                count++;
                for(int i=next; i != now; i = arr[i])
                    count++;
            }
        }
        finish[now] = 1;
    }
}