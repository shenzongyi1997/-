package plane;

import GameUtil.Constant;

import java.awt.*;

/**
 * Created by Administrator on 2017/8/10.
 */
public class Bullet extends GameObject{
    double degree;
    public  Rectangle getRectangle()
    {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
    public void draw(Graphics g)
    {
        Color c=g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval((int)x,(int)y,(int)width,(int)height);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);
        if(x<=0||x>=Constant.gameWidth-width)
        {
            degree=Math.PI-degree;
        }
        if(y<=0||y>=Constant.gameHeight-height)
        {
            degree=-degree;
        }
        g.setColor(c);
    }
    public Bullet(){
        x=Math.random()*Constant.gameWidth;
        y=Math.random()*Constant.gameHeight;
        degree=Math.random()*Math.PI*2;
        speed=10;
        width=10;
        height=10;
    }
}
