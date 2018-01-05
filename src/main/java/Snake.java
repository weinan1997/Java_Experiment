public class Snake extends Creature {
    Snake() {
        this.setFigure("🐍");
        this.setImage("snake.png");
    }
    @Override
    public void report() {
        System.out.println(this.toString());
        System.out.println();
    }

    @Override
    public String toString() {
        return "蛇精使用了玉如意，其所在位置" + this.getPosition().toString() + "周围两格的怪物受到增益";
    }
}
