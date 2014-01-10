package players;

public enum DifficultyLevel {
	
	FLAWLESS    (  0),
	EXTREME     (  5),
	HARD        ( 15),
	NORMAL      ( 40),
	EASY        ( 65),
	WEAK        ( 95),
	CONFUSED    (100);
	
	private int mistakeChance;
	
	private DifficultyLevel( int mistakeChance )
	{
		this.mistakeChance = mistakeChance;
	}
	
	public int getMistakeChance()
	{
		return this.mistakeChance;
	}

}
