package entities;

import game.Game;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import levels.Level;
import system.Data;

public class Caracter extends Entity
{

	protected int fontSize;
	protected Font f;
	protected String sInfo;
	protected TextLayout tInfo;
	
	protected int orientation;
	
	public Caracter(Game _game, Point _node, int _width, int _height)
	{
		super(_game, _node, _width, _height);

		fontSize = 10;
		f = new Font("Arial",Font.PLAIN, fontSize);
		sInfo = new String(node.x + "-" + node.y);
		orientation = Data.RIGHT;
		me.setBounds(pixel.x,pixel.x,width,height);
	}
		
	public void update()
	{
		
	}

	public void draw(Graphics g) 
	{
		super.draw(g);
	}
		
	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}

	public String getsInfo() {
		return sInfo;
	}

	public void setsInfo(String sInfo) {
		this.sInfo = sInfo;
	}

	public TextLayout gettInfo() {
		return tInfo;
	}

	public void settInfo(TextLayout tInfo) {
		this.tInfo = tInfo;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	
}
