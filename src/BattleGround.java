import java.util.*;

public class BattleGround {
    private Position maps[][];      //a map that is created for fighting
    //When BattleGround created, the map will be initialized
    private int N;      //size of battleground
    private Calabash[] brothers;        //HuLu brothers
    BattleGround(int N) {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                maps[i][j] = new Position(i, j, null);
    }

    public int getN() {
        return N;
    }
    public Position[][] getMaps() {
        return maps;
    }

    BattleGround(int N, Scorpion scorpion, Snake snake,Grandpa grandpa, Minion[] minions, Calabash[] brothers) {
        this.maps = new Position[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                maps[i][j] = new Position(i, j, null);
        putCreature(scorpion, findEmptyPosition());
        putCreature(snake, findEmptyPosition());
        putCreature(grandpa, findEmptyPosition());
        for(int i = 0; i < minions.length; i++)
            putCreature(minions[i], findEmptyPosition());
        for(int i = 0; i < brothers.length; i++)
            putCreature(brothers[i], findEmptyPosition());
    }

    //Find empty position
    private Position findEmptyPosition() {
        Random random = new Random();
        Position temp;
        do {
            int i = random.nextInt(N);
            int j = random.nextInt(N);
            temp = maps[i][j];
        }while (temp.isEmpty() == true);
        return temp;
    }

    //To check whether the (a,b) -> (x,y) rectangular is empty
    //Note that a <= x && b <= y
    private boolean checkAreaEmpty(int a, int b, int x, int y) {
        for(int i = a; i <= x; i++)
            for(int j = b; j<=y; j++)
                if(!maps[i][j].isEmpty())
                    return false;
        return true;
    }

    private void ChangShe(Creature[] creatures) {
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - creatures.length);
            j = random.nextInt(N);
        }while (checkAreaEmpty(i, j, i + creatures.length - 1, j));
        for(int s = 0; s < creatures.length; s++)
            putCreature(creatures[s], maps[s + i][j]);
    }

    //creatures.length % 2 == 1
    private void HeYi(Creature[] creatures) {
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - creatures.length / 2);
            j = random.nextInt(N - creatures.length);
        }while (checkAreaEmpty(i, j, i + creatures.length / 2, j + creatures.length - 1));
        for(int s = 0; s < creatures.length/2; s++)
            putCreature(creatures[s], maps[s + i][j + s]);
        for(int s = creatures.length/2; s < creatures.length; s++)
            putCreature(creatures[s], maps[i + creatures.length - s - 1][j + s]);
    }

    private void YanXing(Creature[] creatures) {
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - creatures.length);
            j = random.nextInt(N);
        }while (checkAreaEmpty(i, j, i + creatures.length - 1, j));
        for(int s = 0; s < creatures.length; s++)
            putCreature(creatures[s], maps[i + creatures.length - 1 -s][j + s]);
    }

    private void ChongE(Creature[] creatures) {
        Random random = new Random();
        int i;
        int j;
        do {
            i = random.nextInt(N - creatures.length);
            j = random.nextInt(N -1);
        }while (checkAreaEmpty(i, j, i + creatures.length - 1, j + 1));
        for(int s = 0; s < creatures.length; s++)
            putCreature(creatures[s], maps[i + s][j + (s % 2)]);
    }



    private void putCreature(Creature creature, Position position) {
        position.setCreature(creature);
        creature.setPosition(position);
    }

    public static void main(String args[]) {

    }
}
