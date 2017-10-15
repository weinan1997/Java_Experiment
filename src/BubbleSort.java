import java.util.List;

public class BubbleSort {
    public void bubble(List<Calabash> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).getSeniority().ordinal() > list.get(j+1).getSeniority().ordinal()) {
                    Calabash temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
    }
}
