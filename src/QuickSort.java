import java.util.List;

public class QuickSort implements Sorter{
    @Override
    public void sort(Creature[] creatures) {

    }

    private int Partition(Creature[] creatures, int s, int t) {
        int i = s - 1;
        for (int j = s; j < t; j++) {
            if(creatures[t].Greater(creatures[j])) {
                i++;
                if(i == j)
                    continue;
                Position temp = creatures[i].getPosition();
                creatures[i].setPosition(creatures[j].getPosition());
                creatures[j].setPosition(temp);
            }
        }
        if((i+1) == t)
            return (i+1);
        Position temp = creatures[i+1].getPosition();
        creatures[i+1].setPosition(creatures[t].getPosition());
        creatures[t].setPosition(temp);
        return (i+1);
    }

    private void Qsort(Creature[] creatures, int s, int t) {
        if(s >= t)
            return;
        int p = Partition(creatures, s, t);
        Qsort(creatures, s, p-1);
        Qsort(creatures, p+1, t);
    }
}
