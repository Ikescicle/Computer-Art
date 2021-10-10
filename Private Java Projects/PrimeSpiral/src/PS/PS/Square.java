package PS;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square {
    private int x, y;
    private double s;
    private Color color;
    public Square(int x,int y,int s,Color c){
        this.x = x; this.y = y;
        this.s = s;
        this.color = c;
    }
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillRect(x,y,s,s);
    }
    public Color getColor() { return color; }
    public void setColor(double c1, double c2, double c3) { color = Color.color(c1,c2,c3); }
    public void setColor(Color c) { color = c; }
}
