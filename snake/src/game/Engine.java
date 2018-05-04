package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements KeyListener {

    protected static Engine start;
    protected Point SnakeHead;
    protected Point Apple;
    protected ArrayList<Point> SnakeBody;
    protected int score=0;
    protected boolean GOver;

    private char direction;
    private Rendering paint;
    private Timer timer;

    private class RemindTask extends TimerTask {

        @Override
        public void run() {

            EngineCalculations();

            paint.repaint();
            if(GOver)
            {
                timer.cancel();
                timer.purge();
                return;
            }

            timer.schedule(new RemindTask(), 1*100);
        }
    }


    public static void main(String[] arg)
    {
        start=new Engine();
    }

    public Engine() {
        timer=new Timer();
        startSettings();
        timer.schedule(new RemindTask(), 1*1000); //delay in milliseconds
    }

    private void startSettings()
    {
        JFrame window=new JFrame("snake");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize(790,625);
        window.add(paint=new Rendering());
        window.setResizable(false);

        window.addKeyListener(this);
        window.setFocusable(true);
        window.setFocusTraversalKeysEnabled(false);

        GOver=false;
        direction='s';

        SnakeHead=new Point(5,5);
        Apple=new Point(14,15);

        SnakeBody=new ArrayList();
        SnakeBody.add(new Point(5,4));
        SnakeBody.add(new Point(5,3));

        CreateApple();
    }

    private void EngineCalculations()
    {
        BodyMovement();
        HeadDirection();
        CheckHitBox();
        CheckApple();
    }

    private void CheckHitBox(){
        if(SnakeHead.x==-1|| SnakeHead.x==37 || SnakeHead.y==-1 || SnakeHead.y==27)
           GOver=true;

        for(int i=0;i<SnakeBody.size();i++)
            if(SnakeHead.x==SnakeBody.get(i).x && SnakeHead.y==SnakeBody.get(i).y)
                GOver=true;
    }

    private void CheckApple()
    {
        if(Apple.x==SnakeHead.x && Apple.y==SnakeHead.y)
        {
            score++;
            SnakeBody.add(new Point(SnakeBody.get(SnakeBody.size()-1)));
            CreateApple();
        }
    }

    private void HeadDirection(){
        switch (direction){
            case 'n':
                SnakeHead.y--;
                break;
            case 'e':
                SnakeHead.x++;
                break;
            case 's':
                SnakeHead.y++;
                break;
            case 'w':
                SnakeHead.x--;
                break;
        }
    }

    private void BodyMovement(){
        for(int i=SnakeBody.size()-1;i>0;i--)
            SnakeBody.set(i,SnakeBody.get(i-1));

        SnakeBody.set(0,new Point(SnakeHead.x,SnakeHead.y));
    }

    private void CreateApple(){
        int i=0;
        Random generator = new Random();


        while(i<SnakeBody.size()-1) {
            i++;
            Apple.x = generator.nextInt(36);
            Apple.y = generator.nextInt(26);

            for (int z = 0; i < SnakeBody.size(); z++)
                if (SnakeBody.get(z) != Apple)
                    i++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent ev) {
        if (ev.getKeyCode() == KeyEvent.VK_A && direction!='e')
            direction = 'w';

        if (ev.getKeyCode() == KeyEvent.VK_D && direction!='w')
            direction = 'e';

        if (ev.getKeyCode() == KeyEvent.VK_W && direction!='s')
            direction = 'n';

        if (ev.getKeyCode() == KeyEvent.VK_S && direction!='n')
            direction = 's';
    }
}
