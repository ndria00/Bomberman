package application.controller;

import application.Settings;
import application.model.Game;
import application.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class GameController extends KeyAdapter {

    GamePanel gamePanel;
    List<Integer> directions;

    public GameController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        directions = new ArrayList<>();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        Game.getInstance().updatePlayerDirection(directionFromButton(e));
        if(e.getKeyCode() == KeyEvent.VK_B)
            Game.getInstance().placeBomb();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_B)
            return;

        int direction = directionFromButton(e);
        directions.removeIf(n -> n == direction);
        if(directions.isEmpty()){
            //System.out.println("Stopping player since direction vector is empty: ");
            Game.getInstance().stopPlayer();
//            Game.getInstance().getWorld().getPlayer().setMoving(false);
        }
        else{
            Game.getInstance().updatePlayerDirection(directions.getLast());
            //System.out.println("Updating player direction to direction last: " + directions.getLast() + " size is " + directions.size());
        }
    }

    private int directionFromButton(KeyEvent e){
        int direction = switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT ->  direction = Settings.DIRECTION_LEFT;
            case KeyEvent.VK_UP ->  direction = Settings.DIRECTION_UP;
            case KeyEvent.VK_RIGHT ->  direction = Settings.DIRECTION_RIGHT;
            case KeyEvent.VK_DOWN ->  direction = Settings.DIRECTION_DOWN;
            default -> Settings.DIRECTION_IDLE;
        };
        if(direction != Settings.DIRECTION_IDLE){
            Game.getInstance().getWorld().getPlayer().setMoving(true);
            if(directions.isEmpty() || directions.getLast()  != direction){
                if(!directions.contains(direction) )directions.add(direction);
            }
        }
        return direction;
    }
    public void update(){
        Game.getInstance().update();
        gamePanel.repaint();
    }
}
