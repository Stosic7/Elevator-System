import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BuildingView extends Pane {
    private int totalFloors;
    private int elevatorWidth = 40;
    private int elevatorHeight = 30;
    private int floorHeight = 50;
    private List<Rectangle> elevatorRects;

    public BuildingView(int totalFloors, int numberOfElevators) {
        this.totalFloors = totalFloors;
        this.setPrefSize(200 + numberOfElevators * elevatorWidth, totalFloors * floorHeight);

        // Draw floors
        for (int i = 0; i <= totalFloors; i++) {
            Line floorLine = new Line(0, i * floorHeight, 200 + numberOfElevators * elevatorWidth, i * floorHeight);
            this.getChildren().add(floorLine);
            Text floorLabel = new Text(10, i * floorHeight - 10, "Floor " + (totalFloors - i));
            this.getChildren().add(floorLabel);
        }

        // Create elevator rectangles
        elevatorRects = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            Rectangle elevator = new Rectangle(200 + i * elevatorWidth, (totalFloors - 1) * floorHeight - elevatorHeight, elevatorWidth, elevatorHeight);
            elevator.setFill(Color.BLUE);
            this.getChildren().add(elevator);
            elevatorRects.add(elevator);
        }
    }

    public void updateElevatorPosition(int elevatorIndex, int currentFloor) {
        Rectangle elevator = elevatorRects.get(elevatorIndex);
        elevator.setY((totalFloors - currentFloor - 1) * floorHeight - elevatorHeight);
    }
}
