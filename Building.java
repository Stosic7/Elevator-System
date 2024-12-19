import java.util.ArrayList;
import java.util.List;

public class Building {
    private int totalFloors;
    private List<Elevator> elevators;

    public Building(int totalFloors, int numberOfElevators) {
        this.totalFloors = totalFloors;
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator());
        }
    }

    public void requestElevator(int startFloor, int destinationFloor) {
        // Simple logic to assign the first idle elevator
        for (Elevator elevator : elevators) {
            if (elevator.getDirection().equals("IDLE")) {
                elevator.addRequest(new Request(startFloor, destinationFloor));
                return;
            }
        }
        // If no idle elevators, assign to the first elevator in the list
        elevators.get(0).addRequest(new Request(startFloor, destinationFloor));
    }

    public void stepSimulation() {
        for (Elevator elevator : elevators) {
            elevator.move();
            System.out.println(elevator);
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

}
