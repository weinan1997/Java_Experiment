import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SavePic {
    public static int i = 0;
    private static final String url = "src/main/resources/save/";

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
        File f = new File(url + i + ".jpg");
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            System.out.println("Failed to save the picture");
        }
    }
}
