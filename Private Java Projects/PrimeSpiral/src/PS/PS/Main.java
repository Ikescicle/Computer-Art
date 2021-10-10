package PS;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
/* Prime Spiral, Author: Jason Burley
        ~ The Prime Spiral is a Spiral of Numbers colored in according
        to their abundance of composites.
 */
public class Main extends Application {
    private static final Group group = new Group();
    private static final Scene scene = new Scene(group);
    private static final Canvas canvas = new Canvas(750,750);
    public static GraphicsContext gc = canvas.getGraphicsContext2D();

    private double c = 0.0;
    private static final double size = 2;   private final int d = (int) (750/size);
    private static double antX, antY;

    // Create Area and Ant
    private Square[][] area = new Square[d][d];
    private Ant scanner = new Ant(375,375);
    public void start(Stage stage) {
        // Create the Stage
        stage.setTitle("Prime Spiral");
        stage.setScene(scene);
        group.getChildren().add(canvas);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,750,750);
        for(int i=0; i<d; i++)
            for(int j=0; j<d; j++)
                area[i][j] = new Square((int)(i*size),(int)(j*size),(int)(size),Color.BLUE);

        // Create list of Primes
        ArrayList<Integer> p = new ArrayList<>();
        for(int a=1; a < d*d; a++)
            if(checkPrime(a)) p.add(a);

        double twoCount = Math.floor( Math.log(d*d) / Math.log(2) );
        for(int k=1; k <= d*d; k++){
            // Create Composite Count
            int n = k;
            int comCount=0;
            for (int i=1; i<p.size(); i++) {
                for(int cC=0; cC <= twoCount; cC++){
                    if( n % p.get(i) == 0) {
                        n /= p.get(i); comCount++;
                    }
                    if(p.get(i) > n) break;
                }
            }
            System.out.printf("%s: %s\n", k, comCount);
            c = (comCount-1)/(twoCount-1);
            // Move Ant
            antX = scanner.getX() / size;
            antY = scanner.getY() / size;
            if(antX < 750 && antY < 750) {
                if (scanner.getD() == 'N') {
                    scanner.Move(area[(int)(antX-1)][(int)(antY)].getColor());
                } else if (scanner.getD() == 'S') {
                    scanner.Move(area[(int)(antX+1)][(int)(antY)].getColor());
                } else if (scanner.getD() == 'E') {
                    scanner.Move(area[(int)(antX)][(int)(antY-1)].getColor());
                } else if (scanner.getD() == 'W') {
                    scanner.Move(area[(int)(antX)][(int)(antY+1)].getColor());
                }
                if (c < 0)
                    area[(int)(antX)][(int)(antY)].setColor(Color.RED);
                else if (c == 0)
                    area[(int)(antX)][(int)(antY)].setColor(c, c, c);
                else
                    area[(int)(antX)][(int)(antY)].setColor(1-c, 1-c, 1-c);
            }
        }

        // Show the Stage
        for(int i=0; i<750/size; i++)
            for(int j=0; j<750/size; j++)
                area[i][j].draw(gc);
        stage.show();
    }
    public static double getSize() { return size; }
    public boolean checkPrime(int n){
        boolean valid = true;
        for(int i=2; i<=Math.pow(n,0.5); i++){
            if(n==2 || n==3) break;
            if(n%i == 0){ valid = false; break; }
        }
        return valid;
    }

    public static void main(String[] args) { launch(args); }
}
