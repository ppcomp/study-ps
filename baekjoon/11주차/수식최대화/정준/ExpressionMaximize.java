import java.io.*;
import java.util.Stack;

public class ExpressionMaximize {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String expr = "50*6-3*2";
        ExpressionMaximize e = new ExpressionMaximize();
        long result = e.solution(expr);
        bw.write(result+"");
        bw.flush();
    }

    public long solution(String expression) {
        StringBuilder sb;
        Stack<Character> s;
        char[][] o = {{'*','+','-'},{'*','-','+'},{'+','*','-'},{'+','-','*'},{'-','+','*'},{'-','*','+'}};
        String[] expr = split(expression);  //입력받은 문자열을 분리
        long maxResult = 0;
        for(int l=0;l<o.length;l++) {   //경우의수만큼 순회
            sb = new StringBuilder();
            s = new Stack<>();
            for(int i=0;i<expr.length;i++) {    //문자열 배열의 갯수만큼 순회
                String token = expr[i];
                if(token.equals(String.valueOf(o[l][0])) || token.equals(String.valueOf(o[l][1])) || token.equals(String.valueOf(o[l][2]))) {   //토큰이 연산자인 경우
                    while(!s.isEmpty() && !isPriority(o[l], s.peek(), token.charAt(0))) //토큰의 우선순위가 커질 때까지 pop 
                        sb.append(s.pop()+" ");
                    s.add(token.charAt(0));  
                }
                else {  //토큰이 숫자일 경우
                    sb.append(token+" ");
                }
            }
            while(!s.isEmpty())
                sb.append(s.pop()+" ");
            String[] postfixExpr = sb.toString().split(" ");
            maxResult = Math.max(maxResult, postfixCalc(postfixExpr));
            
        }

        return maxResult;
    }

    public String[] split(String str) { //붙어있는 문자열을 분리해줌
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++) {
            char token = str.charAt(i);
            if(Character.isDigit(token)) {  //숫자
                sb.append(token);
            }
            else {  //연산자
                sb.append(" "+token+" ");
            }
        }
        return sb.toString().split(" ");
    }

    public boolean isPriority(char[] o, char stackTop , char token) {   //연산자 우선순위 판별
        int p1 = -1, p2 = -1;
        for(int i=0;i<o.length;i++) {
            if(o[i] == stackTop)
                p1 = i;
            if(o[i] == token)
                p2 = i;
        }
        
        if(p1 > p2) //스택의 끝에있는 연산자의 우선순위가 낮음
            return true;
        else    //토큰으로 받은 연산자의 우선순위가 낮음
            return false;
        
    }

    public long postfixCalc(String[] expr) {    //후위표기법 계산
        Stack<Long> s = new Stack<>();

        for(int i=0;i<expr.length;i++) {
            String token = expr[i];
            if(token.equals("*")  || token.equals("+") || token.equals("-")) {  //토큰이 연산자인 경우
                long operand2 = s.pop();    //스택에 넣어둔 숫자 pop
                long operand1 = s.pop();    //스택에 넣어둔 숫자 pop
                s.add(calc(operand1, operand2, token.charAt(0)));   //숫자 계산 후에 스택에 넣음
            }
            else {  //숫자를 만나면
                s.add(Long.parseLong(token));   //스택에 넣음
            }
        }

        return Math.abs(s.pop());   //절대값 리턴
    }

    public long calc(long operand1, long operand2, char operator) { //계산
        long result = 0;
        switch(operator) {
            case '*':
                result = operand1 * operand2;
                break;
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
        }
        return result;
    }


}
