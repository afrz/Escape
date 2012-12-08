package system;

import game.Model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyMouseAdapter extends MouseAdapter 
{
	private Model model;
	
	public MyMouseAdapter(Model model) 
	{
		this.model = model;	
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		model.getClic().setLocation(e.getPoint());
	}

	public void mousePressed(MouseEvent e) 
	{
		model.getPressed().setLocation(e.getPoint());
	}

	public void mouseDragged(MouseEvent e) 
	{
		model.getDrag().setLocation(e.getPoint());
	}

	public void mouseReleased(MouseEvent e) 
	{
		model.getReleased().setLocation(e.getPoint());
		model.getPressed().setLocation(-1,-1);
		model.getDrag().setLocation(-1, -1);
		model.getClic().setLocation(-1, -1);
	}
}