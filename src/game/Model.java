package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;

import system.Data;

public class Model
{
	private JFrame jFrame;
	private Game game;
	
	private int orientation;
	
	private Point clic;
	private Point drag;
	private Point pressed;
	private Point released;
	
	public Model(JFrame _jFrame)
	{
		jFrame = _jFrame;
		clic = new Point(-1,-1);
		drag = new Point(-1,-1);
		pressed = new Point(-1,-1);
		released = new Point(-1,-1);
		game = new Game(this);
	}
	
	public void update()
	{
		game.update();
//		System.out.println(drag);
	}

	public void draw(Graphics g) 
	{
		game.draw(g);
	}
		
	public void _keyPressed(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)	{orientation = Data.RIGHT;} 
		if (event.getKeyCode() == KeyEvent.VK_LEFT)		{orientation = Data.LEFT;} 
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE)	{System.exit(0);}
	}
	
	public void _keyReleased(KeyEvent event) 
	{
		orientation = Data.WAIT;
	}

	public JFrame getjFrame() {
		return jFrame;
	}

	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public Point getClic() {
		return clic;
	}

	public void setClic(Point clic) {
		this.clic = clic;
	}

	public Point getDrag() {
		return drag;
	}

	public void setDrag(Point drag) {
		this.drag = drag;
	}

	public Point getPressed() {
		return pressed;
	}

	public void setPressed(Point pressed) {
		this.pressed = pressed;
	}

	public Point getReleased() {
		return released;
	}

	public void setReleased(Point released) {
		this.released = released;
	}
	
	
}
