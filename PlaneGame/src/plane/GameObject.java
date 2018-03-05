package plane;

import java.awt.*;

/**
 * Created by Administrator on 2017/8/10.
 */
public class GameObject {
    double speed;
    double x,y;
    double width,height;
    public Rectangle getRectangle()
    {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);

    }
}
