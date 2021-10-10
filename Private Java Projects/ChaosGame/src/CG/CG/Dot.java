package CG;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Dot {
    private double x,y;
    private double p; private double d;
    private double a,b,c;
    private Color colour;
    private boolean type;
    public Dot(double x, double y, double probability, Color colour){
        this.x = x; this.y = y;
        this.p = probability;
        this.colour = colour;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(colour);
        gc.fillOval(x,y,5,5);
        gc.setFill(Color.BLACK);
        gc.fillOval(x+1,y+1,3,3);
    }
    public boolean isType(){ return type; }
    public double getX() { return x; }
    public double getY() { return y; }

    public double getP() { return p; }
    public double getD() { return d; }

    public double getA(){ return a; }
    public double getB() { return b; }
    public double getC() { return c; }
}
