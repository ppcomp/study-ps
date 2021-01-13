import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Elevator {
    @SerializedName("id")
    private int id;
    @SerializedName("floor")
    private int floor = 1;
    @SerializedName("passengers")
    private ArrayList<Call> passengers;
    @SerializedName("status")
    private String status = "STOPPED";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ArrayList<Call> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Call> passengers) {
        this.passengers = passengers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
