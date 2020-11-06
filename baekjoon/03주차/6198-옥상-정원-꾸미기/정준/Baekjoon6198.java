package additional_study;

import java.io.*;
import java.util.Stack;

public class Baekjoon6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] b = new long[n];
        Stack<Long> s = new Stack<>();


        for(int i=0;i<n;i++)
            b[i] = Long.parseLong(br.readLine());

        long hight;
        long count = 0;
        for(int i=0;i<n;i++) {
            hight = b[i];
            while(!s.isEmpty() && s.peek() <= hight)    //s.peek > hight
                s.pop();

            count += s.size();
            s.push(hight);

        }
        bw.write(count+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
