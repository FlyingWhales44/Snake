package game;

import javax.swing.*;
import java.awt.*;

public class Rendering extends JPanel {

    int scale=20;


    @Override
    protected void paintComponent(Graphics g)
    {
        Engine s=Engine.start;
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(scale*s.SnakeHead.x+s.SnakeHead.x,scale*s.SnakeHead.y+s.SnakeHead.y,20 ,20);

        for(int i=0;i<s.SnakeBody.size();i++)
            g.fillRect(scale*s.SnakeBody.get(i).x+s.SnakeBody.get(i).x,scale*s.SnakeBody.get(i).y+s.SnakeBody.get(i).y,20 ,20);

        g.setColor(Color.green);
        g.fillRect(scale*s.Apple.x+s.Apple.x,scale*s.Apple.y+s.Apple.y,20 ,20);


        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.fillRect(0,567,790,20);
        g.setColor(Color.white);
        g.drawString("SCORE: "+s.score,20,580);

        if(s.GOver)
        {
            g.setColor(Color.BLACK);
            g.fillRect(150,150,490,250);
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER",310,240);
            g.drawString("YOUR SCORE: "+s.score,290,300);
        }
    }
}
