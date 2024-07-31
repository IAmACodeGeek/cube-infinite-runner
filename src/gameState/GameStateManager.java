package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GameStateManager implements KeyListener, MouseListener{
	public static GameStateManager gsm;
	
	public static final String MENU_STATE = "MENU_STATE";
	public static final String GAME_PLAY_STATE = "GAME PLAY STATE";
	public static final String CHANGE_SPRITE_STATE = "CHANGE SPRITE STATE";
	
	private String currentState;
	private GameState currentGameState;
	
	public GameStateManager() {
		
	}
	
	public void update() {
		if(currentGameState != null)
			currentGameState.update();
	}
	
	public void draw(Graphics g) {
		if(currentGameState != null)
			currentGameState.draw(g);
	}
	
	public String getState() {
		return currentState;
	}
	
	public GameState getCurrentState() {
		return currentGameState;
	}
	
	// Keyboard events
	@Override
	public void keyPressed(KeyEvent e) {
		currentGameState.keyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	// Mouse events
	@Override
    public void mousePressed(MouseEvent e) {
		currentGameState.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Code to execute when the mouse enters the panel
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Code to execute when the mouse exits the panel
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Code to execute when the mouse exits the panel
    }
    
}

