import java.util.Scanner;
public class ATM {

	public static void main(String[] args) {
		// init Scanner
		Scanner sc = new Scanner(System.in);
		
		//init Bank
		Bank theBank = new Bank("Bank of England");
		
		//add a user, which creates a saving account
		User user1 = theBank.addUser("Zeeshan", "Khan", "7861");
		
		// add a checking account for our user
		Account account1 = new Account("Checking", user1, theBank);
		user1.addAccount(account1);
		theBank.addAccount(account1);
		System.out.println(theBank.getNewAccountUUID());
		
		User curUser;
		while(true) 
		{
		//stay in login prompt until successful
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			//stay in main menu until the user quits
			ATM.printUserMenu(curUser, sc);
			
		}
	}
	
	public static User mainMenuPrompt(Bank theBank, Scanner sc)
	{
		// inits
		String userID;
		String pin;
		User authUser;
		
		do
		{
			//prompt user for ID/pin combo until correct one is reached
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter user ID:");
			userID = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			//try to get the user object corresponding to the ID and pin
			authUser = theBank.userLogin(userID, pin);
			if(authUser == null)
			{
				System.out.println("Incorrect ID or pin." + "Please try again.");
			}
		} while(authUser == null); //continue looping until successful login
		
		return authUser;
	}
	/*
	 * Menu for logging in to bank account
	 */
	
	public static void printUserMenu(User theUser, Scanner sc)
	{
		// print a summary of the user's accounts
		theUser.printAccountsSummary();
		
		//init
		
		int choice;
		
		// user menu
		do
		{
			System.out.printf("Welcome %s, What would you like to do?", theUser.getFirstName());
			System.out.println("\n 1) show account transaction history");
			System.out.println(" 2) Withdraw");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.println();
			System.out.print("Enter choice");
			choice = sc.nextInt();
			
			if (choice <1 || choice >5)
			{
				System.out.println("Please choice between the numbers 1-5");
				System.out.println(88);
			}
		}while (choice <1 || choice >5);
		
		//process the choice
		switch (choice)
		{
		case 1:
			ATM.showTransHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.despositFunds(theUser, sc);
				break;
		case 4:
			ATM.transferFunds(theUser, sc);
				break;
		case 5:
			sc.nextLine();
			break;
		}
		//redisplay menu until user quits
		if(choice !=5) 
		{
			ATM.printUserMenu(theUser, sc);
		}
	}
	
	public static void despositFunds(User theUser, Scanner sc) 
	{
		//inits
		int toAcc;
		double amount;
		double accBal;
		String memo;
		
		// get the account to transfer from
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from: ", theUser.numAccounts());
			toAcc = sc.nextInt() - 1;
			if(toAcc <0 || toAcc >= theUser.numAccounts())
			{
				System.out.println("Invalid ammount. Please try again");
			}
		} while(toAcc <0 || toAcc >= theUser.numAccounts());
		accBal = theUser.getAcctBalance(toAcc);
		
		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", accBal);
			amount = sc.nextDouble();
			if(amount<0) 
			{
				System.out.println("Amount must be greater than 0");
			}
		}while(amount <0);
		sc.nextLine();
		
		//get a memo
		System.out.println("Enter a memo");
		memo = sc.nextLine();
		
		//do the withdrawal
		theUser.addAcctTransaction(toAcc, 1*amount, memo);
		
	}

	public static void withdrawFunds(User theUser, Scanner sc)
	{
		
				//inits
				int fromAcc;
				double amount;
				double accBal;
				String memo;
				
				// get the account to transfer from
				
				do {
					System.out.printf("Enter the number (1-%d) of the account to transfer from", theUser.numAccounts());
					fromAcc = sc.nextInt() - 1;
					if(fromAcc <0 || fromAcc >= theUser.numAccounts())
					{
						System.out.println("Invalid ammount. Please try again");
					}
				} while(fromAcc <0 || fromAcc >= theUser.numAccounts());
				accBal = theUser.getAcctBalance(fromAcc);
				
				// get the amount to transfer
				do {
					System.out.printf("Enter the amount to transfer (max $%.02f): $", accBal);
					amount = sc.nextDouble();
					if(amount<0) 
					{
						System.out.println("Amount must be greater than 0");
					}
					else if(amount > accBal)
					{
						System.out.printf("Amount must not be greater than balance of $%.02f.\n", accBal);
					}
				}while(amount <0 || amount>accBal);
				sc.nextLine();
				
				//get a memo
				System.out.println("Enter a memo");
				memo = sc.nextLine();
				
				//do the withdrawal
				theUser.addAcctTransaction(fromAcc, -1*amount, memo);
	}

	public static void transferFunds(User theUser, Scanner sc) 
	{
		
		//inits
		int fromAcc;
		int toAcc;
		double amount;
		double accBal;
		
		// get the account to transfer from
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from", theUser.numAccounts());
			fromAcc = sc.nextInt() - 1;
			if(fromAcc <0 || fromAcc >= theUser.numAccounts())
			{
				System.out.println("Invalid ammount. Please try again");
			}
		} while(fromAcc <0 || fromAcc >= theUser.numAccounts());
		accBal = theUser.getAcctBalance(fromAcc);
		
		//get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer to", theUser.numAccounts());
			toAcc = sc.nextInt() - 1;
			if(toAcc <0 || toAcc >= theUser.numAccounts())
			{
				System.out.println("Invalid ammount. Please try again");
			}
		} while(toAcc <0 || toAcc >= theUser.numAccounts());
		
		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", accBal);
			amount = sc.nextDouble();
			if(amount<0) 
			{
				System.out.println("Amount must be greater than 0");
			}
			else if(amount > accBal)
			{
				System.out.printf("Amount must not be greater than balance of $%.02f.\n", accBal);
			}
		}while(amount <0 || amount>accBal);
		
		//do the transfer
		theUser.addAcctTransaction(fromAcc, -1*amount, String.format("Transfer to account %s", theUser.AcctUUID(toAcc)));
		theUser.addAcctTransaction(toAcc, amount, String.format("Transfer to account %s", theUser.AcctUUID(fromAcc)));

	}

	public static void showTransHistory(User theUser, Scanner sc) 
	{
		int theAcc;
		do {
			//get account whose transaction history to look at
			System.out.printf("Enter the number (1-%d) of the account whose transactions you want to see:", theUser.numAccounts());
			theAcc = sc.nextInt()-1;
			if(theAcc <0 || theAcc>= theUser.numAccounts())
			{
				System.out.println("Invalid ammount. Please try again");
			}
		} while(theAcc <0 || theAcc>= theUser.numAccounts());
		//print the transaction history
		theUser.printAccTransHistory(theAcc);
	}
	/*
	 * Show transaction history of the user
	 */
}

