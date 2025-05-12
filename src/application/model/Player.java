package application.model;

import application.Settings;

public class Player extends GameObject{

    private int direction;
    private int bombsLeft;
    private boolean moving;
    private boolean stopAfterMove;

    public Player(Position position){
        super(position);
        this.direction = Settings.DIRECTION_IDLE;
        this.bombsLeft = 3;
        this.moving = false;
        this.stopAfterMove = false;
    }


    public void move(Position position){
//        System.out.println("Setting player position: " + position);
        this.setPosition(position);
        if(stopAfterMove)
            moving = false;
    }

    public void setDirection(int direction) {
//        System.out.println("Set player direction " + direction);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public int getBombsLeft(){
        return bombsLeft;
    }

    public void addBomb(){
        bombsLeft += 1;
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

    public boolean isMoving(){
        return moving;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void setStopAfterMove(boolean stopAfterMove){
        this.stopAfterMove = stopAfterMove;
    }
}
