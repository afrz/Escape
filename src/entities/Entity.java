package entities;

import game.Game;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import levels.LevelsData;

/** Entity class */
public class Entity  implements Comparable
{
	protected Game game;
	protected Point node;
	protected Point pixel;
	protected int width;
	protected int height;
	protected int depth;
	
	protected Rectangle me = new Rectangle();
	protected Rectangle him = new Rectangle();
	
	public Entity(Game _game, Point _node, int _width, int _height)
	{
		game = _game;
		node = _node;
		width = _width;
		height = _height;
		pixel = new Point(node.x * LevelsData.CASE_W,  node.y * LevelsData.CASE_H);

//		initialize();
	}
	
//	public void initialize()
//	{
//		pixel = new Point(node.x * width,  node.y * height);
//	}
	
	public void draw(Graphics g) 
	{
		
	}
	
	public void update()
	{
		
	}
	
	public boolean Collision(Rectangle box1, Rectangle box2)
	{
	   if((box2.x >= box1.x + box1.width)      // trop à droite
		|| (box2.x + box2.width <= box1.width) // trop à gauche
		|| (box2.y >= box1.y + box1.height) // trop en bas
		|| (box2.y + box2.height <= box1.height))  // trop en haut
	          return false; 
	   else
	          return true; 
	}

	public boolean CollisionTile(int x,int y)
	{
	   int i = pixel.x/LevelsData.CASE_W;   
	   int j = pixel.y/LevelsData.CASE_H;
	   return TileIsMur(i,j);
	}
	
	public boolean TileIsMur(int i,int j)
	{
		return game.getLevel().getTypeCase(i, j).equals("w");
	}
	
	public boolean CollisionTiles(Rectangle box)
	{
	   int i1 = box.x/LevelsData.CASE_W;;
	   int j1 = box.y/LevelsData.CASE_H;
	   int i2 = (box.x + box.width -1)/LevelsData.CASE_W;  
	   int j2 = (box.y + box.height -1)/LevelsData.CASE_H;
	   int i,j;
	   for(i=i1;i<=i2;i++)
	   {
	     for(j=j1;j<=j2;j++)
	     {
	         if (TileIsMur(i,j))
	             return true;
	     }
	   }
	   return false;  // si on n'est pas sorti avant, c'est qu'on ne touche aucun tile.
	}

	
	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}




	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public Rectangle getMe() {
		return me;
	}

	public void setMe(Rectangle me) {
		this.me = me;
	}

	public Rectangle getHim() {
		return him;
	}

	public void setHim(Rectangle him) {
		this.him = him;
	}

	@Override
	public int compareTo(Object arg0) 
	{
		Entity e = (Entity) arg0;
		
		if(	depth < e.getDepth())
		{
			/* instance lt received */
			return -1;
		}
		else if(depth > e.getDepth())
		{
			/* instance gt received */
			return 1;
		}
		/* instance == received */
		return 0;
	}
}
