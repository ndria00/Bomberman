package application;

import application.controller.GameController;
import application.view.GameFrame;
import application.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        GameController gameController = new GameController(gamePanel);
        gamePanel.setController(gameController);
        GameFrame frame = new GameFrame(gamePanel);
        frame.setVisible(true);
        GameLoop gameLoop = new GameLoop(gameController);
        gameLoop.startGame();
    }
}
