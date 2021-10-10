package CG;
import javafx.scene.paint.Color;

public class Attractor extends Dot{
    private double d;
    public Attractor(double x,double y,double probability,double distance,Color colour) {
        super(x, y, probability, colour);
        this.d = distance;
    }
    public Attractor(double x,double y, double probability,double a, double b, double c,Color colour){
        super(x,y,probability,colour);
    }
    public boolean isType() { return true; } // Attractor = 'true'
    public double getD() { return d; }
}
