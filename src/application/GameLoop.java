package application;

import application.controller.GameController;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop {
    private final GameController controller;
    private ScheduledExecutorService executorService = null;

    public GameLoop(GameController controller){
        this.controller = controller;
    }

    public void startGame(){
        if(executorService != null)
            return;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(controller::update, 0, 250, TimeUnit.MILLISECONDS);
    }
}
