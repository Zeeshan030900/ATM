import java.util.Date;

public class Transaction {
	private double amount;
	/*
	 AMount of this transaction
	 */
	private Date timestamp;
	/*
	 Date and time of transaction
	 */
	private String memo;
	/*
	 A memo for the transaction
	 */
	private Account inAccount;
	/*
	 The account which the transaction was performed
	 */
	
	
	public Transaction(double amount, Account inAccount) 
	{
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
				
	}
	/*
	 * Create a new transaction
	 * @param amount - amount that was transacted
	 * @param inAccount - the account the transaction belongs to 
	 */
	
	public Transaction(double amount, String memo, Account inAccount) 
	{
		// call the two arguments constructor
		this(amount, inAccount);
		
		// set memo
		this.memo = memo;
	}
	/*
	 * Create a new transaction
	 * @param amount - amount that was transacted
	 * @param inAccount - the account the transaction belongs to 
	 * @param memo - the memo for the transaction
	 */
	public double getAmount()
	{
		return this.amount;
	}
	/*
	 * @return - the account balance
	 */

	public String getSummaryLine() {
		
		if(this.amount >= 0)
		{
			return String.format("%s : $%.02f : %s\n", this.timestamp.toString(), this.amount, this.memo);
		}
		else
		{
			return String.format("%s : $(%.02f) : %s\n", this.timestamp.toString(), this.amount, this.memo);
		}
	}
	/*
	 * Get a string that summaries the transactions
	 */
}
