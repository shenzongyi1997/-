package images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/2.
 */
public class GameUtil {
    public static Image getImage(String path)
    {
        URL url = GameUtil.class.getClassLoader().getResource(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
