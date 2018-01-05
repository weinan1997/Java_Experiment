import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Creature {
    public boolean Greater(Creature another){
        return false;
    }
    public void report() {

    }
    private Position position;
    private String figure = "🌭";
    private BufferedImage image;

    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }

    public String getFigure() {
        return figure;
    }
    public void setFigure(String figure) {
        this.figure = figure;
    }

    public void setImage(String url) {
        URL loc = this.getClass().getClassLoader().getResource(url);
        BufferedImage i = null;
        try{
            i = ImageIO.read(loc);
        } catch (IOException e) {
            System.out.println("failed to read image!");
        }
        image = i;
    }
    public BufferedImage getImage() {
        return image;
    }
}
