import java.util.List;

public class BubbleSort implements Sorter {
    @Override
    public void sort(Creature[] creatures) {
        for (int i = creatures.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (creatures[i].Greater(creatures[j])) {
                    Position temp = creatures[i].getPosition();
                    creatures[i].setPosition(creatures[j].getPosition());
                    creatures[j].setPosition(temp);
                }
            }
        }
    }
}
