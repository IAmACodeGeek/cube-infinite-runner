package gameState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface GameState {
	void update();
	void draw(Graphics g);
	void keyPressed(int keyCode);
	void keyReleased(int keyCode);
	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);
	void destroy();
}