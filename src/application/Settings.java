package application;

import java.awt.*;

public class Settings {
    public static final int WINDOW_SIZE = 600;
    public static final int CELL_WIDTH = 40;
    public static final int WORLD_SIZE = WINDOW_SIZE / CELL_WIDTH;

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_IDLE = 4;

    public static final int BOMB_TIMER = 8;
    public static final int BOMB_RADIUS = 3;

    public static final Font TEXT_FONT = new Font("SansSerif", Font.BOLD, 20);

}
