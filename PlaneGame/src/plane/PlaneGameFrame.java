package plane;

import GameUtil.*;
import images.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.LightweightPeer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static GameUtil.Constant.*;

/**
 * Created by Administrator on 2017/8/7.
 */
public class PlaneGameFrame extends MyFrame{
    Image bg = GameUtil.getImage("images/bg.png");
    Plane p =new Plane("images/露易丝.jpg" ,260,800);
    ArrayList bulletlist = new ArrayList<Bullet>();
    private Image iBuffer=null;
    public String formatDate() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 EE");
        return sdf.format(new Date());
    }
    public String calDate()    {
        Locale.setDefault(Locale.CHINA);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String weekDay=null;
        switch (cal.get(cal.DAY_OF_WEEK))
        {
            case 2: weekDay="周一";break;
            case 3:weekDay="周二";break;
            case 4:weekDay="周三";break;
            case 5:weekDay="周四";break;
            case 6:weekDay="周五";break;
            case 7:weekDay="周六";break;
            case 1:weekDay="周日";break;
            default:break;
        }
        String time = cal.get(cal.YEAR)+"年"+(cal.get(cal.MONTH)+1)+"月"+cal.get(cal.DAY_OF_MONTH)+"日"+weekDay;
        time=time+cal.get(cal.HOUR)+"时"+cal.get(cal.MINUTE)+"分"+cal.get(cal.SECOND)+"秒";
        System.out.println(time);
        return time;
    }//返回显示时间的字符串
    public void drawMyString(Graphics g,int size,String content,int sx,int sy)    {
        Font f=new Font("宋体",Font.BOLD,size);
        Font iif=g.getFont();
        g.setFont(f);
        Color c=g.getColor();
        g.setColor(Color.BLACK);
        g.drawString(content,sx,sy);
        System.out.println("已经画了字符串!");
        g.setFont(iif);
        g.setColor(c);
    }//自定义的画字符串方法
    public void GameOver(Graphics g)    {
        Font f= new Font("宋体",Font.BOLD,100);
        Font fbuffer=g.getFont();
        g.setFont(f);
        g.drawString("游戏失败!",100,400);
        g.setFont(fbuffer);
    }//游戏结束的方法
    public void drawBullet(Graphics g)    {
        for(int i=0;i<bulletlist.size();i++)
        {
            Bullet b=(Bullet)bulletlist.get(i);
            b.draw(g);
            boolean boom = b.getRectangle().intersects(p.getRectangle());
            if(boom)
            {
                System.out.println("Booooooooooooooooooooooooooooooooooooom!");
                p.setLive(false);//飞机挂掉
            }
        }
    }//画子弹
    @Override    public void paint(Graphics g) {
       g.drawImage(bg,0,0,null);
       p.draw(g);
       drawBullet(g);
       if(!p.live)   {           GameOver(g);       }
       drawMyString(g,20,formatDate(),50,60);
    }//重载paint方法
    @Override    public void launchGame() {
        super.launchGame();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("现在按下了"+e.getKeyCode()+"键");
                p.setDir(e,true);
                p.move();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("现在放开了"+e.getKeyCode()+"键");
                p.setDir(e,false);
                p.move();
            }
        });
        //生成一堆子弹
        for(int i=0;i<15;i++)
        {
            Bullet bullet=new Bullet();
            bulletlist.add(bullet);
        }

    }//运行游戏
    @Override    public void update(Graphics g) {
        if (iBuffer==null) {
            iBuffer=this.createImage(Constant.gameWidth,Constant.gameHeight);}
            Graphics gBuffer = iBuffer.getGraphics();
            paint(gBuffer);
            g.drawImage(iBuffer,0,0,null);

    }//双缓冲修复闪烁问题
    public static void main(String[] args)    {
        PlaneGameFrame planeGameFrame = new PlaneGameFrame();
        planeGameFrame.launchGame();
    }//主函数
}
