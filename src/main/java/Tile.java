import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Tile extends Position {
    Tile() {
        URL loc = this.getClass().getClassLoader().getResource("tile 2.png");
        BufferedImage i = null;
        try {
            i = ImageIO.read(loc);
        } catch (IOException e) {
            System.out.println("Failed to read image!");
        }
        this.setImage(i);
    }

    Tile(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getClassLoader().getResource("tile 2.png");
        BufferedImage i = null;
        try {
            i = ImageIO.read(loc);
        } catch (IOException e) {
            System.out.println("Failed to read image!");
        }
        this.setImage(i);
    }

    public int getWidth() {
        return this.getImage().getWidth();
    }
}
