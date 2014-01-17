package players;

public enum DifficultyLevel {
	
	FLAWLESS    (  0, "Computer always plays the best card."),
	EXTREME     (  5, "Very small chance the computer will play a random card."),
	HARD        ( 15, "Small chance the computer will play a random card."),
	NORMAL      ( 40, "Medium chance the computer will play a random card."),
	EASY        ( 65, "Medium chance the computer will play the best card."),
	WEAK        ( 95, "Very small chance the computer will play the best card."),
	CONFUSED    (100, "Computer always plays a random card.");
	
	public static int longestStringVal()
	{
		int longest = 0;
		for( DifficultyLevel d : DifficultyLevel.values() )
		{
			if( d.toString().length() > longest )
			{
				longest = d.toString().length();
			}
		}
		return longest;
	}
	
	private int mistakeChance;
	private String description;
	
	private DifficultyLevel( int mistakeChance, String description )
	{
		this.mistakeChance = mistakeChance;
		this.description = description;
	}
	
	public int getMistakeChance()
	{
		return this.mistakeChance;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String toString()
	{
		String strValue = super.toString().toLowerCase();
		
		// Capitalizes the first letter
		strValue = "" + (char)(strValue.charAt(0) - 32) + strValue.substring(1, strValue.length());
	
		return strValue;
	}

}
