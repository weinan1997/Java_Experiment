public class Grandpa extends Creature {
    boolean Arrested;

    Grandpa() {

    }

    public void report(BattleGround tempBat) {
        arrest(tempBat);
        if(Arrested)
            System.out.println("Mayday, mayday!");
        else
            System.out.println("C'est la guerre, Attaque!");
    }

    private void arrest(BattleGround tempBat) {
        Position[][] tempMap = tempBat.getMaps();
        Position pos = getPosition();
        Arrested = false;
        int x = pos.getX();
        int y = pos.getY();
        int N = tempBat.getN();
        if(x - 1 >= 0 && isDanger(tempMap[x-1][y]))
            Arrested = true;
        else if(x + 1 < N && isDanger(tempMap[x+1][y]))
            Arrested = true;
        else if(y - 1 >= 0 && isDanger(tempMap[x][y-1]))
            Arrested = true;
        else if(y + 1 < N && isDanger(tempMap[x][y+1]))
            Arrested = true;
    }

    private boolean isDanger(Position pos) {
        if(pos.getClass().equals(Snake.class) || pos.getClass().equals(Scorpion.class) || pos.getClass().equals(Minion.class))
            return true;
        else
            return false;
    }
}
