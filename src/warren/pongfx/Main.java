package warren.pongfx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {
    //Variables
    int alturaventana=500;
    int anchuraventana=900;
    double velbolaactual = 3;
    double dificultad = 0.7;
    double velbolax = 3;
    double velbolay = 3;
    double bolaposx = anchuraventana/2;
    double bolaposy = alturaventana/2;
    int bolagrande = 7;
    int barra1posx = 25;
    int barra1posy = 50;
    int barra2posx = anchuraventana-35;
    int barra2posy = 50;
    int alturajugador = 60;
    double velpalo1=0;
    double velpalo2=0;
    int player1puntos=0;
    int player2puntos=0;
    double velplayer2=6;
    
     public void punto(){
            velbolaactual = 3;
            velbolax = 3;
            velbolay = 3;
            bolaposx = anchuraventana/2;
            bolaposy = alturaventana/2;
            
        }
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, anchuraventana, alturaventana);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("PongFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
        //Creacion de Objetos en la escena
        Rectangle bola = new Rectangle(bolaposx, bolaposy,7, 7);
        bola.setFill(Color.WHITE);
        Rectangle player1 = new Rectangle(barra1posx, barra1posy, 10, alturajugador);
        player1.setFill(Color.WHITE);
        Rectangle player2 = new Rectangle(barra2posx, barra2posy, 10, alturajugador);
        player2.setFill(Color.WHITE);
        //Texto de puntuacion
        Text texto = new Text();
        texto.setText("0"+"                                "+"0");
        texto.setTranslateX((anchuraventana/2)-100);
        texto.setTranslateY(20);
        texto.setTextAlignment(TextAlignment.CENTER);
        texto.setWrappingWidth(200);
        texto.setFill(Color.WHITE);
        root.getChildren().addAll(bola,player1,player2,texto);
       
        //AnimationTImer, un bucle que se repite a 60 Hz
        AnimationTimer animacion = new AnimationTimer() {
            @Override
            public void handle(long now){
                //Movimiento Palo 1
                barra1posy += velpalo1;
                player1.setY(barra1posy);
                if(barra1posy< 0){
                    barra1posy=0;
                } else{
                    if(barra1posy > alturaventana-alturajugador){
                        barra1posy = alturaventana-alturajugador;
                    }
                }
                //Movimiento Palo 2
                //Mientras que la velocidad de la bola es menor
                if(velbolaactual<=velplayer2){
                    barra2posy = (int) bola.getY() - alturajugador/2 ;
                }if(velbolaactual>velplayer2){
                    if (bolaposy < barra2posy-3+alturajugador/2){
                    velpalo2 = -velplayer2;
                }else{
                    if(bolaposy > barra2posy+3+alturajugador/2){
                        velpalo2 = velplayer2;
                    }
                }
                }
                if(barra2posy< 0){
                    barra2posy=0;
                } else{
                    if(barra2posy > alturaventana-alturajugador){
                        barra2posy = alturaventana-alturajugador;
                    }
                }
                barra2posy += velpalo2;
                player2.setY(barra2posy);
                //Fin de movimiento del palo 1
                //Collision Palo 1
                Shape collisionplayer1 = Shape.intersect(player1, bola);
                boolean player1rebote = collisionplayer1.getBoundsInLocal().isEmpty();
                if(player1rebote == false){
                    velbolax=velbolaactual;
                }
                //Collison Palo2
                Shape collisionplayer2 = Shape.intersect(player2, bola);
                boolean player2rebote = collisionplayer2.getBoundsInLocal().isEmpty();
                if(player2rebote == false){
                    velbolax=velbolaactual*-1;
                }
                
                //Movimiento y reobte de bola
                bolaposx+=velbolax;
                bolaposy+=velbolay;
                if (bolaposx >=anchuraventana-bolagrande){
                    player1puntos+=1;
                    System.out.println("Player1: "+player1puntos+" Player 2: "+player2puntos);
                    punto();
                    texto.setText(player1puntos+"                                "+player2puntos);
                    velbolax=velbolaactual*-1;
                }
                if (bolaposx <0){
                    player2puntos+=1;
                    System.out.println("Player1: "+player1puntos+" Player 2: "+player2puntos);
                    punto();
                    texto.setText(player1puntos+"                                "+player2puntos);
                    
                }
                if (bolaposy >= alturaventana-bolagrande){
                    velbolay=-velbolaactual;
                    
                    velbolaactual+=dificultad;
                    
                }
                if (bolaposy <0){
                    velbolay=velbolaactual;
                    velbolaactual+=+dificultad;
                }
                
                bola.setX(bolaposx);
                bola.setY(bolaposy);
                // Fin de movimiento de bola
                
            };
        };
        //Entradas de teclado
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case UP:
                    velpalo1= -9;
                    break;
                case DOWN:
                    velpalo1= 9;
                    break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            velpalo1=0;
        });
        
        animacion.start();
        }
       
    }   
   
    


