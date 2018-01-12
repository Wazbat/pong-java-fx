package warren.pongfx;
import static java.time.Clock.system;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    //Variables
    int velbolax = 3;
    int velbolay = 3;
    int bolaposx = 20;
    int bolaposy = 10;
    int bolagrande = 7;
    int barra1posx = 5;
    int barra1posy = 50;
    
    int alturaventana=400;
    int anchuraventana=600;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, anchuraventana, alturaventana);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("PongFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
        
        Rectangle bola = new Rectangle(bolaposx, bolaposy,7, 7);
        bola.setFill(Color.WHITE);
        Rectangle player1 = new Rectangle(barra1posx, barra1posy, 10, 40);
        player1.setFill(Color.WHITE);
        root.getChildren().addAll(bola,player1);
        AnimationTimer animacion = new AnimationTimer() {
            @Override
            public void handle(long now){
                bolaposx+=velbolax;
                bolaposy+=velbolay;
                if (bolaposx >=anchuraventana-bolagrande){
                    velbolax=-3;
                }
                if (bolaposx <0){
                    velbolax=3;
                }
                if (bolaposy >= alturaventana-bolagrande){
                    velbolay=-3;
                }
                if (bolaposy <0){
                    velbolay=3;
                }
                bola.setX(bolaposx);
                bola.setY(bolaposy);
                System.out.println(bolaposy);
            };
        };
        animacion.start();
        }
       
    }   
   
    


