public class Request {
    private int startFloor;
    private int destinationFloor;

    public Request(int startFloor, int destinationFloor) {
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public String toString() {
        return "Request from floor " + startFloor + " to floor " + destinationFloor;
    }
}
