package entities;

import game.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import levels.LevelsData;

import system.Data;

public class Player extends Caracter
{
	private float curSpeed;
	private float targetSpeed = LevelsData.CASE_W / 4;
	private int direction;
	
	public Player(Game _game, Point _nodePosition)
	{
		super(_game, _nodePosition, EntitiesData.PLAYER_W, EntitiesData.PLAYER_H);
	}
	

	public void draw(Graphics g) 
	{
		g.setColor(Color.DARK_GRAY);
		
		g.fillRect(pixel.x, pixel.y, width, height);
	}

	public void update()
	{
		node.setLocation(pixel.x/LevelsData.CASE_W, pixel.y/LevelsData.CASE_H);
		
		fall();
		if(haveToMove())
			move();
		
		me.setLocation(pixel.x, pixel.y);
	}

	public boolean haveToMove()
	{
		return(!game.getModel().getDrag().equals(new Point(-1,-1)));
	}
	
	public void move()
	{
		int pas = LevelsData.CASE_W / 5;
		double offSetX = game.getLevel().getOffSet().getX();
		double offSetY = game.getLevel().getOffSet().getY();
		
		
		// RIGHT
		if(game.getModel().getDrag().x > Data.WINDOW_WIDTH / 2)
		{
//			direction = 1;
//			curSpeed += EntitiesData.PLAYER_ACCELERATION * direction;
//			if (targetSpeed - curSpeed != direction)
//			    curSpeed = targetSpeed;
			
			
			if(pixel.x < Data.WINDOW_WIDTH / 2 - width /2)
			{
				Rectangle tmp = new Rectangle(me.x + pas, me.y, me.width, me.height);
				if(!CollisionTiles(tmp))
					pixel.setLocation(pixel.getX() + pas, pixel.getY());
			}
			else
			{
				int maxOffset = (game.getLevel().getNbCols() - LevelsData.NB_COLS_VIEW) * LevelsData.CASE_W ;
				if(Math.abs(offSetX)<maxOffset)
				{
//					System.out.println("Move Map");
					game.getLevel().getOffSet().setLocation(offSetX - pas, offSetY);
//					pixelStep.setLocation(pixelStep.getX() -pas, pixelStep.getY());
				}
				else
				{
					Rectangle tmp = new Rectangle(me.x + pas, me.y, me.width, me.height);
					if(!CollisionTiles(tmp) && pixel.x < Data.WINDOW_WIDTH - width )
						pixel.setLocation(pixel.getX() + pas, pixel.getY());
				}
			}
				
		}
		// LEFT
		else if(game.getModel().getDrag().x < Data.WINDOW_WIDTH / 2)
		{
//			direction = -1;
//			curSpeed += EntitiesData.PLAYER_ACCELERATION * direction;
//			if (sign(targetSpeed.x - curSpeed.x) != direction.x)
//			    curSpeed.x = targetSpeed.x;
			
			if(pixel.x > Data.WINDOW_WIDTH / 2 - width /2)
			{
				Rectangle tmp = new Rectangle(me.x - pas, me.y, me.width, me.height);
				if(!CollisionTiles(tmp))
					pixel.setLocation(pixel.getX() - pas, pixel.getY());
			}
			else
			{
				//int maxOffset = (game.getLevel().getNbCols() - LevelsData.NB_COLS_VIEW) * LevelsData.CASE_W ;
				if(Math.abs(offSetX)>0)
				{
//					System.out.println("Move Map");
					game.getLevel().getOffSet().setLocation(offSetX + pas, offSetY);
//					pixelStep.setLocation(pixelStep.getX() -pas, pixelStep.getY());
				}
				else
				{
					Rectangle tmp = new Rectangle(me.x - pas, me.y, me.width, me.height);
					if(!CollisionTiles(tmp))
						pixel.setLocation(pixel.getX() - pas, pixel.getY());
				}
			}
//			Rectangle tmp = new Rectangle(me.x - pas, me.y, me.width, me.height);
//			if(!CollisionTiles(tmp))
//				pixel.setLocation(pixel.getX() - pas, pixel.getY());	
		}
		
	}
	
	public void fall()
	{
		int pas = LevelsData.CASE_W / 5;
		Rectangle tmp = new Rectangle(me.x, me.y + pas, me.width, me.height);
		if(!CollisionTiles(tmp))
			pixel.setLocation(pixel.getX(), pixel.getY() + pas);	
	}
//	public boolean isPointed()
//	{
//		return (bounds.contains(game.getModel().getDrag()));
//	}
//	
//	public int defineSide()
//	{
//		return (pixel.x > Data.WINDOW_WIDTH /2) ? Data.RIGHT : Data.LEFT;
//	}
	
//	if(isPointed())
//	{
//		game.getPlayer().setOrientation(defineSide());
//	}
}