public class Position implements Comparable<Position>{
    private int x;
    private int y;
    private Creature creature;
    private String figure = "🌶";
    private boolean empty;
    Position(){
    }
    Position(int x, int y){
        this.x = x;
        this.y = y;
        this.creature = null;
        this.empty = true;
    }
    Position(int x, int y, Creature creature) {
        this.x = x;
        this.y = y;
        this.creature = creature;
        this.empty = (creature == null);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean getEmpty() {
        return this.empty;
    }

    public Creature getCreature() {
        return this.creature;
    }
    public void setCreature(Creature creature) {
        this.creature = creature;
        this.figure = creature.getFigure();
        this.empty = false;
    }

    public void setNull() {
        this.creature = null;
        this.figure = "🌶";
        this.empty = true;
    }

    public String getFigure() {
        return figure;
    }


    @Override
    public int compareTo(Position position) {
        return this.getY() - position.getY();
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}

