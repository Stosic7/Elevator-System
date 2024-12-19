import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Building building;
    private BuildingView buildingView;

    @Override
    public void start(Stage primaryStage) {
        int totalFloors = 10;
        int numberOfElevators = 2;

        building = new Building(totalFloors, numberOfElevators);
        buildingView = new BuildingView(totalFloors, numberOfElevators);

        VBox root = new VBox();
        root.getChildren().add(buildingView);

        Button addRequestButton = new Button("Add Random Request");
        addRequestButton.setOnAction(e -> {
            int startFloor = (int) (Math.random() * totalFloors);
            int destinationFloor = (int) (Math.random() * totalFloors);
            while (destinationFloor == startFloor) {
                destinationFloor = (int) (Math.random() * totalFloors);
            }
            building.requestElevator(startFloor, destinationFloor);
        });
        root.getChildren().add(addRequestButton);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Elevator System Simulation");
        primaryStage.show();

        // Timeline to update simulation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> stepSimulation()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stepSimulation() {
        building.stepSimulation();

        // Update elevator positions in the view
        for (int i = 0; i < building.getElevators().size(); i++) {
            int currentFloor = building.getElevators().get(i).getCurrentFloor();
            buildingView.updateElevatorPosition(i, currentFloor);
        }
    }
}
