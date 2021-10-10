package LangProj;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CrazyAnt extends Drawable {
    private Character d;    // Direction "d" = Char "N,S,E,W"
    private Color setC;
    public CrazyAnt(int x, int y, Color color, Character D){ super(x,y,color); d=D; }
    public void Move(Color c){
        if( c == Color.WHITE || c == Color.WHITESMOKE){
            setC = Color.LIGHTGRAY;
            if(d=='N'){      d='W'; x-=Main.getM(); }
            else if(d=='S'){ d='E'; x+=Main.getM(); }
            else if(d=='E'){ d='N'; y-=Main.getM(); }
            else if(d=='W'){ d='S'; y+=Main.getM(); }
        } else if( c == Color.LIGHTGRAY ){
            setC = Color.GRAY;
            if(d=='N'){      d='W'; x-=Main.getM(); y-=Main.getM(); }
            else if(d=='S'){ d='E'; x+=Main.getM(); y+=Main.getM(); }
            else if(d=='E'){ d='N'; x+=Main.getM(); y-=Main.getM(); }
            else if(d=='W'){ d='S'; x-=Main.getM(); y+=Main.getM(); }
        } else if(c == Color.GRAY){
            setC = Color.BLACK;
            if(d=='N'){      d='E'; x+=Main.getM(); y+=Main.getM(); }
            else if(d=='S'){ d='W'; x-=Main.getM(); y-=Main.getM(); }
            else if(d=='E'){ d='S'; x-=Main.getM(); y+=Main.getM(); }
            else if(d=='W'){ d='N'; x+=Main.getM(); y-=Main.getM(); }
        } else if(c == Color.BLACK){
            setC = Color.WHITESMOKE;
            if(d=='N'){      d='E'; x+=Main.getM(); }
            else if(d=='S'){ d='W'; x-=Main.getM(); }
            else if(d=='E'){ d='S'; y+=Main.getM(); }
            else if(d=='W'){ d='N'; y-=Main.getM(); }
        }
    }
    public Color getSetC(){ return setC; }
    public Character getD(){ return d ;}
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillRect(x,y,Main.getM(),Main.getM());
        gc.setFill(Color.BLACK); gc.fillRect(x+4,y+4,2,2);
    }
}
