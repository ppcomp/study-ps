package week_3;

import java.io.*;
import java.util.*;

public class Baekjoon2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] tower = new int[n];
        Stack<Integer> s = new Stack<>();
        int[] result = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++)
            tower[i] = Integer.parseInt(st.nextToken());

        s.push(0);
        for(int i=1;i<n;i++) {
            while(!s.isEmpty()) {
                if(tower[i]<=tower[s.peek()]) {
                    result[i] = s.peek()+1;
                    break;
                }
                else
                    s.pop();
            }
            s.push(i);
        }
        for(int i: result)
            bw.write(i+" ");
        bw.flush();
        bw.close();
    }
}
