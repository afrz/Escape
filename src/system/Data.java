package system;

import levels.LevelsData;

public class Data
{
	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;	
	public final static int WAIT = 0;
	
	public static boolean DEBUG = true;
	public static int FRAMES_PER_SECOND = 30;
	public static int FRAME_RATE = 1000 / FRAMES_PER_SECOND;
	public static int WINDOW_WIDTH = 800;
	public static int WINDOW_HEIGHT = 500;
	
	public static String DIRECTORY_FILES = "files/";
}
