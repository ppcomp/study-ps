package week_3;

import java.io.*;
import java.util.*;

public class Baekjoon4889 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> s;
        int line = 1;
        int changeCount = 0;

        String str = br.readLine();

        while(!str.contains("-")) {
            s = new Stack<>();
            for(int i=0; i<str.length();i++) {
                if(!s.isEmpty()) {
                    if(str.charAt(i) == '{')
                        s.push(str.charAt(i));
                    else
                        s.pop(); // 짝이 맞으면 내보냄
                }
                else {  //스택이 비어있을 경우
                    if(str.charAt(i) == '{')
                        s.push(str.charAt(i));
                    else {  // '}' 일 경우
                       s.push('{'); //방향을 바꿔서 스택에 삽입
                       changeCount++;
                    }
                }
            }
            if(!s.isEmpty())
                changeCount += s.size()/2;  //스택에 쌓여있는 '{' 절반을 바꿔줌

            bw.write(line++ +". "+changeCount+"\n");
            str = br.readLine();
            changeCount = 0;
        }
        bw.flush();
        bw.close();
    }
}
