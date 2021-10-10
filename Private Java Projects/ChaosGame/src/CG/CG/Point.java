package CG;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point{
    private double x,y;
    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double X){ x = X; }
    public void setY(double Y){ y = Y; }
    public void draw(GraphicsContext gc){
        gc.setFill(Color.YELLOW);
        gc.fillRect(x,y,2,2);
    }
}
