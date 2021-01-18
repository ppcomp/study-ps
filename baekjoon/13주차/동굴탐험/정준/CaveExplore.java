import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class CaveExplore {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    }

    public boolean solution(int n, int[][] path, int[][] order) {
        int[] prev = new int[n];    //미리 방문해야 하는 방
        int[] next = new int[n];    //다음에 방문할 방
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] roomPath = new ArrayList[n];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < roomPath.length; i++) //초기화
            roomPath[i] = new ArrayList<>();
        
        for (int i = 0; i < path.length; i++) { //양방향 경로 저장
            int prevRoom = path[i][0];
            int nextRoom = path[i][1];
            
            roomPath[prevRoom].add(nextRoom);
            roomPath[nextRoom].add(prevRoom);
        }

        for (int i = 0; i < order.length; i++)  //이전에 방문해야하는 방들 저장
            prev[order[i][1]] = order[i][0];
        
        if(prev[0] != 0)    //0번방 이전에 방문해야 하는 방이 있으면 false
            return false;
        
        visited[0] = true;  //0번방에서 시작
        for (int i = 0; i < roomPath[0].size(); i++) //0번방과 직접 연결된 방들을 스택에 삽입
            s.add(roomPath[0].get(i));
        
        while(!s.isEmpty()) {
            int now = s.pop();
            int prevVisitedRoom = prev[now];    //현재 방 이전에 방문했어야 하는 방
            if(visited[now])    //현재 방을 방문한 적 있음
                continue;
            else if(!visited[prevVisitedRoom]) {  //현재 방 이전에 방문했어야 하는 방을 가본 적 없음
                next[prevVisitedRoom] = now;      //방문해야(next) 하는 방 목록에 현재 방 저장
                continue;
            }  

            visited[now] = true;    //현재 방 방문 표시

            for (int i = 0; i < roomPath[now].size(); i++) 
                if(!visited[roomPath[now].get(i)])
                    s.add(roomPath[now].get(i));    //현재 방과 직접 연결된 방 중에 방문 안한 방 삽입
            
            if(next[now] != 0)
                s.add(next[now]);   //현재 방 방문 이후에 갈 수 있는 방 삽입
        }

        for (int i = 0; i < n; i++)     //하나라도 방문한 적 없으면 false
            if(!visited[i])
                return false;
        
        return true;
    }
}