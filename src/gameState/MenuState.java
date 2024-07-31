package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.Constants;
import com.ui.ImageButton;

import game.GameWindow;
import level.BackGround;

public class MenuState implements GameState {
	private BackGround bg;
	private ImageButton playButton, titleLogo;
	
	public MenuState() {
		// Set Background
		bg = new BackGround(Constants.bgURL, GameWindow.WIDTH, GameWindow.HEIGHT);
		bg.setScrollSpeed(2);
		
		// UI
		playButton = new ImageButton(Constants.playButtonURL, 150, 150);
		playButton.setPosition((GameWindow.WIDTH - playButton.getWidth()) / 2, (GameWindow.HEIGHT - playButton.getHeight()) / 2);
			
		titleLogo = new ImageButton(Constants.titleURL, 350, 50);
		titleLogo.setPosition((GameWindow.WIDTH - titleLogo.getWidth()) / 2, playButton.getY() - titleLogo.getHeight()*2);
	
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics g) {
		bg.draw(g);
		titleLogo.draw(g);
		playButton.draw(g);
		
	}

	@Override
	public void destroy() {
		bg.destroy();
		playButton.destroy();
	}
	
	@Override
	public void keyPressed(int keyCode) {
		
	}

	@Override
	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
