package application;

import application.controller.GameController;
import application.view.GameFrame;


public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        GameFrame frame = new GameFrame(gameController);
        frame.setVisible(true);
        GameLoop gameLoop = new GameLoop(gameController);
        gameLoop.startGame();
    }
}
