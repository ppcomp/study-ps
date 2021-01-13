
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import model.Call;
import model.Elevator;
import model.WaitFloor;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonParse {
    private Gson gson;

    public JsonParse() {
        gson = new GsonBuilder().create();
    }

    public ArrayList<Elevator> parsingElevator(JsonElement jsonElement) {   //엘레베이터 목록 파싱
        ArrayList<Elevator> elevators = new ArrayList<>();
        JsonElement elevatorJson = jsonElement.getAsJsonObject().get("elevators");
        if(elevatorJson.isJsonArray()) {
            JsonArray elevatorArr = elevatorJson.getAsJsonArray();
            for(int i=0;i<elevatorArr.size();i++) {
                Elevator elevator = gson.fromJson(elevatorArr.get(i),Elevator.class);
                elevators.add(elevator);
            }
        }
        return elevators;
    }

    public ArrayList<Call> parsingCall(JsonElement jsonElement) {
        ArrayList<Call> calls = new ArrayList<>();
        JsonElement callJson = jsonElement.getAsJsonObject().get("calls");
        if(callJson.isJsonArray()) {
            JsonArray callArr = callJson.getAsJsonArray();
            for(int i=0;i<callArr.size();i++) {
                Call call = gson.fromJson(callArr.get(i),Call.class);
                calls.add(call);
            }
        }
        return calls;
    }

    public HashMap<String, WaitFloor> parsingCallOfFloor(ArrayList<Call> calls) {
        HashMap<String, WaitFloor> result = new HashMap();
        WaitFloor waitFloor;
        for(int i=0;i<calls.size();i++) {
            Call temp = calls.get(i);
            String key = String.valueOf(temp.getStart());
            ArrayList<Integer> idArr;
            ArrayList<Integer> endFloorArr;
            if(!result.containsKey(key)) {  //키가 해쉬맵에 없으면
                idArr  = new ArrayList<>();
                endFloorArr = new ArrayList<>();
                idArr.add(temp.getId());
                endFloorArr.add(temp.getEnd());
                waitFloor = new WaitFloor(idArr,endFloorArr);
                result.put(key,waitFloor);
            }
            else {  //키가 해쉬맵에 있으면
                waitFloor = result.get(key);
                waitFloor.getCallIds().add(temp.getId());
                waitFloor.getEndFloors().add(temp.getEnd());
                result.put(key,waitFloor);
            }

        }
        return result;
    }
}
