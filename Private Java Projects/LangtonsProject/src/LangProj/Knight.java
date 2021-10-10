package LangProj;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Knight extends Drawable{
    private Character d;    // Direction "d" = Char "N,S,E,W"
    private Color setC;
    public Knight(int x, int y, Color color, Character D){ super(x,y,color); d=D; }
    public void Move(Color c){
        if( c == Color.WHITE || c == Color.WHITESMOKE){
            setC = Color.LIGHTGRAY;
            if(d=='N'){      d='W'; x-=2*Main.getM(); y-=Main.getM(); }
            else if(d=='S'){ d='E'; x+=2*Main.getM(); y+=Main.getM(); }
            else if(d=='E'){ d='N'; x+=Main.getM(); y-=2*Main.getM(); }
            else if(d=='W'){ d='S'; x-=Main.getM(); y+=2*Main.getM(); }
        } else if(c == Color.LIGHTGRAY){
            setC = Color.GRAY;
            if(d=='N'){      d='W'; x-=Main.getM(); y-=2*Main.getM(); }
            else if(d=='S'){ d='E'; x+=Main.getM(); y+=2*Main.getM(); }
            else if(d=='E'){ d='N'; x+=2*Main.getM(); y-=Main.getM(); }
            else if(d=='W'){ d='S'; x-=2*Main.getM(); y+=Main.getM(); }
        } else if(c == Color.GRAY){
            setC = Color.BLACK;
            if(d=='N'){      d='E'; x+=Main.getM(); y+=2*Main.getM(); }
            else if(d=='S'){ d='W'; x-=Main.getM(); y-=2*Main.getM(); }
            else if(d=='E'){ d='S'; x-=2*Main.getM(); y+=Main.getM(); }
            else if(d=='W'){ d='N'; x+=2*Main.getM(); y-=Main.getM(); }
        } else if (c == Color.BLACK) {
            setC = Color.WHITESMOKE;
            if(d=='N'){      d='E'; x+=2*Main.getM(); y+=Main.getM(); }
            else if(d=='S'){ d='W'; x-=2*Main.getM(); y-=Main.getM(); }
            else if(d=='E'){ d='S'; x-=Main.getM(); y+=2*Main.getM(); }
            else if(d=='W'){ d='N'; x+=Main.getM(); y-=2*Main.getM(); }
        }
    }
    public Color getSetC(){ return setC; }
    public Character getD(){ return d ;}
    @ Override
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillOval(x,y,Main.getM(),Main.getM());
    }
}
