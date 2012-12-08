package levels;

import game.Game;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import system.Data;

public class Case 
{
		// Coordonnés
		private Point node;
		private Point pixel;
		
		// Propriétés
		private int width = LevelsData.CASE_W;
		private int height = LevelsData.CASE_H;
		private Rectangle2D bounds;
		private String type;
		
		// Model associé
		private Game game;
		
		// Affichage
		private int fontSize;
		private Font f;
		private String sInfo;
		private TextLayout tInfo;

		public Case(Game _game, int _col, int _row, String _type)
		{
			game = _game;
			type = _type;

			node = new Point(_col, _row);
			pixel = new Point(_col * width,  _row * height);
			bounds = new Rectangle2D.Double(pixel.x,pixel.y,width,height);
			
			fontSize = 10;
			f = new Font("Arial",Font.PLAIN, fontSize);
			sInfo = new String(node.x + "-" + node.y);
		}
		
		public void draw(Graphics g) 
		{
			if(!Data.DEBUG)
			{

			}
			
			if(Data.DEBUG)
			{
//				if(type.equals("0"))
//				{
//					g.setColor(Color.WHITE);
//					g.fillRect(pixel.x, pixel.y, width, height);
//				}
				if(type.equals("w"))
				{
					g.setColor(Color.GREEN);
					g.fillRect(pixel.x, pixel.y, width, height);
				}
			}

			Graphics2D g2 = (Graphics2D) g;
//			float alpha = (float) 0.15;
			
//			AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//			g2.setComposite(composite);

			FontRenderContext frc = ((Graphics2D) g).getFontRenderContext();
			tInfo = new TextLayout(sInfo, f, frc);
			g.setColor(Color.BLACK);
			g.drawRect(pixel.x, pixel.y, width, height);
			tInfo.draw((Graphics2D) g,pixel.x, pixel.y + fontSize);
//			composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
//			g2.setComposite(composite); 
		}
				
		public void update(Point _offSet)
		{
			pixel.setLocation(
					 _offSet.getX() + (node.getX() * width), 
					 _offSet.getY() + (node.getY() * height)
					);
			
			bounds.setRect(pixel.getX(),pixel.getY(),width,height);
//			System.out.println(this + " " + node.x  + " " + node.y);
//			if(isPointed())
//			{
//				game.getPlayer().setOrientation(defineSide());
//			}
				
		}

//		public boolean isPointed()
//		{
//			return (bounds.contains(game.getModel().getDrag()));
//		}
//		
//		public int defineSide()
//		{
//			return (pixel.x > Data.WINDOW_WIDTH /2) ? Data.RIGHT : Data.LEFT;
//		}
		
		public Point getPoint() {
			return node;
		}

		public void setPoint(Point point) {
			this.node = point;
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

		public Rectangle2D getBounds() {
			return bounds;
		}

		public void setBounds(Rectangle2D bounds) {
			this.bounds = bounds;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Game getGame() {
			return game;
		}

		public void setGame(Game game) {
			this.game = game;
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
}
