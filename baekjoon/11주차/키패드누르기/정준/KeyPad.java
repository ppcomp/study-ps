import java.io.*;

public class KeyPad {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String test = "100-200*300-500+20";
        String[] parsing = test.split("\\+|\\-|\\*");
        for(String s : parsing)
            bw.write(s+" ");
        bw.flush();
    }
    
    public String solution(int[] numbers, String hand) {
        StringBuilder result = new StringBuilder();
        int[][] keyPad = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}}; //키패드 위치 저장
        int[] left = {3,0}; //왼손 시작지점
        int[] right = {3,2};//오른손 시작지점
        int r, c;
        boolean isLeft = false;
        for(int i=0;i<numbers.length;i++) {
            r = keyPad[numbers[i]][0];  //행
            c = keyPad[numbers[i]][1];  //열

            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) //왼손만 누르는 키
                isLeft = true;
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) //오른손만 누르는 키
                isLeft = false;               
            else {  //더 가까운 손이 누르는 키
                int leftRange = Math.abs(r-left[0]) + Math.abs(c-left[1]);  //왼손과 키 사이의 거리 
                int rightRange = Math.abs(r-right[0]) + Math.abs(c-right[1]);//오른손과 키 사이의 거리

                if(leftRange < rightRange) {    //왼손이 더 가까울 때
                    isLeft = true;
                }
                else if(leftRange == rightRange) {  //양손 거리가 같을 때
                    if(hand.equals("left")) //왼손잡이 경우
                        isLeft = true;
                    else                    //오른손잡이 경우
                        isLeft = false;
                }
                else    //오른손이 더 가까울 때
                    isLeft = false;
            }

            if(isLeft) {    //왼손으로 키 누름
                left[0] = r;
                left[1] = c;
                result.append("L");
            }
            else {          //오른손으로 키 누름
                right[0] = r;
                right[1] = c;
                result.append("R");
            }
        }

        return result.toString();
    }

}
