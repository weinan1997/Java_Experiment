public class Creature {
    public boolean Greater(Creature another){
        return false;
    }
    public void report() {

    }
    private Position position;
    private String figure = "🌭";
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public String getFigure() {
        return figure;
    }
    public void setFigure(String figure) {
        this.figure = figure;
    }
}
