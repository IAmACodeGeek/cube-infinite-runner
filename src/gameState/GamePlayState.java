package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.Constants;
import com.tools.animations.Animation;

import game.GameWindow;
import level.BackGround;
import level.Cube;
import level.Floor;
import level.obstacles.ObstacleGenerator;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import java.io.File;

public class GamePlayState implements GameState {
	Clip bgClip;
	BackGround bg;
	Floor floor;
	Cube cube;
	ObstacleGenerator obsGenerator;
	boolean playerIsDead = false;
	
	public GamePlayState() {
		startMusic();
		bg = new BackGround(Constants.bgURL, GameWindow.WIDTH, GameWindow.HEIGHT, 0, -50);
		bg.setScrollSpeed(2);
		
		floor = new Floor(Constants.bgURL, GameWindow.WIDTH * 2, GameWindow.HEIGHT, 0, 350);
		
		HashMap<String, String[]> cubeURLs;
		cubeURLs = new HashMap<String, String[]>(Map.of(Animation.IDLE, Constants.cubeIdle1, Animation.JUMP, Constants.cubeJump1, Animation.DESTROY, Constants.cubeDestroy1));
		
		cube = new Cube(cubeURLs, 
				new HashMap<String, int[][]>(Map.of(Animation.IDLE, Constants.cubeIdleSizes, Animation.JUMP, Constants.cubeJumpSizes, Animation.DESTROY, Constants.cubeDestroySizes)), 
				Constants.cubeIdleSizes[0][0], Constants.cubeIdleSizes[0][1], floor, 20, 25);
		floor.setScrollSpeed((int)cube.getVelocityX());
		
		obsGenerator = new ObstacleGenerator(0, 0, 1500, floor.getY(), cube.getVelocityX(), 1);
	}

	@Override
	public void update() {
		if(!playerIsDead) {	
			bg.update();
			floor.update();
			cube.update();
			obsGenerator.update();
			playerIsDead = obsGenerator.checkCubeDead(cube);
		}
		else {
			GameStateManager.gsm.getCurrentState().destroy();
			GameStateManager.gsm.setState(GameStateManager.GAME_PLAY_STATE);
		}
	}
	
	public void destroy() {
		bg.destroy();
		floor.destroy();
		cube.destroy();
		obsGenerator.destroy();
		bgClip.close();
	}
	
	@Override
	public void draw(Graphics g) {
		bg.draw(g);
		obsGenerator.draw(g);
		cube.draw(g);
		floor.draw(g);
		
		Font font1 = new Font("Helvetica", Font.BOLD, 30); 
		g.setFont(font1);
		g.setColor(new Color(255,223,102));
		String currentScoreText = "Current Score: " + cube.getcurrentScore();
        g.drawString(currentScoreText, 450, 80);
        
	}
	
	private void startMusic() {
		// Music
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(Constants.bgMusicURL));
			
			bgClip = AudioSystem.getClip();
			bgClip.open(ais);
			bgClip.start();
			
			bgClip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if(event.getType() == LineEvent.Type.STOP) {
						bgClip.setLoopPoints(10, 100);
						bgClip.start();
					}
				}
			});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(int keyCode) {
		if(keyCode == KeyEvent.VK_SPACE) {
			cube.jump();
		}
		else if(keyCode == KeyEvent.VK_ESCAPE) {
			GameStateManager.gsm.getCurrentState().destroy();
			GameStateManager.gsm.setState(GameStateManager.MENU_STATE);
		}

	}

	@Override
	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		cube.jump();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

	}

}
