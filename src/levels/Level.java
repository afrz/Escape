package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import system.Data;
import game.Game;
import game.RessourcesFactory;

public abstract class Level 
{
	protected Game game;
	protected String MapFilePath;
	protected int nbCols, nbRows;
	protected Case[][] terrain;
	protected Point offSet = new Point(0,0);
	protected int minx,maxx,miny,maxy;
	protected Scanner m;
	protected String MapTxtFile[];// =  new String[100];
	
	public Level(Game _game)
	{
		game = _game;
	}
	
	public void initialize()
	{
		
		MapTxtFile = new String[nbCols];
		terrain = new Case[nbCols][nbRows];

		openFile();
		readFile();
		closeFile();
		//System.out.println(MapTxtFile);
		for(int y=0; y <nbRows; y++)
		{
			for(int x=0; x <nbCols; x++)
			{
//				System.out.println(x + " " + y);
				terrain[x][y]=new Case(game, x, y, getTypeCase(x, y));
			}
		}
	}
	
	public void updateFocus()
	{
		minx = (int) (Math.abs(offSet.getX()) / LevelsData.CASE_W);
		maxx = (int) ((Math.abs(offSet.getX()) + LevelsData.CASE_W * LevelsData.NB_COLS_VIEW)/LevelsData.CASE_W);
		if(maxx < nbCols) maxx++;
		
		miny = (int) (Math.abs(offSet.getY()) / LevelsData.CASE_H);		
		maxy = (int) ((Math.abs(offSet.getY()) + LevelsData.CASE_H * LevelsData.NB_ROWS_VIEW)/LevelsData.CASE_H);
		if(maxy < nbRows) maxy++;
		
		System.out.println(minx + " " + miny + " " + maxx + " " + maxy);
	}
	
	public void update()
	{
		updateFocus();
		int i,j;
		for(i = minx; i< maxx; i++)
		{
			for(j = miny; j< maxy; j++)
			{
				terrain[i][j].update(offSet);
			}
		}
		
//		for(int i = 0; i < nbCols; i++)
//		{
//			for(int j = 0; j < nbRows ; j++)
//			{
////				System.out.println(i + " " + j);
//				terrain[i][j].update();
//			}
//		}
	}
	
	public void draw(Graphics g) 
	{
		drawBackground(g);
		drawMiddleground(g);
		
		int i,j;
		for(i = minx; i< maxx; i++)
		{
			for(j = miny; j< maxy; j++)
			{
				terrain[i][j].draw(g);
			}
		}
//		for(int i = 0; i < nbCols; i++)
//		{
//			for(int j = 0; j < nbRows ; j++)
//			{
//				terrain[i][j].draw(g);
//			}
//		}
	}
	
	public void drawBackground(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
//		for(int i=0; i <)
	}
	
	public void drawMiddleground(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
	}
	
	public void openFile()
	{
		try
		{
			m = RessourcesFactory.getInstance().getMapFile(MapFilePath);
		}
		catch(Exception e)
		{
			System.out.println("ERROR LOADING MAP");
		}
	}

	public void readFile()
	{
		while(m.hasNext())
		{
			for(int i = 0; i<nbRows; i++)
			{
//				System.out.println(i);
				MapTxtFile[i] = m.next();
//				System.out.println(MapTxtFile[i]);
			}
		}
//		System.out.println(MapTxtFile);
	}

	public void closeFile()
	{
		m.close();
	}
	
	public String getTypeCase(int x, int y)
	{
		String index = "0";
		index = MapTxtFile[y].substring(x, x + 1);
//		System.out.println(index);
		return index;
	}

	
	
	
	public Case[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(Case[][] terrain) {
		this.terrain = terrain;
	}

	public Point getOffSet() {
		return offSet;
	}

	public void setOffSet(Point offSet) {
		this.offSet = offSet;
	}

	public int getNbCols() {
		return nbCols;
	}

	public void setNbCols(int nbCols) {
		this.nbCols = nbCols;
	}

	public int getNbRows() {
		return nbRows;
	}

	public void setNbRows(int nbRows) {
		this.nbRows = nbRows;
	}
	
	
}
