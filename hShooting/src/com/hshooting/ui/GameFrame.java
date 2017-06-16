package com.hshooting.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.hshooting.data.GameData;
import com.hshooting.data.unit.Unit;
import com.hshooting.property.GraphicProperty;
import com.hshooting.round.EnemyCreator;
import com.hshooting.round.TestEnemyCreator;
import com.hshooting.thread.ThreadData;

public class GameFrame extends JFrame{
	private static GameFrame instance = new GameFrame();
	private static ScreenPainter screenPainter;
	private static GameData gameData;
	public static GameFrame getInstance() {
		return instance;
	}
	public static void start(){
		gameData = new GameData();
		instance.setVisible(true);
		screenPainter = instance.new ScreenPainter();
		screenPainter.start();
		//EnemyCreator creator = new Round1EnemyCreator();
		EnemyCreator creator = new TestEnemyCreator();
		ThreadData.addService(creator);
	}
	private GameFrame(){
		super.setTitle("hShooting v1.0");
		super.setSize(GameData.width, GameData.height);
		super.setResizable(true);
		super.setFocusTraversalKeysEnabled(false);
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		//this.addKeyListener(new KeyProcessor());
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());
		setFocusableWindowState(true);
		//this.requestFocus();
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	private Image backImage = null;
	private Graphics backScreen = null;
	@Override
	public void paint(Graphics g) {
		if(backImage == null){
			backImage = createImage(GameData.width, GameData.height);
			backScreen = backImage.getGraphics();
		}else{
			backScreen.clearRect(0, 0, GameData.width, GameData.height);
		}
		drawScreen(g);
	}
	
	private void drawScreen(Graphics g){
		drawBackground(g);
		drawUnit(g);
		g.drawImage(backImage, 0, 0, this);
	}
	
	private void drawBackground(Graphics g){
		
	}
	
	private void drawUnit(Graphics g){
		gameData.drawAll(backScreen, this);
	}
	
	private class MyDispatcher implements KeyEventDispatcher{
		public boolean dispatchKeyEvent(KeyEvent e) {
//			System.out.println("key event");
			if(e.getID() == KeyEvent.KEY_PRESSED){
				GameData.press(e.getKeyCode());
			}else if(e.getID() == KeyEvent.KEY_RELEASED){
				GameData.release(e.getKeyCode());
			}
			return false;
		}
	}
	
//	모든 컴포넌트에서 발생시키고 싶다면 이방법으론 무리
//	class KeyProcessor extends KeyAdapter{
//		public void keyPressed(KeyEvent e) {
//			GameData.press(e.getKeyCode());
//		}
//		public void keyReleased(KeyEvent e) {
//			GameData.release(e.getKeyCode());
//		}
//	}
	
	private void close(){
		//ThreadData.shutdown();
		GameData.clearEffect();
		GameData.clearEnemy();
		GameData.clearMissile();
		Unit.shutdown();
		System.exit(0);
	}
	
	/**
	 * @author Administrator
	 *         <h1>그림 그리는 스레드</h1> 60 fps로 맞춰져 있음
	 */
	class ScreenPainter {

		/**
		 * java.util.Timer를 활용해 fps의 주사율로 DrawingFrame을 repaint
		 */
		public void start() {
			java.util.Timer timer = new java.util.Timer(true);
			java.util.TimerTask task = new java.util.TimerTask() {
				public void run() {
					instance.repaint();
//					System.out.println(GameData.getAllStringData());
				}
			};
			timer.schedule(task , 0, 1000 / GraphicProperty.fps);
		}
	}
	
	
}
