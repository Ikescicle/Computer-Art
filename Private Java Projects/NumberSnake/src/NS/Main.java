package NS;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private Ant ant = new Ant(925,280,'W');
    private final Canvas canvas = new Canvas(1000, 750);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private Button button = new Button(); private boolean play = false;
    private int n = 0;
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("Number Snake");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
//        root.getChildren().add(button); button.setOnAction(this::action);

        do{ for(int i=0; i <= ant.getDist(); i++) {
                if(n == 2147483647) break;
                n++;
                ant.move(ant.getD());
                ant.changeColor(n);
            }   ant.draw(gc);
            System.out.println(n);
        }while(n <= 2147483646);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(){
        Runnable task = this::runTask; //Runnable task = new Runnable(){ public void run(){ runTask(); } };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    } public void runTask(){
        try{
            do{ for(int i=0; i <= (ant.getDist() - 20); i++) {
                    n++;
                    ant.move(ant.getD());
                    ant.changeColor(n);
                    System.out.println(n);
                }   ant.draw(gc);
                Thread.sleep(1);
            }while(play);
        }catch(InterruptedException ie){ ie.printStackTrace(); }
    }
    private void action(ActionEvent ae) { play = !play; start(); }

    public static int getSize() { return 1; } // <-- Change size here
    public static boolean checkSq(double n) {
        n = Math.pow(n, 1.0 / 2.0);
        n *= Math.pow(10,12); n = Math.round(n) / Math.pow(10,12);
        return n - Math.floor(n) == 0;
    }
    public static boolean checkCu(double n) {
        n = Math.pow(n, 1.0 / 3.0);
        n *= Math.pow(10,12); n = Math.round(n) / Math.pow(10,12);
        return n - Math.floor(n) == 0;
    }
}