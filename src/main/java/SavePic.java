import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SavePic {
    static int i = 0;
    public static String url;

    public static void savePic (JFrame jf) {
        if (i == 0) {
            File df = new File(url);
            File[] af = df.listFiles();
            for (int j = 0; j < af.length; j++) {
                af[j].delete();
            }
        }

        Container content = jf.getContentPane();
        BufferedImage image = new BufferedImage(jf.getWidth(),jf.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        content.printAll(g);
        File f = new File(url + i + ".png");
        try {
            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            System.out.println("Failed to save the picture");
        }
    }
}
