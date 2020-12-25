import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ParenthesesConversion {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String test = "()))((()";
        ParenthesesConversion p = new ParenthesesConversion();

        bw.write(p.solution(test));
        bw.flush();

    }

    public String solution(String p) {
        if(p.length() == 0)
            return "";
        else {
            String answer = "";
            Queue<Character> s = new LinkedList<Character>();
            int open = 0, close = 0;
            StringBuilder sb = new StringBuilder();
            String u = "", v = "";
            for(int i=0;i<p.length();i++) {
                s.offer(p.charAt(i));
                if(p.charAt(i) == '(')
                    open++;
                else 
                    close++;

                if(open != 0 && close != 0 && open == close) {  //더이상 분리할 수 없는 균형잡힌 문자열
                    while(!s.isEmpty()) 
                        sb.append(s.poll());
                    
                    u = sb.toString();  //지금까지 나왔던 문자열은 u에 저장, 이후의 문자열은 v

                    if(open+close >= p.length())    //v가 빈 문자열인 경우
                        v = "";
                    else
                        v = p.substring(open+close);    //u 다음 문자열부터는 v

                    break;
                }
            }

            if(isCorrect(u)) {  //u가 올바른 괄호이면 v를 위의 알고리즘 다시 수행하고 u에 이어붙임
                u += solution(v);
                return u;
            }
            else {  //u가 올바른 괄호가 아니면
                answer += "(" + solution(v) + ")";  //v를 재귀적으로 수행한 결과에 괄호를 붙임
                answer += convertPrnth(u);  //u의 첫번째, 마지막 문자 제거하고 나머지 문자열 괄호 방향 바꾸고 뒤에 붙임

                return answer;
            }
        }
    }

    public boolean isCorrect(String p) {    //올바른 괄호 판단
        Stack<Character> s = new Stack<>();

        for(int i=0;i<p.length();i++) {
            if(p.charAt(i) == '(')
                s.push(p.charAt(i));
            else {
                if(s.isEmpty())
                    return false;
                else
                    s.pop();
            }
        }
        return true;
    }

    public String convertPrnth(String p) {  //문자열 괄호 변환
        String result = "";
        if(p.length() <= 2)
            return result;
        else {
            result = p.substring(1,p.length()-1);   //앞 뒤 문자열 제거
            result = result.replace("(", "O");  //괄호 방향 변경
            result = result.replace(")","C");
            result = result.replace("O", ")");
            result = result.replace("C","(");
            return result;
        }
    }
    
}
