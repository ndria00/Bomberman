package application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Settings {
    public static final int WINDOW_SIZE = 600;
    public static final int WORLD_SIZE = 15;
    public static final int CELL_WIDTH = WINDOW_SIZE / WORLD_SIZE;

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_IDLE = 4;

    public static final int BOMB_TIMER = 8;
    public static final int BOMB_RADIUS = 3;

    public static final Font TEXT_FONT = new Font("SansSerif", Font.BOLD, 20);

    public static final Map<Integer, Integer> keyToDirections = Map.ofEntries(
            Map.entry(KeyEvent.VK_UP, Settings.DIRECTION_UP),
            Map.entry(KeyEvent.VK_RIGHT, Settings.DIRECTION_RIGHT),
            Map.entry(KeyEvent.VK_DOWN, Settings.DIRECTION_DOWN),
            Map.entry(KeyEvent.VK_LEFT, Settings.DIRECTION_LEFT));

    public static final int EXPLOSION_DURATION = 10;
}
