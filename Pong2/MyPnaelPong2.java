package Pong2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyPnaelPong2 extends JPanel implements Runnable, ActionListener {

    static final int GAME_WIDTH=1400;
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*0.5555);
    static int PADDEL_WIDTH=25;
    static int PADDEL_HEIGHT=150;
    static final int diameter=20;
    Image image;
    Graphics graphics;
    Thread thread;
    Padels padel1;
    Padels padel2;
    Score score;
    Ball ball;

    MyPnaelPong2(){

        newPadels();
        newBall();

        score=new Score(GAME_WIDTH,GAME_HEIGHT);

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new Al());

        thread=new Thread(this);
        thread.start();

       Timer timer=new Timer(10,this);
      // timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkcollision();
        repaint();
    }

    @Override
    public void run() {

        //game loop
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        while (true){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1){
                move();
                checkcollision();
                repaint();
                delta--;
            }
        }

    }

    class Al extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            padel1.keyPressed(e);
            padel2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            padel1.keyReleased(e);
            padel2.keyReleased(e);
        }
    }

    public void draw(Graphics g){

        padel1.draw(g);
        padel2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paint(Graphics g) {
        image=createImage(GAME_WIDTH,GAME_HEIGHT);
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,GAME_WIDTH,GAME_HEIGHT,this);
    }
    public void move(){
        padel1.move();
        padel2.move();
        ball.move();
    }

    public void newPadels(){
        padel1=new Padels(0,(GAME_HEIGHT/2)-(PADDEL_HEIGHT/2) ,PADDEL_WIDTH,PADDEL_HEIGHT,1);
        padel2=new Padels((GAME_WIDTH-PADDEL_WIDTH),(GAME_HEIGHT/2)-(PADDEL_HEIGHT/2) ,PADDEL_WIDTH,PADDEL_HEIGHT,2);
    }
    public void newBall(){
        ball=new Ball((GAME_WIDTH/2-diameter),(GAME_HEIGHT/2-diameter),diameter);
    }

    public void checkcollision(){

        //stoping padels from edge
        if(padel1.y<=0)
            padel1.y=0;
        if (padel1.y>=(GAME_HEIGHT-PADDEL_HEIGHT))
            padel1.y=(GAME_HEIGHT-PADDEL_HEIGHT);
        if(padel2.y<=0)
            padel2.y=0;
        if (padel2.y>=(GAME_HEIGHT-PADDEL_HEIGHT))
            padel2.y=(GAME_HEIGHT-PADDEL_HEIGHT);

        //ball with edge
        if (ball.y<=0)
            ball.setydirection(-ball.yvelocity);
        if (ball.y>=(GAME_HEIGHT-diameter))
            ball.setydirection(-ball.yvelocity);

        //ball with padel

        if (padel1.intersects(ball) && ball.x>=PADDEL_WIDTH/2){
            ball.setxdirection(-ball.xvelocity);
            ball.xvelocity++;
            if(ball.yvelocity<0)
                ball.yvelocity--;
            else
                ball.yvelocity++;

        }
        if (padel2.intersects(ball) && ball.x<(GAME_WIDTH-PADDEL_WIDTH)){
            ball.setxdirection(-ball.xvelocity);
            if(ball.yvelocity<0)
                ball.yvelocity--;
            else
                ball.yvelocity++;
        }

        //winning

        if (ball.x<=0){
            score.palyer2++;
            newPadels();
            newBall();
        }
        if (ball.x>=GAME_WIDTH){
            score.palyer1++;
            newPadels();
            newBall();
        }

    }

}
