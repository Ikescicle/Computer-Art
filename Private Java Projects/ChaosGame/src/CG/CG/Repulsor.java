package CG;

import javafx.scene.paint.Color;

public class Repulsor extends Dot{
    private double d;
    private double a,b,c;
    public Repulsor(double x,double y,double probability,double distance,Color colour) {
        super(x, y, probability, colour);
    }
    public Repulsor(double x,double y, double probability,double a, double b, double c,Color colour){
        super(x,y,probability,colour);
        this.a = a; this.b = b; this.c = c;
    }
    public boolean isType() { return false; } // Repulsor = 'false
    public double getD() { return d; }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
}
