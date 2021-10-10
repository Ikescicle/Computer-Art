package PS;

import javafx.scene.paint.Color;

public class Ant {
    private int x,y;
    private Character d = 'N'; // Direction N,S,E,W
    public Ant(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void Move(Color c){
        if(c == Color.BLUE){
            if(d=='N'){      d='W'; x-=Main.getSize(); }
            else if(d=='S'){ d='E'; x+=Main.getSize(); }
            else if(d=='E'){ d='N'; y-=Main.getSize(); }
            else if(d=='W'){ d='S'; y+=Main.getSize(); }
        }else {
            if(d=='N'){      y-=Main.getSize(); }
            else if(d=='S'){ y+=Main.getSize(); }
            else if(d=='E'){ x+=Main.getSize(); }
            else if(d=='W'){ x-=Main.getSize(); }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Character getD() { return d; }
}
