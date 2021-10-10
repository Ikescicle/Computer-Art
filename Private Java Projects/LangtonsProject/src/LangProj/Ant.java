package LangProj;
import javafx.scene.paint.Color;

public class Ant extends Drawable{
    private Character d;    // Direction "d" = Char "N,S,E,W"
    private Color setC;
    public Ant(int x, int y, Color color, Character D){ super(x,y,color); d=D; }
    public void Move(Color c){
        if( c == Color.WHITE || c == Color.WHITESMOKE){
            setC = Color.BLACK;
            if(d=='N'){      d='W'; x-=Main.getM(); }
            else if(d=='S'){ d='E'; x+=Main.getM(); }
            else if(d=='E'){ d='N'; y-=Main.getM(); }
            else if(d=='W'){ d='S'; y+=Main.getM(); }
        } else if(c == Color.BLACK){
            setC = Color.WHITESMOKE;
            if(d=='N'){      d='E'; x+=Main.getM(); }
            else if(d=='S'){ d='W'; x-=Main.getM(); }
            else if(d=='E'){ d='S'; y+=Main.getM(); }
            else if(d=='W'){ d='N'; y-=Main.getM(); }
        } else if(c == Color.GRAY || c == Color.LIGHTGRAY){
            setC = c;
            if(d=='N'){      d='N'; y-=Main.getM(); }
            else if(d=='S'){ d='S'; y+=Main.getM(); }
            else if(d=='E'){ d='E'; x+=Main.getM(); }
            else if(d=='W'){ d='W'; x-=Main.getM(); }
        }
    }
    public Color getSetC(){ return setC; }
    public Character getD(){ return d ;}
}
