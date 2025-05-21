package application.view;

import application.Settings;
import application.model.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private ImagesHandler imagesHandler;

    public GamePanel(){
        imagesHandler = new ImagesHandler();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x,y;
        World world = Game.getInstance().getWorld();
        for(int i = 0; i < world.getSize(); ++i){
            for(int j = 0; j < world.getSize(); ++j){
                x = i * Settings.CELL_WIDTH;
                y = j * Settings.CELL_WIDTH;
                World.BlockType cellType = world.getTypeAt(new Position(i,j));
                if(cellType == World.BlockType.BOX || cellType == World.BlockType.EMPTY || cellType == World.BlockType.WALL)
                    g.drawImage(imagesHandler.getImage(cellType), x, y, null);
            }
        }

        for(Bomb b : Game.getInstance().getWorld().getBombs().values()){
            x = b.getPosition().x() * Settings.CELL_WIDTH;
            y = b.getPosition().y() * Settings.CELL_WIDTH;
            g.drawImage(imagesHandler.getImage(World.BlockType.BOMB), x, y, null);
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
        //paint explosions
        for(Explosion explosion : Game.getInstance().getWorld().getExplosions()){
            Position pos = explosion.getPosition();
            x = pos.x() * Settings.CELL_WIDTH;
            y = pos.y() * Settings.CELL_WIDTH;
            g.drawImage(imagesHandler.getImage(World.BlockType.EXPLOSION), x, y, null);
        }
        //paint player
        Position playerPos = Game.getInstance().getWorld().getPlayer().getPosition();
        x = playerPos.x() * Settings.CELL_WIDTH;
        y = playerPos.y() * Settings.CELL_WIDTH;
        g.drawImage(imagesHandler.getImage(World.BlockType.PLAYER), x, y, null);


    }
}
