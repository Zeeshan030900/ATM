import java.util.ArrayList;

public class Account {
	private String name;
	/*
	 Name of the account
	 */
	private String uuid;
	/*
	 Account ID number
	 */
	private User holder;
	/*
	 The user object that owns the account
	 */
	private ArrayList<Transaction> transactions;
	/*
	 The list of transactions
	 */
	
	/*
	 * Create a new Account
	 * param:
	 * 		 name- name of the account
	 * 		 holder- The user that holds the account
	 * 		 theBank- The bank that issues the account
	 */
	public Account(String user, User holder, Bank theBank) {
		// set the account name and holder
		this.name = name;
		this.holder = holder;
		
		// get new account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		// Initialise transactions
		this.transactions = new ArrayList<Transaction>();
		
	}

	public String getUUID() 
	{
		return this.uuid;
	}
	/*
	 * Get account ID
	 */
	public String getSummaryLine()
	{
		// get balance
		double balance = this.getBalance();
		
		//format summary line, depending if account is negative
		if(balance >=0)
		{
			return String.format("%s : $%.02f : %s", this.uuid,balance, this.name);
		}
		else
		{
			return String.format("%s : $(%.02f) : %s", this.uuid,balance, this.name);
		}
	}
	/*
	 * Get summary of the account
	 */
	public double getBalance()
	{
		double balance = 0;
		for ( Transaction t : this.transactions)
		{
			balance += t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() 
	{
		System.out.printf("\nTranscation History %s\n", this.uuid);
		for( int t =this.transactions.size()-1; t>= 0; t--)
		{
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		//create new transaction object and add to list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
		
	}
}
