import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Creature implements Runnable{
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

    public boolean move(int x, int y) {
        Position p = this.getPosition();
        int nx = p.getX() + x;
        int ny = p.getY() + y;
        if(nx < 0 || nx >= BattleGround.getN() || ny < 0 || ny >= BattleGround.getN())
            return false;
        Position newPosition = BattleGround.getPosition(nx, ny);
        if(!newPosition.getEmpty())
            return false;
        this.setPosition(newPosition);
        newPosition.setCreature(this);
        BattleGround.setPosition(nx, ny, newPosition);
        p.setNull();
        BattleGround.setPosition(p.getX(), p.getY(), p);
        return true;
    }

    public boolean oneStepMove(DIRECTION direction) {
        switch (direction) {
            case UP: return move(-1, 0);
            case DOWN: return move(1, 0);
            case LEFT: return move(0, -1);
            case RIGHT: return move(0, 1);
            default: return false;
        }


    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            DIRECTION direction = DIRECTION.values()[rand.nextInt(4)];
            synchronized (Field.moveLock) {
                while (!this.oneStepMove(direction))
                    direction = DIRECTION.values()[rand.nextInt(4)];
                try {

                    Thread.sleep(500);
                    GraphicBG.getField().repaint();

                } catch (Exception e) {

                }
            }
        }
    }
}

enum DIRECTION {
    UP, DOWN, LEFT, RIGHT
}
