import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class JewelryShopping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] test = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

        JewelryShopping j = new JewelryShopping();
        int[] result = j.solution(test);
        
        
    }

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();  //보석의 종류를 저장해주는 HashSet
        HashMap<String,Integer> map = new HashMap<>();  //보석이 나온 갯수 카운트해주는 HashMap
        Queue<String> q = new LinkedList<>();   //슬라이싱해줄 큐
        int minLength = gems.length+1;          //최소길이
        int startPos = 0;                       //탐색하면서 저장되는 시작위치
        int startIndex = 0;                     //최종 시작위치

        for(int i=0;i<gems.length;i++)  //HashSet 데이터 저장 
            set.add(gems[i]);
        
        for(int i=0;i<gems.length;i++) {
            if(map.containsKey(gems[i]))    //hASHmAP에 데이터가 있으면
                map.put(gems[i], map.get(gems[i])+1);   //카운트 증가
            else
                map.put(gems[i], 1);    //카운트 1로 추가

            q.add(gems[i]); //슬라이싱 큐에 보석 저장

            while(true) {
                String temp = q.peek();
                if(map.get(temp) > 1) { //맵에 두개이상 카운트 되어있으면 큐에서 방출
                    map.put(temp, map.get(temp)-1);
                    q.poll();
                    startPos++; //시작지점 한칸 이동
                }
                else
                    break;
            }

            if(map.size() == set.size() && minLength > q.size()) {  //HashMap과 HashSet에 저장된 사이즈 같으면 보석이 다들어있음
                minLength = q.size();
                startIndex = startPos;
            }
        }
        answer[0] = startIndex + 1;         //시작지점
        answer[1] = startIndex + minLength; //끝지점
        
        return answer;
    }
}
