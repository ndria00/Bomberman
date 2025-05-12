package application.model;

import application.Settings;
import application.view.GameFrame;
import application.view.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private static Game instance;

    private World world;

    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }

    private Game(){
        world = new World();
    }
    public void updatePlayerDirection(int direction){
        world.updatePlayerDirection(direction);
    }
    public void stopPlayer(){
        world.getPlayer().setStopAfterMove(true);
    }
    public void movePlayer(){
        world.movePlayer();
    }
    public World getWorld(){
        return world;
    }
    public void placeBomb(){
        world.createBomb();
    }
    public void update(){
        world.update();
    }
}
