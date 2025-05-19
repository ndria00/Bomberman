package application.controller;

import application.Settings;
import application.model.Game;
import application.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController extends KeyAdapter {

    GamePanel gamePanel;
    List<Integer> directions;
    Map<Integer, Integer> keyToDirections;

    public GameController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        directions = new ArrayList<>();
        keyToDirections = Map.ofEntries(
                Map.entry(KeyEvent.VK_UP, Settings.DIRECTION_UP),
                Map.entry(KeyEvent.VK_RIGHT, Settings.DIRECTION_RIGHT),
                Map.entry(KeyEvent.VK_DOWN, Settings.DIRECTION_DOWN),
                Map.entry(KeyEvent.VK_LEFT, Settings.DIRECTION_LEFT));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(e.getKeyCode() == KeyEvent.VK_B)
            Game.getInstance().placeBomb();
        else if(keyToDirections.containsKey(e.getKeyCode())){
            int direction = directionFromButton(e);
            if(!directions.contains(direction))
                directions.add(direction);
            Game.getInstance().updatePlayerDirection(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(keyToDirections.containsKey(e.getKeyCode())){
            int direction = directionFromButton(e);
            directions.removeIf(n -> n == direction);
            if(directions.isEmpty()){;
                Game.getInstance().updatePlayerDirection(Settings.DIRECTION_IDLE);
            }
            else{
                Game.getInstance().updatePlayerDirection(directions.getLast());
            }
        }
    }

    private int directionFromButton(KeyEvent e){
        return  switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT ->  Settings.DIRECTION_LEFT;
            case KeyEvent.VK_UP ->  Settings.DIRECTION_UP;
            case KeyEvent.VK_RIGHT ->  Settings.DIRECTION_RIGHT;
            case KeyEvent.VK_DOWN ->  Settings.DIRECTION_DOWN;
            default -> Settings.DIRECTION_IDLE;
        };
    }
    public void update(){
        Game.getInstance().update();
        gamePanel.repaint();
    }
}
