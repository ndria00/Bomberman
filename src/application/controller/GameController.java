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

    private GamePanel gamePanel;
    private List<Integer> directions;

    public GameController(){
        directions = new ArrayList<>();
    }
    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        gamePanel.addKeyListener(this);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(e.getKeyCode() == KeyEvent.VK_B)
            Game.getInstance().placeBomb();
        else if(Settings.keyToDirections.containsKey(e.getKeyCode())){
            int direction =  Settings.keyToDirections.get(e.getKeyCode());
            if(!directions.contains(direction))
                directions.add(direction);
            Game.getInstance().updatePlayerDirection(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(Settings.keyToDirections.containsKey(e.getKeyCode())){
            int direction =  Settings.keyToDirections.get(e.getKeyCode());
            directions.removeIf(n -> n == direction);
            if(directions.isEmpty()){;
                Game.getInstance().updatePlayerDirection(Settings.DIRECTION_IDLE);
            }
            else{
                Game.getInstance().updatePlayerDirection(directions.getLast());
            }
        }
    }

    public void update(){
        try{
            Game.getInstance().update();
            gamePanel.repaint();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
