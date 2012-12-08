package game;

import java.awt.Graphics;
import java.awt.Point;

import entities.Player;

import levels.Level;
import levels.Level_1;

public class Game 
{
	private Model model;
	private Player player;
	private Level level;
	
	public Game(Model _model)
	{
		initGame(_model);
	}
	
	public void initGame(Model _model)
	{
		model = _model;
		level = new Level_1(this);
		player = new Player(this, new Point(2,2));
//		System.out.println(level.getTerrain());
		
//		player = new Player(this);
	}
	
	public void update()
	{
		level.update();
		player.update();
	}
	
	public void draw(Graphics g) 
	{
		level.draw(g);
		player.draw(g);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
