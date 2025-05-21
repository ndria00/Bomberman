package application.model;

public class Explosion extends GameObject {
    private int timeLeft;

    public Explosion(Position position, int timeLeft) {
        super(position);
        this.timeLeft = timeLeft;
    }


    public int getTimeLeft() {
        return timeLeft;
    }
    public void tick(){
        timeLeft-=1;
    }

}
