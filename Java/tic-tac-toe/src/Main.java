import java.util.Scanner;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		
		char[][] board = new char[3][3]; // Creation of Tic-Tac-Toe board 
						
		for(int i =0;i<3;i++) // Setting the cells as empty.
		{
			for(int j=0;j<3;j++) 
			{
				board[i][j]=' ';
			}
		}
		
        Scanner input = new Scanner(System.in);
        
        String playerMove = new String();
        
		
		printInfo(board); // Printing User Interface
		
		int counter = 0 ; // Counts the Repeat times
		
		while( counter<=8 ) // Game Loop
		{
			System.out.println("\nPlayer Move (X): ");
			
			playerMove = input.nextLine() ;
			
			move(playerMove,board); // Player Move
			
			displayBoard(board) ;
						
			if (counter >=2) //Checking after the third Repeat , the cases of win , defeat and draw 
			{
				if(situationChecker(board) == true) 
					break;
			}
			
			pc_move(board) ; // PC Move	and board print 		

			if (counter >=2) 
			{
				if(situationChecker(board) == true) 
					break;
			}
			
			counter ++ ;
		} // End While Loop 
		
	}
	
public static boolean situationChecker(char board[][])	
{
	// Checks if the last moves result in Win , Defeat , Draw		
	if(win(board) == true) // Win case 
	{
		System.out.println("\nYou win!");
		return true;
		
	}
	if (lose(board) == true) // Defeat case
	{
		System.out.println("\nYou lose!");
		return true;
	}
	if (draw(board) == true ) // Draw case
	{
		System.out.println("\nIt's a draw!");
		return true;
	}
		return false; // False means neither of the above , game continues
}
	
public static void printInfo(char[][] board)
{
	// Prints the starting interface for the user's experience 
	System.out.println("************");
	System.out.println("Tic-Tac-Toe!");
	System.out.println("************\n");
	System.out.println("Please enter the column (A, B or C) and then the row (1, 2, or 3) of your move.\n");
	displayBoard(board);
}

public static void displayBoard(char[][] board) 
{
	// Prints the current situation of the Tic-Tac-Toe board
	System.out.println("   A B C");
    System.out.println("1 " + "|"+ board[0][0] + "|" + board[0][1] + "|" + board[0][2]+ "|" );
    System.out.println("2 " + "|"+ board[1][0] + "|" + board[1][1] + "|" + board[1][2]+ "|" );
    System.out.println("3 " + "|"+ board[2][0] + "|" + board[2][1] + "|" + board[2][2]+ "|" );

}

public static void move(String a, char board[][]) 
{
	/* Checks if the player's move is acceptable , or is already taken and prints the 
	 * right messages .If the player's move is acceptable , puts X on the right place 
	 * r = row , c = column , a = player's move*/
	Scanner second = new Scanner(System.in);
	
	int r=0, c=0;
	
	if(!a.equals("A1")&&!a.equals("A2")&&!a.equals("A3")&&!a.equals("B1")&&!a.equals("B2")&& !a.equals("B3")&&!a.equals("C1")&&!a.equals("C2")&& !a.equals("C3")) // Checks if the move is acceptable 
	{
		System.out.println("Invalid Input: Please enter the column and row of your move (Example: A1)."); 
		System.out.println("\nPlayer Move (X): ");
		a = second.nextLine();
		move(a, board);
	}
	else // if the move is acceptable , transforming the board cells into array cells
	{
		if(a.equals("A1"))
		{
			r=0;
			c=0;
		}
		if(a.equals("A2"))
		{
			r=1;
			c=0;
		}
		if(a.equals("A3"))
		{
			r=2;
			c=0;
		}
		if(a.equals("B1"))
		{
			r=0;
			c=1;
		}
		if(a.equals("B2"))
		{
			r=1;
			c=1;
		}
		if(a.equals("B3"))
		{
			r=2;
			c=1;
		}
		if(a.equals("C1"))
		{
			r=0;
			c=2;
		}
		if(a.equals("C2"))
		{
			r=1;
			c=2;
		}
		if(a.equals("C3"))
		{
			r=2;
			c=2;
		}
		if(board[r][c]!=' ') // Checks if the space is already taken 
		{
			System.out.println("The space entered is already taken.\r\n");
			System.out.println("Player Move (X): ");
			a=second.nextLine();
			move(a, board); // Recall of the move method , so that the new move is checked again 
			
		}
		if(board[r][c]==' ') // If space is empty , places the player's move 
		{
			board[r][c]='X';
		}
	} // End Else
}

public static void pc_move(char board[][]) 
{ 
	/* Uses the Random method to generate the PC move . 
	 * Transforming the integer (0-8) into board spaces 
	 * Using a boolean flag , so that the computer picks an acceptable space .
	 * While loop continues when space is not acceptable 
	 * While loop stops when an acceptable space is picked*/
	
	if (boardFull(board) != true) //  board is full , means that the player has done the last move and game must end , pc move is useless
	{
		int pc_move;
		
		Random random_number = new Random();

		boolean flag = true ;

		while (flag)
		{
			pc_move = random_number.nextInt(9);
			
			if (pc_move == 0) // move 0 equals A1
			{
				if (board[0][0]!='X' && board[0][0]!='O')
				{
					board[0][0]='O';
					flag = false ;
					System.out.println("\nComputer Move (O): A1\n");
				}
			}
			else if (pc_move == 1) // move 1 equals B1
			{
				if (board[0][1] != 'X' && board[0][1] != 'O')
				{
					board[0][1] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): B1\n");
				}
			}
			else if (pc_move == 2) // move 2 equals C1
			{
				if (board[0][2] != 'X' && board[0][2] != 'O')
				{
					board[0][2] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): C1\n");
				}
			}
			else if (pc_move == 3) // move 3 equals A2
			{
				if (board[1][0] != 'X' && board[1][0] != 'O')
				{
					board[1][0] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): A2\n");
				}
			}
			else if (pc_move == 4) // move 4 equals B2
			{
				if (board[1][1] != 'X' && board[1][1] != 'O')
				{
					board[1][1] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): B2\n");
				}
			}
			else if (pc_move == 5) // move  5 equals C2
			{
				if (board[1][2] != 'X' && board[1][2] != 'O')
				{
					board[1][2] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): C2\n");
				}
			}
			else if (pc_move == 6) // move 6 equals A3
			{
				if (board[2][0] != 'X' && board[2][0] != 'O')
				{
					board[2][0] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): A3\n");
				}
			}
			else if (pc_move == 7) // move 7 equals B3
			{
				if (board[2][1] != 'X' && board[2][1] != 'O')
				{
					board[2][1] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): B3\n");
				}
			}
			else if (pc_move == 8) // move 8 equals C3
			{
				if (board[2][2] != 'X' && board[2][2] != 'O')
				{
					board[2][2] = 'O' ;
					flag = false ;
					System.out.println("\nComputer Move (O): C3\n");
				}
			}
		} // end while loop
		displayBoard(board) ;
	} // end if 
	
}

public static boolean win(char board[][])
{
	// Checks if the player wins at all possible scenarios
	char player='X';
			
	if (board[0][0] == player && board[0][1] == player && board[0][2] == player || // 1st row
            board[1][0] == player && board[1][1] == player && board[1][2] == player || // 2nd row
            board[2][0] == player && board[2][1] == player && board[2][2] == player || // 3rd row
            board[0][0] == player && board[1][0] == player && board[2][0] == player || // 1st col
            board[0][1] == player && board[1][1] == player && board[2][1] == player || // 2nd col
            board[0][2] == player && board[1][2] == player && board[2][2] == player || // 3rd col
            board[0][0] == player && board[1][1] == player && board[2][2] == player || // Diagonal           
            board[2][0] == player && board[1][1] == player && board[0][2] == player) //   Diagonal      

            return true;
        else 
        {
            return false;
        }
}

public static boolean lose(char board[][])
{
	// Checks if the player loses at all possible scenarios
	char player='O';
			
	if (board[0][0] == player && board[0][1] == player && board[0][2] == player || // 1st row
            board[1][0] == player && board[1][1] == player && board[1][2] == player || // 2nd row
            board[2][0] == player && board[2][1] == player && board[2][2] == player || // 3rd row
            board[0][0] == player && board[1][0] == player && board[2][0] == player || // 1st col
            board[0][1] == player && board[1][1] == player && board[2][1] == player || // 2nd col
            board[0][2] == player && board[1][2] == player && board[2][2] == player || // 3rd col
            board[0][0] == player && board[1][1] == player && board[2][2] == player || // Diagonal          
            board[2][0] == player && board[1][1] == player && board[0][2] == player) //   Diagonal      

            return true;
        else 
        {
            return false;
        }
}


public static boolean draw(char board[][])
{
	// Checks if game ends in a draw
	if (boardFull(board) == true && win(board) == false && lose(board) == false) // For a draw , game board must be full and neither win or defeat 
	{
		return true;
	}
	else 
	{
		return false ;
	}
}

public static boolean boardFull(char [][] board) 
{
	// Checks if the board is full 
    if (board[0][0] != ' ' && board[0][1] != ' ' && board[0][2] != ' ' &&
        board[1][0] != ' ' && board[1][1] != ' ' && board[1][2] != ' ' &&
        board[2][0] != ' ' && board[2][1] != ' ' && board[2][2] != ' ')

        return true;
    else 
    {
        return false;
    } 
} 

} // END
