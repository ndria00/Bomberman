package application.model;

public class Explosion {
    private Position position;
    private int timeLeft;

    public Explosion(Position position, int timeLeft) {
        this.position = position;
        this.timeLeft = timeLeft;
    }

    public Position getPosition() {
        return position;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
    public void tick(){
        timeLeft-=1;
    }

}
