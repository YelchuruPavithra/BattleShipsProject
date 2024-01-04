package Mypackage;
import java.util.Scanner;

//1st step
class PrintOcean
{
	//printing ocean
	static void print()
	{
		System.out.print("  ");
		for(int i=0;i<10;i++)
		{
			System.out.print(i);
		}
		System.out.println();
		for(int i=0;i<10;i++)
		{
			System.out.print(i+"|");
			for(int j=0;j<10;j++)
			{
				if(BattleShips.Battleshiparray[i][j]==null)
				{
					BattleShips.Battleshiparray[i][j]=" ";
					System.out.print(BattleShips.Battleshiparray[i][j]);
				}
					
				//hiding computer's ships
				else if(BattleShips.Battleshiparray[i][j]=="x")
					System.out.print(" ");
				else
					System.out.print(BattleShips.Battleshiparray[i][j]);
			}
			
			System.out.println("|"+i);
		
		}
		System.out.print("  ");
		for(int i=0;i<10;i++)
		{
			System.out.print(i);
		}
		System.out.println();
	}
}

//2nd step-deploying players ships
class DeployPlayerShips 
{
	
	Scanner sc=new Scanner(System.in);
	int x,y;
	 
	
	void deploy()
	{
		System.out.println("Deploy your ships");
		
		for(int i=0;i<5;) 
		{
			//taking coordinates from user
			System.out.print("Enter x coordinate of your "+(i+1)+"ship:");
			x=sc.nextInt();
			System.out.print("Enter y coordinate of your "+(i+1)+"ship:");
			y=sc.nextInt();
			
			//deploying player's ships with @
			if((x>=0&&x<10) && (y>=0&&y<10) && BattleShips.Battleshiparray[x][y]==" ")
			{
				BattleShips.Battleshiparray[x][y]="@";
				i++;
			}
			else if((x>=0&&x<10) && (y>=0&&y<10) && BattleShips.Battleshiparray[x][y]=="@")
			{
				System.out.println("You cannot place two or more ships on the same location");
			}
			else if((x<0||x>=10) || (y<0||y>=10))
			{
				System.out.println("You can't place outside the 10 by 10 grid");
			}
		}
		PrintOcean.print();
	}
}

//3rd step-deploying computers ships
class DeployComputerShips
{
	int x,y;
	
	void deploy() 
	{
		System.out.println("computer is deploying ships");
		for(int i=0;i<5;) 
		{
			//generating random coordinates for computer
			x=(int)(Math.random()*10);
			y=(int)(Math.random()*10);
			
			//deploying computer's ships with x
			if((x>=0&&x<10) && (y>=0&&y<10) && BattleShips.Battleshiparray[x][y]==" ")
			{
				BattleShips.Battleshiparray[x][y]="x";
				i++;
			}
			else if((x>=0&&x<10) && (y>=0&&y<10) && BattleShips.Battleshiparray[x][y]=="@"||BattleShips.Battleshiparray[x][y]=="x")
			{
				System.out.println("You cannot place two or more ships on the same location");
			}
			System.out.println("ship deployed");
			
		}
		PrintOcean.print();
	}
}


class playersTurn
{
	Scanner sc=new Scanner(System.in);
	int x,y;
	void playersTurnresult()
	{
		//player guessing the coordinates of computers ship
		do{
			System.out.println("player's turn");
			System.out.print("Enter x coordinate of your ship:");
			x=sc.nextInt();
			System.out.print("Enter y coordinate of your ship:");
			y=sc.nextInt();
			if((x<0||x>=10) || (y<0||y>=10))
			{
				System.out.println("You can't place outside the 10 by 10 grid");
			}
			else if(BattleShips.Battleshiparray[x][y]=="x")
			{
				System.out.println("Boom! You sunk the ship!");
				BattleShips.Battleshiparray[x][y]="!";
				BattleShips.playerscore++;
				BattleShips.computerscore--;
			}
			else if(BattleShips.Battleshiparray[x][y]=="@")
			{
				System.out.println("oh no,you sunk your own ship");
				BattleShips.Battleshiparray[x][y]="!";
				BattleShips.playerscore--;
			}
			else if(BattleShips.Battleshiparray[x][y]==" ")
			{
				System.out.println("sorry you missed");
				BattleShips.Battleshiparray[x][y]="-";
			}
			System.out.println("playerscore:"+BattleShips.playerscore+"  "+"computerscore:"+BattleShips.computerscore);
			System.out.println();
		}while((x<0||x>=10) || (y<0||y>=10));
	}
}


class compuersTurn
{
	int x,y;
	void computersTurnresult()
	{
		//computer guessing the coordinates of players ships
			do{
				System.out.println("comuter's turn");
				x=(int)(Math.random()*10);
				y=(int)(Math.random()*10);
				if((x<0||x>=10) || (y<0||y>=10))
				{
					System.out.println("You can't place outside the 10 by 10 grid");
				}
				else if(BattleShips.Battleshiparray[x][y]=="@")
				{
					System.out.println("player loses ship");
					BattleShips.Battleshiparray[x][y]="!";
					BattleShips.playerscore--;
					BattleShips.computerscore++;
				}
				else if(BattleShips.Battleshiparray[x][y]=="x")
				{
					System.out.println("computer sunks its own ship");
					BattleShips.Battleshiparray[x][y]="!";
					BattleShips.computerscore--;
				}
				else if(BattleShips.Battleshiparray[x][y]==" ")
				{
					System.out.println("computer missed");
				}
				System.out.println("playerscore:"+BattleShips.playerscore+"  "+"computerscore:"+BattleShips.computerscore);
				System.out.println();
			}while((x<0||x>=10) || (y<0||y>=10));
	}
}

//4th step-playing
class play
{
	playersTurn pt=new playersTurn();
	compuersTurn ct=new compuersTurn();
	void playing()
	{
		do 
		{
			pt.playersTurnresult();
			ct.computersTurnresult();
			PrintOcean.print();
		}while(BattleShips.playerscore!=0&&BattleShips.computerscore!=0);
	}
}
//5th step-game over
class Gameover
{
	void game()
	{	
		System.out.println("playerscore:"+BattleShips.playerscore+"  "+"computerscore:"+BattleShips.computerscore);
		if(BattleShips.playerscore==0)
			System.out.println("computer won");
		else
			System.out.println("Hurray! you won");
	}
		
}

//main class
public class BattleShips {
	//static array to store the ships coordinates
	public static String Battleshiparray[][]=new String[10][10]; 
	//static variable to store player's score
	static int playerscore=5;
	//static variable to store computer's score
	static int computerscore=5;
	//main method
	public static void main(String[] args) {
		
		System.out.println("**** Welcome to Battle Ships game ****");
		System.out.println("Right now the ship is empty.");

		//calling static method to print ocean-1st step
		PrintOcean.print();
		
		//deploying player's ships-2nd step
		DeployPlayerShips dp=new DeployPlayerShips();
		dp.deploy();
		
		//deploying computer's ships-3rd step
		DeployComputerShips dc=new DeployComputerShips();
		dc.deploy();
		
		//playing the battle-4th step
		play p=new play();
		p.playing();
		
		//game over-5th step
		Gameover gm=new Gameover();
		gm.game();	
		
	}

}
