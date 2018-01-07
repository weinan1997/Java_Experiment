import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import java.util.List;

public class Field extends JPanel {
    private int width;
    private int height;
    private int tileLength;
    private int N = 15;
    private ArrayList<Tile> tiles = new ArrayList<>();
    static ArrayList<Creature> creatures = new ArrayList<>();
    static boolean completed = false;

    private ArrayList<Thread> creaturesThreads = new ArrayList<>();

    static final String moveLock = "Move lock";

    Field(int n){
        N = n;
        addKeyListener(new TAdapter());
        setFocusable(true);
        initTiles();
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public void addCreatures(List<Creature> c) {
        creatures.addAll(c);
    }

    private void initTiles() {
        Tile temp = new Tile();
        tileLength = temp.getWidth();
        width = tileLength * N;
        height = tileLength * N;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                Tile t = new Tile(i * tileLength, j * tileLength);
                tiles.add(t);
            }
    }

    public void buildWorld(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < tiles.size(); i++) {

            Tile temp = tiles.get(i);

            g.drawImage(temp.getImage(), temp.getX(), temp.getY(), this);

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }

        int deadNumber = 0;
        for (int i = 0; i < creatures.size() / 2; i++) {
            if(!creatures.get(i).isAlive()) {
                deadNumber++;
                creaturesThreads.get(i).interrupt();
            }
        }
        if(deadNumber == creatures.size() / 2) {
            completed = true;
            g.setColor(new Color(0, 0, 0));
            g.drawString("Completed", 25, 20);
        }
        deadNumber = 0;
        for (int i = creatures.size() / 2; i < creatures.size(); i++) {
            if(!creatures.get(i).isAlive()) {
                deadNumber++;
                creaturesThreads.get(i).interrupt();
            }
        }
        if(deadNumber == creatures.size() / 2) {
            completed = true;
            g.setColor(new Color(0, 0, 0));
            g.drawString("Completed", 25, 20);
        }




        for (int i = 0; i < creatures.size(); i++) {

            Creature temp = creatures.get(i);

            if(!temp.isAlive())
                continue;

            g.drawImage(temp.getImage(), temp.getPosition().getY() * tileLength, temp.getPosition().getX() * tileLength, this);

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }

        if (completed)
            for (int i = 0; i < creaturesThreads.size(); i++)
                creaturesThreads.get(i).interrupt();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (completed) {
                return;
            }

            int key = e.getKeyCode();

            if(key == KeyEvent.VK_SPACE) {
                for (int i = 0; i < creatures.size(); i++) {
                    creaturesThreads.add(new Thread(creatures.get(i)));
                    creaturesThreads.get(i).start();
                }
            }
            else if(key == KeyEvent.VK_R)
                restartLevel();
            repaint();
        }
    }

    public void restartLevel() {

        tiles.clear();
        initTiles();
        if (completed) {
            completed = false;
        }
    }

}
