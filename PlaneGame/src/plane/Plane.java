package plane;

import images.GameUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

/**
 * Created by Administrator on 2017/8/7.
 */
public class Plane extends GameObject{
    public Plane(String imgpath, double x, double y) {
        this.img = GameUtil.getImage(imgpath);
        this.x = x;
        this.y = y;
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        speed=10;
    }
    public Plane()    {

    }
    boolean left,right,up,down;
    Image img;

    public void setLive(boolean live) {
        this.live = live;
    }

    boolean live = true;
    public void setDir(KeyEvent e,Boolean b)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                left=b;
             //   if(b)x-=speed;
                break;
            case KeyEvent.VK_RIGHT:
                right=b;
               // if(b)x+=speed;
                break;
            case KeyEvent.VK_UP:
                up=b;
                //if(b)y-=speed;
                break;
            case KeyEvent.VK_DOWN:
                down=b;
                //if(b)y+=speed;
                break;
            default:
                break;
        }
    }
    public void draw(Graphics g)
    {
        if (live) {
            g.drawImage(img,(int)x,(int)y,null);
        }
    }
    public void move()
    {
        if(left)
        {
            this.x-=speed;
        }
        if(right)
        {
            this.x+=speed;
        }
        if(up)
        {
            this.y-=speed;
        }
        if(down)
        {
            this.y+=speed;
        }
    }


}
