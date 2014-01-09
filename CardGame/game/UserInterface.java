package game;

import java.util.Scanner;

public class UserInterface {
	
	private Scanner scanner;
	
	public UserInterface(Scanner scanner)
	{
		this.scanner = scanner;
	}
	
	public UserInterface()
	{
		this(new Scanner(System.in));
	}
	
	public String ask(String question)
	{
		this.display(question + " ");
		return this.scanner.nextLine();
	}

	public int askForIntInRange(String question, int min, int max) {
		this.display(question + " [" + min + "-" + max + "] : ");
		return this.nextValidInt(min, max);
	}

	public int nextValidInt(int min, int max) {
		int result;
		while( true )
		{
			while( !this.scanner.hasNextInt() )
			{
				this.scanner.next();
			}
			result = this.scanner.nextInt();
			
			if( result < min || result > max ) {
				this.display("That number is too big or too small.  Please try again: ");
			}
			else
			{
				break;
			}
		}
		return result;
	}

	public void display(String string)
	{
		System.out.print(string);
	}

	public void line()
	{
		this.display("***********************************************************\n");
	}

	public void line(int times)
	{
		for( int i = 0; i < times; i++ ) this.line();
	}

}