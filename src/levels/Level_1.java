package levels;

import system.Data;
import game.Game;

public class Level_1 extends Level
{
	public Level_1(Game _game)
	{
		super(_game);
		MapFilePath = Data.DIRECTORY_FILES + "level1.txt";
		nbCols = LevelsData.LEVEL1_COLS;
		nbRows = LevelsData.LEVEL1_ROWS;
		initialize();
	}
}
