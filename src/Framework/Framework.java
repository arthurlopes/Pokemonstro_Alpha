package Framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Framework extends Canvas {
    
    public static int frameWidth;
    public static int frameHeight;

    /**
     * Time of one second in nanoseconds.
     * 1 second = 1 000 000 000 nanoseconds
     */
    public static final long secInNanosec = 1000000000L;
    
    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;
    private final int GAME_FPS = 30;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, 
    							 PLAYING, GAMEOVER, DESTROYED}
    
    public static GameState gameState;
    
    private long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;
    
    // The actual game
    private Game game;
    
    public Framework () {
        super();
        
        gameState = GameState.VISUALIZING;
        
        Thread gameThread = new Thread() {
            @Override
            public void run() {
                GameLoop();
            }
        };
        gameThread.start();
    }
    
    private void Initialize() {
    	
    }
    
    private void LoadContent() {
    
    }
    
    private void GameLoop() {
    	// This two variables are used in VISUALIZING state of the game. 
    	// We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        // This variables are used for calculating the time that defines for how 
    	// long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;
        
        while(true) {
            beginTime = System.nanoTime();
            
            switch (gameState) {
                case PLAYING:
                	gameTime += System.nanoTime() - lastTime;
                	
                    game.UpdateGame(gameTime);
                    
                    lastTime = System.nanoTime();
                break;
                case OPTIONS:
                    
                break;
                case STARTING:
                    Initialize();
                    LoadContent();
                    gameState = GameState.MAIN_MENU;
                break;
                case MAIN_MENU:
                	newGame();
                break;
	        	case VISUALIZING:
		            if (this.getWidth() > 1 && visualizingTime > secInNanosec) {
		                frameWidth = this.getWidth();
		                frameHeight = this.getHeight();
		                
		                gameState = GameState.STARTING;
		            }
		            else {
		                visualizingTime += System.nanoTime() - lastVisualizingTime;
		                lastVisualizingTime = System.nanoTime();
		            }
	            break;
	        }
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 
            // 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10)
                timeLeft = 10; //set a minimum
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) {
            	
            }
        }
    }
    
    @Override
    public void Draw(Graphics2D g2d) {
        switch (gameState) {
            case PLAYING:
                game.Draw(g2d);
            break;
            case MAIN_MENU:
            	
            break;
            case OPTIONS:
                
            break;
        }
    }
    
    private void newGame() {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();
    }
    
    @Override
    public void keyReleasedFramework(KeyEvent e) {
    	switch (gameState) {
    		case PLAYING:
    			game.player.finish_move(e);
    		break;
    	}
    }
}