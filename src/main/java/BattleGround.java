import java.util.List;
import java.util.Random;

public class BattleGround {
    private Position maps[][];      //a map that is created for fighting
    //When BattleGround created, the map will be initialized
    private int N;      //size of battleground
    private Calabash[] brothers;        //HuLu brothers
    /*
    BattleGround(int n) {
        N = n;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                maps[i][j] = new Position(i, j, null);
    }
    */

    public int getN() {
        return N;
    }

    public Position[][] getMaps() {
        return maps;
    }

    BattleGround()
    {
    }

    BattleGround(int n, List<Creature> monsters, List<Creature> brothers) {
        N = n;
        maps = new Position[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                maps[i][j] = new Position(i, j, null);
        //putCreature(snake, findEmptyPosition());
        //putCreature(grandpa, findEmptyPosition());
    }

    public void showBattleGround() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(this.maps[i][j].getFigure());
            System.out.print("\n");
        }
        System.out.println();
    }

    //Find empty position
    private Position findEmptyPosition() {
        Random random = new Random();
        Position temp;
        int i, j;
        i = random.nextInt(N);
        j = random.nextInt(N);
        while (!maps[i][j].getEmpty()) {
            i = random.nextInt(N);
            j = random.nextInt(N);
        }
        return maps[i][j];
    }

    //To check whether the (a,b) -> (x,y) rectangular is empty
    //Note that a <= x && b <= y
    private boolean checkAreaEmpty(int a, int b, int x, int y) {
        for (int i = a; i <= x; i++)
            for (int j = b; j <= y; j++)
                if (!maps[i][j].getEmpty())
                    return false;
        return true;
    }

    public void ChangShe(List<Creature> list) {
        int len = list.size();
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - len);
            j = random.nextInt(N);
        } while (!checkAreaEmpty(i, j, i + len - 1, j));
        for (int s = 0; s < len; s++)
            putCreature(list.get(s), this.maps[s + i][j]);
    }

    //creatures.length % 2 == 1
    public void HeYi(List<Creature> list) throws IllegalArgumentException{
        int len = list.size();
        if(len % 2 == 0)
            throw new IllegalArgumentException();
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - len / 2);
            j = random.nextInt(N - len);
        } while (!checkAreaEmpty(i, j, i + len / 2, j + len - 1));
        for (int s = 0; s < len / 2; s++)
            putCreature(list.get(s), this.maps[s + i][j + s]);
        for (int s = len / 2; s < len; s++)
            putCreature(list.get(s), this.maps[i + len - s - 1][j + s]);
    }

    public void YanXing(List<Creature> list) {
        Random random = new Random();
        int i;
        int j;
        int len = list.size();
        do {
            i = random.nextInt(N - len);
            j = random.nextInt(N - len);
        } while (!checkAreaEmpty(i, j, i + len - 1, j + len - 1));
        for (int s = 0; s < len; s++)
            putCreature(list.get(s), this.maps[i + len - 1 - s][j + s]);
    }

    public void ChongE(List<Creature> list) {
        Random random = new Random();
        int i;
        int j;
        int len = list.size();
        do {
            i = random.nextInt(N - len);
            j = random.nextInt(N - 1);
        } while (!checkAreaEmpty(i, j, i + len - 1, j + 1));
        for (int s = 0; s < len; s++)
            putCreature(list.get(s), this.maps[i + s][j + (s % 2)]);
    }

    private void putCreature(Creature creature, Position position) {
        position.setCreature(creature);
        creature.setPosition(position);
    }

}
