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
        String[] exprParsing = split(expression);
        long maxResult = 0;
        for(int l=0;l<o.length;l++) {
            sb = new StringBuilder();
            s = new Stack<>();
            for(int i=0;i<exprParsing.length;i++) {
                String token = exprParsing[i];
                if(token.equals(String.valueOf(o[l][0]))  || token.equals(String.valueOf(o[l][1])) || token.equals(String.valueOf(o[l][2]))) {
                    if(s.isEmpty()) //스택이 비어있으면
                        s.add(token.charAt(0));
                    else {  //스택이 비어있지않으면
                        if(isPriority(o[l], s.peek(), token.charAt(0))) {
                            s.add(token.charAt(0));
                        }
                        else {

                            while(!s.isEmpty() && !isPriority(o[l], s.peek(), token.charAt(0))) 
                                sb.append(s.pop()+" ");
                            
                            s.add(token.charAt(0));
                        }
                    }
                }
                else {  //숫자일 경우
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

    public String[] split(String str) {
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

    public boolean isPriority(char[] o, char stackTop , char token) {
        int p1 = -1, p2 = -1;
        for(int i=0;i<o.length;i++) {
            if(o[i] == stackTop)
                p1 = i;
            if(o[i] == token)
                p2 = i;
        }
        
        if(p1 > p2)
            return true;
        else
            return false;
        
    }

    public long postfixCalc(String[] expr) {    //후위표기법 계산
        Stack<Long> s = new Stack<>();

        for(int i=0;i<expr.length;i++) {
            String token = expr[i];
            if(token.equals("*")  || token.equals("+") || token.equals("-")) {
                long operand2 = s.pop();
                long operand1 = s.pop();
                s.add(calc(operand1, operand2, token.charAt(0)));
            }
            else {  //숫자를 만나면
                s.add(Long.parseLong(token));
            }
        }

        return Math.abs(s.pop());
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
