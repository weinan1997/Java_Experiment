import java.util.List;

public class QuickSort {
    public int Partition(List<Calabash> list, int s, int t) {
        int x = list.get(t).getSeniority().ordinal();
        int i = s - 1;
        for (int j = s; j < t; j++) {
            if(list.get(j).getSeniority().ordinal() < x) {
                i++;
                if(i == j)
                    continue;
                Calabash temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        if((i+1) == t)
            return (i+1);
        Calabash temp = list.get(i+1);
        list.set(i+1, list.get(t));
        list.set(t, temp);
        return (i+1);
    }

    private void Qsort(List<Calabash> list, int s, int t) {
        if(s >= t)
            return;
        int p = Partition(list, s, t);
        Qsort(list, s, p-1);
        Qsort(list, p+1, t);
    }
}
