public class Grandpa extends Creature {
    boolean Arrested;
    Grandpa() {
        this.setFigure("👴");
        this.setImage("grandpa.png");
    }

    public void report(BattleGround tempBat) {
        arrest(tempBat);
        if(Arrested)
            System.out.println("爷爷所在位置" + this.getPosition().toString() + "在怪物控制范围内，请速去救援！");
        else
            System.out.println("爷爷发动技能\"葫芦攻势\"，其所在位置" + this.getPosition().toString() + "周围两格的葫芦娃受到增益");
        System.out.println();
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
