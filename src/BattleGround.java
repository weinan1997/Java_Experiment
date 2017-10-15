public class BattleGround {
    private Position maps[][];      //a map that is created for fighting
    //When BattleGround created, the map will be initialized
    private int N;
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

    public static void main(String args[]) {

    }
}
