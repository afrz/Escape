package system;

import game.Model;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame //implements KeyListener
{
	private static final long serialVersionUID = -2716787098156809733L;
	private Model model;
	private MyMouseAdapter ma;
	private GamePanel gamePanel = new GamePanel();
	private boolean running = true;
	private int frameCount = 0;
	   	   
	public Main()
	{
		initModel();
		initFrame();	
		runGameLoop();
	}
	
	public static void main(String[] args) 
	{
		Main main = new Main();
		main.setVisible(true);
	}
	
	private class GamePanel extends JPanel 
	{
		private static final long serialVersionUID = 608704461673752659L;
		float interpolation;
		
		public void paintComponent(Graphics g) 
		{
			model.draw(g);
		}
		
		public void update()
		{
			model.update();
		}
		
		public void setInterpolation(float interp)
		{
			interpolation = interp;
		}
	}
	
	private void initFrame() 
	{
		setTitle("TestOldSchoolGfx");
		setResizable(false);
		setUndecorated(false);
		
		gamePanel = new GamePanel();	
		gamePanel.setPreferredSize(new Dimension(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT));		
		gamePanel.addMouseListener(ma);
		gamePanel.addMouseMotionListener(ma);
		setContentPane(gamePanel);
		pack();
		setLocationRelativeTo(null);	
		
		addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
			{
				model._keyPressed(e);
			}
			public void keyReleased(KeyEvent e) 
			{
				model._keyReleased(e);
			}
		});
	}

	private void initModel() 
	{
		model = new Model(this);
		ma = new MyMouseAdapter(model);
	}

	//Starts a new thread and runs the game loop in it.
	public void runGameLoop()
	{
		Thread loop = new Thread()
		{
			public void run()
			{
				gameLoop();
			}
		};
		loop.start();
	}
	   
	//Only run this in another Thread!
	private void gameLoop()
	{

		//This value would probably be stored elsewhere.
		final double GAME_HERTZ = 30.0;
		//Calculate how many ns each frame should take for our target game hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		//At the very most we will update the game this many times before a new render.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		//We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		//Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		//If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		//Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running)
		{
			// System.out.println("run");
			double now = System.nanoTime();
			int updateCount = 0;

			//Do as many game updates as we need to, potentially playing catchup.
			while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
			{
				updateGame();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}

			//If for some reason an update takes forever, we don't want to do an insane number of catchups.
			//If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
			if (lastUpdateTime - now > TIME_BETWEEN_UPDATES)
			{
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}

			//Render. To do so, we need to calculate interpolation for a smooth render.
			float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
			drawGame(interpolation);
			lastRenderTime = now;

			//Update the frames we got.
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime)
			{
				//System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
				frameCount = 0;
				lastSecondTime = thisSecond;
			}

			//Yield until it has been at least the target time between renders. This saves the CPU from hogging.
			while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
			{
				Thread.yield();

				//This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
				//You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
				//FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
				try {Thread.sleep(1);} catch(Exception e) {} 

				now = System.nanoTime();
			}
		}
	}

	private void updateGame()
	{
		gamePanel.update();
	}

	private void drawGame(float interpolation)
	{
		gamePanel.setInterpolation(interpolation);
		gamePanel.repaint();
	}
}