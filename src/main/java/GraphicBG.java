import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphicBG {
    static List<Creature> monsters = new ArrayList<>();
    static List<Creature> brothers = new ArrayList<>();

    public static Ground ground;

    private static Field field;

    public static Field getField() {
        return field;
    }

    private final int N = 14;

    GraphicBG() {
        initBattle(monsters, brothers);
        BattleGround battle1 = new BattleGround(N, monsters, brothers);
        System.out.println("Round 1:");
        battle1.ChangShe(brothers);
        battle1.YanXing(monsters);
        battle1.showBattleGround();

        field = new Field(N);
        field.addCreatures(monsters);
        field.addCreatures(brothers);
        ground = new Ground(field);
        ground.setVisible(true);
    }

    private void initBattle(List<Creature> monsters, List<Creature> brothers) {
        Snake snake = new Snake();
        Grandpa grandpa = new Grandpa();
        Scorpion scorpion = new Scorpion();
        Minion[] minions = new Minion[6];
        for(int i = 0; i < minions.length; i++)
            minions[i] = new Minion();
        Calabash[] calabashes = new Calabash[7];
        for(int i = 0; i < calabashes.length; i++)
            calabashes[i] = new Calabash(COLOR.values()[i], SENIORITY.values()[i], String.valueOf(i+1) + ".png");
        monsters.add(snake);
        monsters.add(scorpion);
        monsters.addAll(Arrays.asList(minions));
        brothers.add(grandpa);
        brothers.addAll(Arrays.asList(calabashes));
    }
}
