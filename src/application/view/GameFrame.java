package application.view;

import application.Settings;
import application.controller.GameController;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(GameController controller){
        setSize(Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        gamePanel = new GamePanel();
        controller.setGamePanel(gamePanel);
        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setLocation(toolkit.getScreenSize().width / 2 - Settings.WINDOW_SIZE / 2, toolkit.getScreenSize().height / 2 - Settings.WINDOW_SIZE / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
    }

}
