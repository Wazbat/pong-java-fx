package warren.pongfx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("PongFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        Circle circleBall = new Circle(10, 30, 7);
        circleBall.setFill(Color.WHITE);
        root.getChildren().add(circleBall);            
    }   
    
}
