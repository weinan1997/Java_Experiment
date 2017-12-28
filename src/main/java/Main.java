import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private List<Creature> monsters = new ArrayList<>();
    private List<Creature> brothers = new ArrayList<>();

    Main() {
        init(monsters, brothers);
        BattleGround battle1 = new BattleGround(15, monsters, brothers);
        System.out.println("Round 1:");
        battle1.ChangShe(brothers);
        battle1.YanXing(monsters);
        battle1.showBattleGround();
    }

    private void init(List<Creature> monsters, List<Creature> brothers) {
        Snake snake = new Snake();
        Grandpa grandpa = new Grandpa();
        Scorpion scorpion = new Scorpion();
        Minion[] minions = new Minion[6];
        for(int i = 0; i < minions.length; i++)
            minions[i] = new Minion();
        Calabash[] calabashes = new Calabash[7];
        for(int i = 0; i < calabashes.length; i++)
            calabashes[i] = new Calabash(COLOR.values()[i], SENIORITY.values()[i]);
        monsters.add(snake);
        monsters.add(scorpion);
        monsters.addAll(Arrays.asList(minions));
        brothers.add(grandpa);
        brothers.addAll(Arrays.asList(calabashes));
    }

    public static void main(String args[]) {
        Main process = new Main();
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}
