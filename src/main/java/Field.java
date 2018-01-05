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
    private final int N = 15;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();

    private boolean completed = false;

    Field(){
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

        for (int i = 0; i < creatures.size(); i++) {

            Creature temp = creatures.get(i);

            g.drawImage(temp.getImage(), temp.getPosition().getX() * tileLength, temp.getPosition().getY() * tileLength, this);

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }
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
