import com.google.gson.*;
import model.Call;
import model.Command;
import model.Elevator;
import model.WaitFloor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Simulation {
    public static final String COMMAND_STOP = "STOP";
    public static final String COMMAND_UP = "UP";
    public static final String COMMAND_DOWN = "DOWN";
    public static final String COMMAND_OPEN = "OPEN";
    public static final String COMMAND_CLOSE = "CLOSE";
    public static final String COMMAND_ENTER = "ENTER";
    public static final String COMMAND_EXIT = "EXIT";
    public static final String COMMAND_NULL = "NULL";

    public static final String STATUS_STOPPED = "STOPPED";
    public static final String STATUS_OPENED =  "OPENED";
    public static final String STATUS_UPWARD = "UPWARD";
    public static final String STATUS_DOWNWARD = "DOWNWARD";

    public static String baseUrl = "http://localhost:8000"; //api 서버 기본 주소
    public static Gson gson = new GsonBuilder().create();   //Gson 라이브러리
    public static ArrayList<Call> calls;                //서버에서 받은 기다리는 사람 목록
    public static ArrayList<Elevator> elevators;        //엘리베이터 상태 목록
    public static HashMap<String, WaitFloor> waitFloors; //기다리는 사람이 있는 층 목록
    public static int maxFloor;                         //최대 층
    public static int maxPassenger = 8;                 //엘리베이터 최대 탑승 인원
    public static JsonParse p = new JsonParse();        //Json 파싱을 위한 클래스


    public static void main(String[] args) throws IOException {
        String user = "tester";
        int problemNum = 2;
        int elevatorNum = 4;
        if(problemNum == 0)
            maxFloor = 5;
        else
            maxFloor = 25;

        JsonElement json = start(user,problemNum,elevatorNum);              //서버 호출 시작
        String token = json.getAsJsonObject().get("token").getAsString();   //response json 데이터에서 토큰값 파싱
        boolean isEnd = json.getAsJsonObject().get("is_end").getAsBoolean();

        while(!isEnd) { //사람들을 모두 이동시키면 종료
            json = onCalls(token);                  //현재 상태 호출
            calls = p.parsingCall(json);            //현재 기다리는 사람 목록 파싱
            elevators = p.parsingElevator(json);    //현재 엘리베이터 상태 목록 파싱
            waitFloors = p.parsingCallOfFloor(calls);//기다리는 사람 목록(calls)을 가지고 층마다 기다리는 사람 목록으로 분리
            String cmds = createCommand();          //명령어 생성
            System.out.println(cmds);
            json = action(token,cmds);
            isEnd = json.getAsJsonObject().get("is_end").getAsBoolean();
        }


    }

    public static JsonElement start(String user, int problem, int count) throws IOException {
        URL connectUrl = new URL(baseUrl+"/start/"+user+"/"+problem+"/"+count);
        HttpURLConnection con = (HttpURLConnection) connectUrl.openConnection();
        con.setRequestMethod("POST");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JsonElement element = JsonParser.parseString(response.toString());
            return element;
        }
    }

    public static JsonElement onCalls(String token) throws IOException {
        URL connectUrl = new URL(baseUrl+"/oncalls");
        HttpURLConnection con = (HttpURLConnection) connectUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Auth-Token",token);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JsonElement element = JsonParser.parseString(response.toString());
            return element;
        }
    }

    public static JsonElement action(String token, String cmds) throws IOException {
        URL connectUrl = new URL(baseUrl+"/action");
        HttpURLConnection con = (HttpURLConnection) connectUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("X-Auth-Token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);

        //Json Data 전송하는 코드
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = cmds.getBytes("utf-8");
            os.write(input,0,input.length);
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JsonElement element = JsonParser.parseString(response.toString());
            return element;
        }
    }

    public static String createCommand() {
        StringBuilder result = new StringBuilder();
        ArrayList<Command> commands = new ArrayList<>();
        for(int i=0;i<elevators.size();i++) {   //엘리베이터 갯수만큼 명령어 입력
            int floor = elevators.get(i).getFloor();
            ArrayList<Call> passengers = elevators.get(i).getPassengers();
            String status = elevators.get(i).getStatus();
            String command = COMMAND_NULL;
            String key;
            int nearCallFloor;
            int[] callIds = null;
            if(passengers.size() == 0) {    //탑승자가 없을 경우
                    key = String.valueOf(floor);
                    if(waitFloors.containsKey(key)) {
                        //엘리베이터가 위치한 층에 탑승할 사람이 있고 예약이 안되있으면
                        switch (status) {
                            case STATUS_UPWARD:
                                command = COMMAND_STOP;
                                break;
                            case STATUS_DOWNWARD:
                                command = COMMAND_STOP;
                                break;
                            case STATUS_STOPPED:
                                command = COMMAND_OPEN;
                                break;
                            case STATUS_OPENED:
                                command = COMMAND_ENTER;
                                callIds = enterCall(passengers,floor);
                        }
                    }
                    else {  //엘리베이터가 위치한 층에 탑승할 사람이 없으면
                        nearCallFloor = searchNearCall(floor);  //다른 층의 기다리는 사람 탐색
                        if(nearCallFloor == -1) {   //다른 층에 기다리는 사람이 없으면
                            switch (status) {       //정지상태 유지
                                case STATUS_UPWARD:
                                    command = COMMAND_STOP;
                                    break;
                                case STATUS_DOWNWARD:
                                    command = COMMAND_STOP;
                                    break;
                                case STATUS_STOPPED:
                                    command = COMMAND_STOP;
                                    break;
                                case STATUS_OPENED:
                                    command = COMMAND_CLOSE;
                            }
                        }
                        else {  //다른 층에 기다리는 사람이 있으면
                            boolean isNextUp;
                            if(floor < nearCallFloor)   //올라갈지 내려갈지 결정
                                isNextUp = true;
                            else
                                isNextUp = false;

                            switch (status) {   //이동
                                case STATUS_UPWARD:
                                    if(isNextUp)
                                        command = COMMAND_UP;
                                    else
                                        command = COMMAND_STOP;
                                    break;
                                case STATUS_DOWNWARD:
                                    if(isNextUp)
                                        command = COMMAND_STOP;
                                    else
                                        command = COMMAND_DOWN;
                                    break;
                                case STATUS_STOPPED:
                                    if(isNextUp)
                                        command = COMMAND_UP;
                                    else
                                        command = COMMAND_DOWN;
                                    break;
                                case STATUS_OPENED:
                                    command = COMMAND_CLOSE;
                            }
                        }
                    }
            }
            else if(isExitCall(passengers,floor) || (passengers.size() < maxPassenger && isEnterCall(floor,passengers.get(0).getEnd()))) {
                // 엘리베이터 자리가 있을 때 현재 층에서 태우거나, 내릴 인원이 있는 경우 (탑승자 있음)
                int firstPassengerTargetFloor = passengers.get(0).getEnd();
                if(isExitCall(passengers,floor)) {   //탑승자중에 목적지에 도착한 사람이 있으면
                    switch (status) {
                        case STATUS_UPWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_DOWNWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_STOPPED:
                            command = COMMAND_OPEN;
                            break;
                        case STATUS_OPENED:
                            command = COMMAND_EXIT;
                            callIds = exitCall(passengers,floor);
                    }
                }
                else if(isEnterCall(floor,firstPassengerTargetFloor)) { //태울 사람이 있으면
                    switch (status) {
                        case STATUS_UPWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_DOWNWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_STOPPED:
                            command = COMMAND_OPEN;
                            break;
                        case STATUS_OPENED:
                            command = COMMAND_ENTER;
                            callIds = enterCall(passengers,floor);
                    }

                }
            }
            else {  //지나가는 층이고 탑승자가 있는 경우 (내리는 경우랑 지나가는 경우만 존재)
                int firstPassengerTargetFloor = passengers.get(0).getEnd();
                if(firstPassengerTargetFloor == floor) {   //첫번째 탑승자가 목적지에 도착했으면
                    switch (status) {
                        case STATUS_UPWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_DOWNWARD:
                            command = COMMAND_STOP;
                            break;
                        case STATUS_STOPPED:
                            command = COMMAND_OPEN;
                            break;
                        case STATUS_OPENED:
                            command = COMMAND_EXIT;
                            callIds = exitCall(passengers,floor);
                    }
                }
                else {  //첫번째 탑승자가 목적지에 도착하지 않았으면
                    boolean isNextUp;
                    if(floor < firstPassengerTargetFloor)   //올라갈지 내려갈지
                        isNextUp = true;
                    else
                        isNextUp = false;
                    switch (status) {
                        case STATUS_UPWARD:
                            if(isNextUp)
                                command = COMMAND_UP;
                            else
                                command = COMMAND_STOP;
                            break;
                        case STATUS_DOWNWARD:
                            if(isNextUp)
                                command = COMMAND_STOP;
                            else
                                command = COMMAND_DOWN;
                            break;
                        case STATUS_STOPPED:
                            if(isNextUp)
                                command = COMMAND_UP;
                            else
                                command = COMMAND_DOWN;
                            break;
                        case STATUS_OPENED:
                            command = COMMAND_CLOSE;
                    }
                }
            }
            Command cmd;
            if(callIds == null)
                cmd = new Command(i,command);
            else
                cmd = new Command(i,command,callIds);
            commands.add(cmd);

        }
        result.append("{\"commands\":");
        result.append(gson.toJson(commands));
        result.append("}");

        return result.toString();
    }

    public static int searchNearCall(int floor) {   //탑승객이 있는 가장 가까운 층을 찾는 함수
        int i = 1;
        String key;
        while(i<maxFloor) {
            if(floor+i <= maxFloor) {
                key = String.valueOf(floor + i);
                if(waitFloors.containsKey(key))
                    return Integer.valueOf(key);
            }
            if(floor-i >= 1) {
                key = String.valueOf(floor - i);
                if(waitFloors.containsKey(key))
                    return Integer.valueOf(key);
            }
            i++;
        }
        return -1;
    }

    public static int[] exitCall(ArrayList<Call> passengers, int nowFloor) {    //내리는 사람 목록 반환
        int arrLength = 0;
        for (int i = 0; i < passengers.size(); i++) {
            if(passengers.get(i).getEnd() == nowFloor)
                arrLength++;
        }
        int[] result = new int[arrLength];
        int idx = 0;
        for (int i = 0; i < passengers.size(); i++) {
            if(passengers.get(i).getEnd() == nowFloor)
                result[idx++] = passengers.get(i).getId();
        }
        return  result;
    }

    public static int[] enterCall(ArrayList<Call> passengers, int nowFloor) {   //탑승하는 사람 목록 반환
        Integer[] temp;
        int[] result;
        int freeSpace = maxPassenger - passengers.size(); //현재 탈 수 있는 최대 인원 수
        String key = String.valueOf(nowFloor);
        WaitFloor waitFloor = waitFloors.get(key);
        ArrayList<Integer> waitPeople = waitFloor.getCallIds();
        ArrayList<Integer> enterPeople = new ArrayList<>();  //탑승하는 인원 목록
        for (int i = 0; i < freeSpace; i++) {
            if(!waitPeople.isEmpty())
                enterPeople.add(waitPeople.remove(0));
            else
                break;
        }
        temp = enterPeople.toArray(new Integer[enterPeople.size()]);
        result = Arrays.stream(temp).mapToInt(i->i).toArray();
        waitFloor.setCallIds(waitPeople);
        if(waitPeople.isEmpty())
            waitFloors.remove(key);
        else
            waitFloors.put(key,waitFloor);
        return  result;
    }

    public static boolean isExitCall(ArrayList<Call> passengers , int nowFloor) {   //내리는 사람 있는지 확인
        for (int i = 0; i < passengers.size(); i++) {   //내리는 승객 있음
            if(passengers.get(i).getEnd() == nowFloor) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEnterCall(int nowFloor, int targetFloor) {              //탑승하는 사람 있는지 확인
        String key = String.valueOf(nowFloor);
        if(waitFloors.containsKey(key)) {    //타는 승객 있음 (타겟층보다 멀면 안됨)
            WaitFloor waitFloor = waitFloors.get(key);
            ArrayList<Integer> idArr = waitFloor.getCallIds();
            ArrayList<Integer> endFloorArr = waitFloor.getEndFloors();
            boolean isUp = (nowFloor - targetFloor < 0)?true:false; //올라가는지 내려가는지
            for (int i = 0; i < endFloorArr.size(); i++) {
                int endFloor = endFloorArr.get(i);
                if(isUp && endFloor <= targetFloor)         //타켓층보다 가까운곳에 내리는 경우
                    return true;
                else if(!isUp && endFloor >= targetFloor)   //타켓층보다 가까운곳에 내리는 경우
                    return true;
            }
        }
        return false;
    }
}
