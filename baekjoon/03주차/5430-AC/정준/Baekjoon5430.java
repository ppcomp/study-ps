package week_3;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        LinkedList<String> list;
        int t = Integer.parseInt(br.readLine());
        String func;
        String inputArr;
        Boolean error;
        Boolean reverse;

        for(int i=0;i<t;i++) {
            func = br.readLine();
            br.readLine();  //not use
            inputArr = br.readLine();
            error = false;
            reverse = false;
            list = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(inputArr,",[]");

            while(st.hasMoreTokens())
                list.add(st.nextToken());

            for(int j=0;j<func.length();j++) {
                if(func.charAt(j) == 'R')
                    reverse = !reverse;
                else {  // 'D'
                    if(list.isEmpty()) {
                        error = true;
                        bw.write("error\n");
                        break;
                    }
                    else {
                        if(reverse)
                            list.removeLast();
                        else
                            list.removeFirst();
                    }
                }
            }
            if(!error) {
                bw.write("[");
                while(!list.isEmpty()) {
                    if(reverse)
                        bw.write(list.removeLast()+"");
                    else
                        bw.write(list.removeFirst()+"");
                    if(list.size() > 0)
                        bw.write(",");
                }
                bw.write("]\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
