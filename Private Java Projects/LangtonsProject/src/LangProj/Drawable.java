package LangProj;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Drawable {
    protected Color color;
    protected int x,y;
    public Drawable(int x, int y, Color color) {
        this.x = x;    this.y = y;
        this.color = color;
    }

    public void draw(GraphicsContext gc){
        Main.gc.setFill(color);
        Main.gc.fillRect(x,y,Main.getM(),Main.getM());
    }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
}
