public class Minion extends Creature {
    Minion() {
        this.setFigure("🐸");
        this.setImage("minion.png");
        this.strength = new java.util.Random().nextInt(100);
        this.camp = CAMP.EVIL;
    }
    @Override
    public void report() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "喽啰 @" + this.getPosition().toString();
    }
}
