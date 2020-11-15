import java.io.*;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;

public class Baekjoon9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        boolean[] check;
        int[] arr;
        Stack<Integer> s = new Stack<>();

        int t = Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        for(int k=0;k<t;k++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            st = new StringTokenizer(br.readLine()," ");
            check = new boolean[n];
            for(int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(st.nextToken())-1;                
            }
            s.push(arr[0]);
            while(!s.isEmpty()) {
                if(!check[s.peek()]) {
                    
                }
            }
                
            
            



        }
    }
}
