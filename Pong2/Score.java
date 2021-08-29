package Pong2;

import java.awt.*;

public class Score extends Rectangle {
    int palyer1=0;
    int palyer2=0;
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    Score(int GAME_WIDTH,int GAME_HEIGHT){

        this.GAME_WIDTH=GAME_WIDTH;
        this.GAME_HEIGHT=GAME_HEIGHT;

    }

    public void draw(Graphics g){

        g.setColor(Color.white);
        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
        g.setFont(new Font("Consola",Font.BOLD,50));
        g.drawString(String.valueOf(palyer1/10)+String.valueOf(palyer1%10),(GAME_WIDTH/2)-85,100);
        g.drawString(String.valueOf(palyer2/10)+String.valueOf(palyer2%10),(GAME_WIDTH/2)+20,100);
    }
}
