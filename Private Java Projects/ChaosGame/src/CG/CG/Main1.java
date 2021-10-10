package CG;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Main1 extends Application {
    // Stage
    private static final Group root = new Group();
    private static final Scene scene = new Scene(root);
    private static final Canvas canvas = new Canvas(720, 780);
    public static final GraphicsContext gc = canvas.getGraphicsContext2D();
    // GUI
    private Button playButton; private boolean play = false;
    private Button switchem;
    private boolean AoR = true; // Attractor = 'true', Repuslor = 'false'
    private TextArea setProbability = new TextArea();
    private TextArea setDistance = new TextArea();
    // Dots
    private ArrayList<Attractor> attractors = new ArrayList<>(0);
    private ArrayList<Repulsor> repulsors = new ArrayList<>(0);
    private Point point = new Point(360,360);
    // Variables
    private final int range = 1000000;
    private Random rand = new Random();
    private int count = 0;
    private double p; private double d;
    public void start(Stage stage) {
        // Create the Stage
        stage.setTitle("Chaos Game");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        gc.setFill(Color.BLACK);    gc.fillRect(0,0,720,720);
        gc.setFill(Color.LIGHTBLUE); gc.fillRect(0,720,720,60);
        gc.setFill(Color.SNOW);     gc.fillRect(0,720,720,2);

        // Create GUI
        playButton = new Button("Stop");    // Play
        playButton.relocate(10,730);
        playButton.setPrefSize(120,40);
        playButton.setStyle("-fx-background-color:#FE250E;-fx-font-weight:bold;");
        playButton.setOnAction(this::playHandler);
        gc.strokeLine(140,722,140,780);
        switchem = new Button("Attractor");
        switchem.relocate(150,735);
        switchem.setPrefSize(120,30);
        switchem.setStyle("-fx-font-weight:bold;");
        switchem.setOnAction(this::attractOrRepulse);
        Label pLabel = new Label("P: ");
        pLabel.relocate(290,730);
        pLabel.setPrefHeight(30);
        pLabel.setStyle("-fx-font-size:1.6em;");
        setProbability.relocate(310,730);
        setProbability.setPrefSize(100,30);
        setProbability.setStyle("-fx-font-size:1.2em;");
        Label dLabel = new Label("D:");
        dLabel.relocate(430,730);
        dLabel.setPrefHeight(30);
        dLabel.setStyle("-fx-font-size:1.6em;");
        setDistance.relocate(450,730);
        setDistance.setPrefSize(100,30);
        setDistance.setStyle("-fx-font-size:1.2em;");
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,this::addNew);
        root.getChildren().addAll(playButton,switchem,pLabel,setProbability,dLabel,setDistance);

        stage.show();
    }
    // GUI
    private void playHandler(ActionEvent p){
        play = !play;
        if(play){
            playButton.setStyle("-fx-background-color:#63FC5A;-fx-font-weight:bold;");
            playButton.setText("Play");
        }else{
            playButton.setStyle("-fx-background-color:#FC635A;-fx-font-weight:bold;");
            playButton.setText("Pause");
        }
        start();
    }
    private void attractOrRepulse(ActionEvent ar){
        if(AoR) { AoR = false; switchem.setText("Repulsor");
        }else{ AoR = true; switchem.setText("Attractor");
        }
    }
    // Mouse Events
    private void addNew(MouseEvent me){ // "release" Event, Add new Object
        double x = me.getX();
        double y = me.getY();
        if(y < 720){
            try{
                p = Double.parseDouble(setProbability.getText());
                d = Double.parseDouble(setDistance.getText());
            }catch(Exception e){ System.err.println("-! Error: Value muse be a decimal !-"); }
            if( (p<1 && p>0) && (d<1 && d>0) ){
                if(AoR){
                    attractors.add(new Attractor(x, y,p,d,Color.LIGHTBLUE));
                    attractors.get(attractors.size()-1).draw(gc);
                }else{
                    repulsors.add(new Repulsor(x, y,p,d,Color.RED));
                    repulsors.get(repulsors.size()-1).draw(gc);
                }
            }else{ System.err.println("-! Error: Input must be between 0 and 1 !-"); }
        }
    }
    // Task
    public void start(){
        Runnable task = this::runTask; //Runnable task = new Runnable(){ public void run(){ runTask(); } };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }
    public void runTask(){
        do{
            try{
                for (Attractor attractor : attractors)
                    if (attractor != null) {
                        count++;
                        System.out.println(count);
                        setCoord(point, attractor);
                        point.draw(gc);
                        Thread.sleep(10);
                    }
                for (Repulsor repulsor : repulsors)
                    if (repulsor != null) {
                        count++;
                        System.out.println(count);
                        setCoord(point, repulsor);
                        point.draw(gc);
                        Thread.sleep(10);
                    }
            }catch(InterruptedException ie){ ie.printStackTrace(); }
        }while(play);
    }
    public void setCoord(Point p, Dot ar){
        int rng = rand.nextInt() % range;
        double xNext,yNext;
        if(rng < range*ar.getP()){
            xNext = p.getX() - ar.getX();
            yNext = p.getY() - ar.getY();
            if(ar.isType()) {
                xNext *= ar.getD();
                yNext *= ar.getD();
                p.setX(xNext + ar.getX()); p.setY(yNext + ar.getY());
            }else{
                xNext /= ar.getD();
                yNext /= ar.getD();
                p.setX(xNext + ar.getX()); p.setY(yNext + ar.getY());
            }
        }
    }
    public static void main(String[] args) { launch(args); }
}
