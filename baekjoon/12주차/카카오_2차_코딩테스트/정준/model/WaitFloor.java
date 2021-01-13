import java.util.ArrayList;

public class WaitFloor {
    private ArrayList<Integer> callIds;     //해당 층에서 기다리는 사람 목록
    private ArrayList<Integer> endFloors;   //callIds의 목적지 목록

    public WaitFloor(ArrayList<Integer> callsId, ArrayList<Integer> endFloors) {
        this.callIds = callsId;
        this.endFloors = endFloors;
    }

    public ArrayList<Integer> getCallIds() {
        return callIds;
    }

    public ArrayList<Integer> getEndFloors() {
        return endFloors;
    }

    public void setEndFloors(ArrayList<Integer> endFloors) {
        this.endFloors = endFloors;
    }

    public void setCallIds(ArrayList<Integer> callIds) {
        this.callIds = callIds;
    }

}
