package LangProj;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

/* Langton's Ant, Author: Jason Burley
        ~ Langton's Ant is a Cellular Automata where the Ant changes its
        direction contingent on the color square it's on and simultaneously
        changes the color of said square.
*/
public class Main extends Application{
    // Stage
    private static final Group root = new Group();
    private static final Scene scene = new Scene(root);
    private static final Canvas canvas = new Canvas(750, 792);
    public static GraphicsContext gc = canvas.getGraphicsContext2D();
    // Square size = m; Screen Size = s;
    private static final int m = 10;    private static final int s = 748/m;
    private int count = 0;
    // Ants & Knights
    private Area[][] square;
    private ArrayList<Ant> ants = new ArrayList<>(0);
    private ArrayList<CrazyAnt> crazyAnts = new ArrayList<>(0);
    private ArrayList<Knight> knights = new ArrayList<>(0);
    // GUI
    private static int wCount = 0;      private static int lgCount = 0;
    private static int bCount = 0;      private static int dgCount = 0;
    private static Button direction;    private Character d = 'N';
    private static Button switchem;     private int type = 1; // Ant = 1, CrazyAnt = 2, Knight = 3
    private static Button playButton;   private boolean play = false;
    // Code starts here
    public void start(Stage stage) {
        // Create the Stage
        stage.setTitle("Langton's Ant");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        gc.setFill(Color.WHITE);    gc.fillRect(0,0,750,750);
        gc.setFill(Color.GRAY);     gc.fillRect(0,750,750,2.5);
        gc.setFill(Color.DARKBLUE); gc.fillRect(0,752.5,750,40);
        gc.setFill(Color.DARKGRAY); gc.fillRect(445,757,30,30);
        gc.setFill(Color.RED);      gc.fillRect(447,759,26,26);
        Button refresh;

        // Create the Area
        square = new Area[s][s];
        for (int i=0; i<s; i++)
            for (int j=0; j<s; j++)
                square[i][j] = new Area(m*i, m*j, Color.WHITE);
        // Create GUI
        playButton = new Button("Stop");    // Play
        playButton.relocate(10,757);
        playButton.setPrefSize(100,30);
        playButton.setStyle("-fx-background-color:#FE250E;-fx-font-weight:bold;");
        playButton.setOnAction(this::playHandler);
        refresh = new Button("Refresh");    // Refresh
        refresh.relocate(120,757);
        refresh.setPrefSize(80,30);
        refresh.setOnAction(this::updateHandler);
        gc.strokeLine(370,755,370,800);
        direction = new Button("North");    // Direction
        direction.relocate(380,757);
        direction.setPrefSize(60,30);
        direction.setOnAction(this::setDirection);
        switchem = new Button("Ant");       // Switchem
        switchem.setStyle("-fx-font-weight:bold;");
        switchem.relocate(500,757);
        switchem.setPrefSize(80,30);
        switchem.setOnAction(this::setType);
        // Mouse Events
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::addNew);
        canvas.setOnMouseMoved(this::viewMouse);
        root.getChildren().addAll(playButton,refresh,direction,switchem);

        stage.show();
    }

    // Mouse Events
    private void viewMouse(MouseEvent me){  // Track the Mouse, Object follows Mouse
        if(!play){
            for (int i=0; i<s; i++)
                for (int j=0; j<s; j++)
                    square[i][j].draw(gc);
            int index = Math.max(ants.size(), crazyAnts.size());
            index = Math.max(index, knights.size());
            for(int i=0; i<index; i++){
                if(i < ants.size())
                    if(ants.get(i) != null) ants.get(i).draw(gc);
                if(i < crazyAnts.size())
                    if(crazyAnts.get(i) != null) crazyAnts.get(i).draw(gc);
                if(i < knights.size())
                    if(knights.get(i) != null) knights.get(i).draw(gc);
            }
            if(me.getX() < 740 && me.getY() < 740){
                int x = (int) Math.floor(me.getX()); x -= x%m;
                int y = (int) Math.floor(me.getY()); y -= y%m;
                Color color = Color.RED;
                if(d=='E'){ color = Color.YELLOW;
                }else if(d=='S'){ color = Color.GREEN;
                }else if(d=='W'){ color = Color.BLUE;
                } gc.setFill(color);
                if(type == 1){  gc.fillRect(x,y,m,m); }
                else if(type == 2){ gc.fillRect(x,y,m,m);
                    gc.setFill(Color.BLACK); gc.fillRect(x+4,y+4,2,2); }
                else if(type == 3){ gc.fillOval(x,y,m,m); }
            }
        }else System.err.println("-! Error: Cannot display cursor while running !-");
    }
    private void addNew(MouseEvent me){ // "release" Event, Add new Object
        if(me.getX() < 740 && me.getY() < 740){
            int x = (int) Math.floor(me.getX()); x -= x%m;
            int y = (int) Math.floor(me.getY()); y -= y%m;
            Color color = Color.RED;
            if(d=='E'){ color = Color.YELLOW;
            }else if(d=='S'){ color = Color.GREEN;
            }else if(d=='W'){ color = Color.BLUE;
            }
            if(type == 1){
                ants.add(new Ant(x,y,color,d));
                ants.get(ants.size()-1).draw(gc);
            }else if (type == 2){
                crazyAnts.add(new CrazyAnt(x,y,color,d));
                crazyAnts.get(crazyAnts.size()-1).draw(gc);
            }else if (type == 3){
                knights.add(new Knight(x,y,color,d));
                knights.get(knights.size()-1).draw(gc);
            }
        }
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
        startTask();
    }
    private void setDirection(ActionEvent di){
        if(d=='N'){       d='E'; direction.setText("East");
            gc.setFill(Color.YELLOW); gc.fillRect(447,759,26,26);
        }else if(d=='E'){ d='S'; direction.setText("South");
            gc.setFill(Color.GREEN); gc.fillRect(447,759,26,26);
        }else if(d=='S'){ d='W'; direction.setText("West");
            gc.setFill(Color.BLUE); gc.fillRect(447,759,26,26);
        }else if(d=='W'){ d='N'; direction.setText("North");
            gc.setFill(Color.RED); gc.fillRect(447,759,26,26);
        }
    }
    private void setType(ActionEvent ae){
        if(type == 1) { type = 2; switchem.setText("Crazy Ant");
        }else if(type == 2) { type = 3; switchem.setText("Knight");
        }else if(type == 3) { type = 1; switchem.setText("Ant");
        }
    }
    private void updateHandler(ActionEvent ud) {
        ants.clear();
        crazyAnts.clear();
        knights.clear();
        for(int i=0; i<s; i++)
            for(int j=0;j<s;j++){
                square[i][j].setColor(Color.WHITE);
                square[i][j].draw(gc);
            }
        count = 0;
    }

    public void startTask(){ // **Copied from FxConcurrencyExample3.java
        // Create and Instantiate a Runnable
        Runnable task = this::runTask; // Runnable task = new Runnable(){ public void run(){ runTask(); } };
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }
    // Run Object
    public void runTask() {
        int antX, antY;
        int cAntX, cAntY;
        int knightX, knightY;
        do{ count++;
            try {
                // Ant
                if(ants.size() >= 1){
                    for (int i=0; i<ants.size(); i++) {
                        if(ants.get(i) != null){
                            antX = ants.get(i).getX() / m;
                            antY = ants.get(i).getY() / m;
                            ants.get(i).Move(square[antX][antY].getColor());
                            System.out.printf("%s: (%s,%s) %s\n", count, (antX+1) *m, (antY+1)*m, ants.get(i).getD());
                            try {
                                square[antX][antY].setColor(ants.get(i).getSetC());
                                square[antX][antY].draw(gc);
                            }catch(Exception e){ System.err.println("-! Error: Ant is out of bounds !-"); break; }
                            ants.get(i).draw(gc);
                        }
                    }
                }
                // Crazy Ant
                if(crazyAnts.size() >= 1){
                    for (int i=0; i<crazyAnts.size(); i++) {
                        if(crazyAnts.get(i) != null){
                            cAntX = crazyAnts.get(i).getX() / m;
                            cAntY = crazyAnts.get(i).getY() / m;
                            crazyAnts.get(i).Move(square[cAntX][cAntY].getColor());
                            System.out.printf("%s: (%s,%s) %s\n", count, (cAntX+1) *m, (cAntY+1)*m, crazyAnts.get(i).getD());
                            try {
                                square[cAntX][cAntY].setColor(crazyAnts.get(i).getSetC());
                                square[cAntX][cAntY].draw(gc);
                            }catch(Exception e){ System.err.println("-! Error: Ant is out of bounds !-"); break; }
                            crazyAnts.get(i).draw(gc);
                        }
                    }
                }
                // Knight
                if (knights.size() >= 1) {
                    for (int i=0; i<knights.size(); i++) {
                        if(knights.get(i) != null){
                            knightX = knights.get(i).getX() / m;
                            knightY = knights.get(i).getY() / m;
                            knights.get(i).Move(square[knightX][knightY].getColor());
                            System.out.printf("%s: (%s,%s) %s\n", count, (knightX+1) *m, (knightY+1)*m, knights.get(i).getD());
                            try {
                                square[knightX][knightY].setColor(knights.get(i).getSetC());
                                square[knightX][knightY].draw(gc);
                            }catch(Exception e){ System.err.println("-! Error: Knight is out of bounds !-"); break; }
                            knights.get(i).draw(gc);
                        }
                    }
                }
                Thread.sleep(50);
            } catch (InterruptedException ie) { ie.printStackTrace(); }
        }while(play);
        for(int i=0; i<s; i++)
            for(int j=0;j<s;j++){
                if(square[i][j].getColor() == Color.WHITESMOKE)
                    wCount++;
                else if(square[i][j].getColor() == Color.LIGHTGRAY)
                    lgCount++;
                else if(square[i][j].getColor() == Color.GRAY)
                    dgCount++;
                else if(square[i][j].getColor() == Color.BLACK)
                    bCount++;
            }
        System.out.printf("%s White, %s Light Gray, %s Dark Gray, %s Black\n",wCount,lgCount,dgCount,bCount);
        System.out.println(s*s + " Total");
    }
    public static int getM() { return m; }

    public static void main(String[] args) { launch(args); }
}
