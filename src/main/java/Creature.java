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
    protected double strength;
    protected CAMP camp;
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

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

    private boolean combat(Creature c1, Creature c2) {
        double winProbability = c1.strength / (c1.strength + c2.strength);
        if(Math.random() <= winProbability)
            return true;
        return false;
    }

    public boolean move(int x, int y) {
        if (Field.completed == true)
            return false;
        if (!this.isAlive())
            return false;
        Position p = this.getPosition();
        int nx = p.getX() + x;
        int ny = p.getY() + y;
        if(nx < 0 || nx >= BattleGround.getN() || ny < 0 || ny >= BattleGround.getN())
            return false;
        Position newPosition = BattleGround.getPosition(nx, ny);
        if(newPosition.getCreature() != null) {
            Creature opponent = newPosition.getCreature();
            if(this.camp == opponent.camp)
                return false;
            else {
                boolean combatResult = combat(this, opponent);
                if(combatResult) {
                    newPosition.getCreature().alive = false;
                    newPosition.getCreature().setPosition(null);
                    newPosition.setNull();
                }
                else {
                    this.alive = false;
                    this.setPosition(null);
                    p.setNull();
                    BattleGround.setPosition(p.getX(), p.getY(), p);
                    return true;
                }
            }
        }
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

    private DIRECTION findEnemy() {
        Position[][] maps = BattleGround.getMaps();
        Position p = this.getPosition();
        int N = BattleGround.getN();
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i < N; i++) {
            try {
                if (maps[i][y].getCreature() != null) {
                    if (maps[i][y].getCreature().camp != this.camp) {
                        if (i < x)
                            return DIRECTION.UP;
                        else
                            return DIRECTION.DOWN;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("At (" + i + ", " + y + ") find empty creature");
            }
        }
        for(int j = 0; j < N; j++) {
            try {
                if (maps[x][j].getCreature() != null) {
                    if (maps[x][j].getCreature().camp != this.camp) {
                        if (j < y)
                            return DIRECTION.LEFT;
                        else
                            return DIRECTION.RIGHT;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("At (" + x + j + ") find empty creature");
            }
        }
        return (DIRECTION.values()[new Random().nextInt(4)]);
    }

    public void run() {
        while (!Thread.interrupted() && this.alive) {
            Random rand = new Random();
            //DIRECTION direction = DIRECTION.values()[rand.nextInt(4)];
            DIRECTION direction = findEnemy();
            synchronized (Field.moveLock) {
                this.oneStepMove(direction);
                try {

                    Thread.sleep(100);
                    GraphicBG.getField().repaint();
                    if (!Field.completed  && Field.toSave) {
                        SavePic.i++;
                        SavePic.savePic(GraphicBG.ground);
                    }

                } catch (Exception e) {

                }
            }
        }
    }
}

enum DIRECTION {
    UP, DOWN, LEFT, RIGHT
}

enum CAMP {
    JUSTICE, EVIL
}