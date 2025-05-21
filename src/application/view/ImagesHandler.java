package application.view;

import application.Settings;
import application.model.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImagesHandler {
    private static String RESOURCE_PATH = "/application/resources/";
    private Map<World.BlockType, Image> ojbectToImage;

    public ImagesHandler() {
        ojbectToImage = new HashMap<>();
        try{
            ojbectToImage.put(World.BlockType.BOMB, ImageIO.read(getClass().getResource(RESOURCE_PATH + "bomb.png")));
            ojbectToImage.put(World.BlockType.BOX, ImageIO.read(getClass().getResource(RESOURCE_PATH + "box.jpeg")));
            ojbectToImage.put(World.BlockType.EXPLOSION, ImageIO.read(getClass().getResource(RESOURCE_PATH + "explosion.png")));
            ojbectToImage.put(World.BlockType.EMPTY, ImageIO.read(getClass().getResource(RESOURCE_PATH + "floor.jpeg")));
            ojbectToImage.put(World.BlockType.PLAYER, ImageIO.read(getClass().getResource(RESOURCE_PATH + "player.png")));
            ojbectToImage.put(World.BlockType.WALL, ImageIO.read(getClass().getResource(RESOURCE_PATH + "wall.jpeg")));

            for(Map.Entry<World.BlockType, Image> entry : ojbectToImage.entrySet()){
                entry.setValue(entry.getValue().getScaledInstance(Settings.CELL_WIDTH, Settings.CELL_WIDTH, Image.SCALE_SMOOTH));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Image getImage(World.BlockType blockType){
        return ojbectToImage.get(blockType);
    }
}
