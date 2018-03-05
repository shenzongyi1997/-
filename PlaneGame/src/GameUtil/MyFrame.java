package GameUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static GameUtil.Constant.*;

/**
 * Created by Administrator on 2017/8/3.
 * 此类的作用是提供运行窗口并且开启重画线程
 */
public class MyFrame extends Frame{
    public void launchGame()    {
        setSize(gameWidth,gameHeight);
        setLocation(initialX,initialY);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        new MyFrame.PaintThread().start();
    }//设置窗口,自动开启paint线程
    protected class PaintThread extends Thread{
        public void run(){
            while(true){
                repaint();
                try {
                    Thread.sleep(sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
