import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Playback implements Runnable{
    private ArrayList<File> saved;
    private int count = 0;
    private Field field;

    Playback(Field f) {
        field = f;
        File fs = new File(SavePic.url);
        saved = new ArrayList<>();
        for (int i = 0; i < fs.list().length; i ++) {
            String path = SavePic.url + String.valueOf(i) + ".png";
            File temp = new File(path);
            saved.add(temp);
        }
    }


    public void play(Graphics g) {
        if (count < saved.size()) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(saved.get(count));
            } catch(IOException e) {
                System.out.println("Failed to load saved images");
            }
            g.setColor(new Color(250, 240, 170));
            g.fillRect(0, 0, Field.width, Field.height);
            g.drawImage(image, 0, 0, field);
            count++;
        }
    }

    public void run() {
        while (!Thread.interrupted() && count < saved.size()) {
            field.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
        }
    }
}
