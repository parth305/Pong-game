package Pong2;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    int xvelocity;
    int yvelocity;
    Random random;
    int intialspped=5;

    Ball(int x,int y,int diameter){
      super(x,y,diameter,diameter);
      random=new Random();

      int randomxdirection=random.nextInt(2);
        if (randomxdirection==0)
            randomxdirection=-1;
      setxdirection(randomxdirection*intialspped);

        int randomydirection=random.nextInt(2);
        if (randomydirection==0)
            randomydirection=-1;
        setydirection(randomydirection*intialspped);

    }

    public void setxdirection(int randomxdirection){
        xvelocity=randomxdirection;
    }

    public void setydirection(int randomydirection){
        yvelocity=randomydirection;
    }

    public void move(){
        x=x+xvelocity;
        y=y+yvelocity;
    }

    public void draw(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x,y,width,height);
    }
}
