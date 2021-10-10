package NS;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ant {
    private double x,y;
    private Color c = Color.WHITE;
    private Character d;
    private boolean r = true; // Rotation CW = 'true', Rotation CCW = 'false'
    private final double dist = 2100.0;
    public Ant(double x, double y, Character direction){
        this.x = x; this.y = y;
        d = direction;
    } public void draw(GraphicsContext gc){
        gc.setFill(c);
        gc.fillRect(x,y, Main.getSize(), Main.getSize());
    }
    public Character getD(){ return d; }
    // Move and Turn, and Change Color
    public void move(Character d){
        if(d == 'N'){       y -= Main.getSize() / dist;
        }else if(d == 'S'){ y += Main.getSize() / dist;
        }else if(d == 'W'){ x -= Main.getSize() / dist;
        }else if(d == 'E'){ x += Main.getSize() / dist;
        }
    } public void turn(Character direction){
        if(direction == 'N'){
            if(r) this.d = 'E';
            else  this.d = 'W';
        }else if(direction == 'E'){
            if(r) this.d = 'S';
            else  this.d = 'N';
        }else if(direction == 'S'){
            if(r) this.d = 'W';
            else  this.d = 'E';
        }else if(direction == 'W'){
            if(r) this.d = 'N';
            else  this.d = 'S';
        }
    }
    public void changeColor(int n){
        if (n == 1) c = Color.YELLOW;
        else if(Main.checkCu(n)) {
            c = Color.BLUE;
            r = true; turn(d);
        } else if(Main.checkSq(n)) {
            c = Color.RED;
            r = false; turn(d);
        } else c = Color.WHITE;
    }
    public double getDist(){ return dist; }
}