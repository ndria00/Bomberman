package application.view;

import application.Settings;
import application.controller.GameController;
import application.model.Bomb;
import application.model.Game;
import application.model.Position;
import application.model.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GameView gameView;

    public GamePanel(){
        gameView = new GameView();
    }

    public void setController(GameController controller){
        this.addKeyListener(controller);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        World world = Game.getInstance().getWorld();
        for(int i = 0; i < world.getSize(); ++i){
            for(int j = 0; j < world.getSize(); ++j){
                int x = i * Settings.CELL_WIDTH;
                int y = j * Settings.CELL_WIDTH;
                switch (world.getTypeAt(new Position(i,j))){
                    case BOX -> {
                        g.setColor(Color.green);
//                        g.fillRect(x, y, Settings.CELL_WIDTH, Settings.CELL_WIDTH);
                        g.drawImage(gameView.getImage(World.BlockType.BOX), x, y, null);
                    }
                    case WALL -> {
                        g.setColor(Color.gray);
                        g.fillRect(x, y, Settings.CELL_WIDTH, Settings.CELL_WIDTH);
                        g.drawImage(gameView.getImage(World.BlockType.WALL), x, y, null);
                    }
                    case PLAYER -> {
                        g.setColor(Color.blue);
                        g.drawImage(gameView.getImage(World.BlockType.PLAYER), x, y, null);
//                        g.fillOval(x, y, Settings.CELL_WIDTH, Settings.CELL_WIDTH);
                    }
                    case EMPTY -> {
                        g.setColor(Color.lightGray);
//                        g.fillRect(x, y, Settings.CELL_WIDTH, Settings.CELL_WIDTH);
                        g.drawImage(gameView.getImage(World.BlockType.EMPTY), x, y, null);
                    }

                }
            }
        }

        for(Bomb b : Game.getInstance().getWorld().getBombs().values()){
            int x = b.getPosition().x() * Settings.CELL_WIDTH;
            int y = b.getPosition().y() * Settings.CELL_WIDTH;
            g.drawImage(gameView.getImage(World.BlockType.BOMB), x, y, null);
            int timeLeft = b.getTimeLeft();

            if(timeLeft >= 6)
                g.setColor(Color.GREEN);
            else if(timeLeft > 3)
                g.setColor(Color.yellow);
            else
                g.setColor(Color.RED);
            g.setFont(Settings.TEXT_FONT);
            FontMetrics metrics = g.getFontMetrics(Settings.TEXT_FONT);
            int width = metrics.stringWidth(String.valueOf(b.getTimeLeft()));
            g.drawString(String.valueOf(b.getTimeLeft()), x + Settings.CELL_WIDTH / 2 - width, y + Settings.CELL_WIDTH / 2);
        }

        //paint player
        Position playerPos = Game.getInstance().getWorld().getPlayer().getPosition();
        int x = playerPos.x() * Settings.CELL_WIDTH;
        int y = playerPos.y() * Settings.CELL_WIDTH;
        g.drawImage(gameView.getImage(World.BlockType.PLAYER), x, y, null);


    }
}
