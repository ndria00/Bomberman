package application.model;

import application.Settings;

public class Player extends GameObject{

    private int direction;
    private int bombsLeft;

    public Player(Position position, int totalBombs){
        super(position);
        this.direction = Settings.DIRECTION_IDLE;
        this.bombsLeft = totalBombs;
    }
    public void move(Position position){
        this.setPosition(position);
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getBombsLeft(){
        return bombsLeft;
    }
    public void addBomb(){
        bombsLeft += 1;
    }
    public void useBomb(){
        bombsLeft-=1;
    }
    public Position simulateMove(){
        Position playerPos = getPosition();
        if(direction == Settings.DIRECTION_IDLE)
            return playerPos;
        Position newPosition = null;
        switch (direction){
            case Settings.DIRECTION_LEFT -> newPosition = new Position(playerPos.x() -1, playerPos.y());
            case Settings.DIRECTION_UP -> newPosition = new Position(playerPos.x(), playerPos.y() -1);
            case Settings.DIRECTION_RIGHT -> newPosition = new Position(playerPos.x() +1, playerPos.y());
            case Settings.DIRECTION_DOWN -> newPosition = new Position(playerPos.x(), playerPos.y()+1);
            default -> newPosition = playerPos;
        }
        return  newPosition;
    }
}
