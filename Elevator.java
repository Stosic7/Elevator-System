import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
    private int currentFloor;
    private String direction; // "UP", "DOWN", or "IDLE"
    private Queue<Request> requests;

    public Elevator() {
        this.currentFloor = 0; // Default starting floor
        this.direction = "IDLE";
        this.requests = new LinkedList<>();
    }

    public void addRequest(Request request) {
        requests.add(request);
        if (direction.equals("IDLE")) {
            direction = request.getStartFloor() > currentFloor ? "UP" : "DOWN";
        }
    }

    public void move() {
        if (!requests.isEmpty()) {
            Request currentRequest = requests.peek();
            if (currentFloor < currentRequest.getStartFloor()) {
                currentFloor++;
                direction = "UP";
            } else if (currentFloor > currentRequest.getStartFloor()) {
                currentFloor--;
                direction = "DOWN";
            } else {
                // Arrived at start floor, now move to destination
                if (currentFloor < currentRequest.getDestinationFloor()) {
                    currentFloor++;
                    direction = "UP";
                } else if (currentFloor > currentRequest.getDestinationFloor()) {
                    currentFloor--;
                    direction = "DOWN";
                } else {
                    // Arrived at destination
                    requests.poll();
                    direction = requests.isEmpty() ? "IDLE" : direction;
                }
            }
        } else {
            direction = "IDLE";
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Elevator on floor " + currentFloor + " moving " + direction;
    }
}
