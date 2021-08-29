package Pong2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Padels extends Rectangle {

    int id;
    int yVelocity;
    int speed=15;
    Padels(int x,int y,int width,int height,int id){
        super(x,y,width,height);
        this.id=id;
    }

    public void setYdirection(int yDirection){
       yVelocity=yDirection;
    }

    public void move(){
        y=y+yVelocity;
    }

    public void draw(Graphics g){
        if(id==1)
            g.setColor(Color.red);
        else
            g.setColor(Color.blue);
        g.fillRect(x,y,width,height);
    }

    public void keyPressed(KeyEvent e) {

        switch (id){
            case 1:
                if (e.getKeyCode()==KeyEvent.VK_W)
                  setYdirection(-speed);
                if (e.getKeyCode()==KeyEvent.VK_S)
                   setYdirection(speed);
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    setYdirection(speed);
                if (e.getKeyCode()==KeyEvent.VK_UP)
                    setYdirection(-speed);
                break;
        }

    }

    public void keyReleased(KeyEvent e) {

        switch (id){
            case 1:
                if (e.getKeyCode()==KeyEvent.VK_W)
                    setYdirection(0);
                if (e.getKeyCode()==KeyEvent.VK_S)
                    setYdirection(0);
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    setYdirection(0);
                if (e.getKeyCode()==KeyEvent.VK_UP)
                    setYdirection(0);
                break;

        }

    }
}
