import com.google.gson.annotations.SerializedName;

public class Command {
    @SerializedName("elevator_id")
    private int elevatorId;
    @SerializedName("command")
    private String command;
    @SerializedName("call_ids")
    private int[] callIds;

    public Command(int elevatorId, String command) {
        this.elevatorId = elevatorId;
        this.command = command;
    }

    public Command(int elevatorId, String command, int[] callIds) {
        this.elevatorId = elevatorId;
        this.command = command;
        this.callIds = callIds;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int[] getCallIds() {
        return callIds;
    }

    public void setCallIds(int[] callIds) {
        this.callIds = callIds;
    }
}
