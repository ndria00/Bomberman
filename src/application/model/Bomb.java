package application.model;

import application.Settings;

public class Bomb extends GameObject implements Runnable{
    private int timeLeft;

    public Bomb(Position position){
        super(position);
        this.timeLeft = Settings.BOMB_TIMER;
    }
    private synchronized void tick(){
        timeLeft -= 1;
    }
    public synchronized boolean isExploded(){
        return timeLeft == 0;
    }
    public synchronized int getTimeLeft(){
        return  timeLeft;
    }
    public synchronized void explode(){
        timeLeft = 0;
    }
    public synchronized void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
    @Override
    public void run() {
        while(timeLeft > 0){
            try {
                tick();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
