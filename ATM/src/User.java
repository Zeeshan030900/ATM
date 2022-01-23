import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
	
	private String firstName;
	/* 
	 First name of user
	 */
	private String lastName;
	/*
	 last name of user
	 */
	private String uuid;
	/*
	 ID of the user
	 */
	private byte[] hashPin;
	/*
	 MD5 hash of the user's pin
	 */
	private ArrayList<Account> accounts;
	/*
	 The list of the user's account
	 */
	
	public User(String firstName, String lastName, String pin, Bank theBank) {
			// set user's name
			this.firstName = firstName;
			this.lastName = lastName;
			
			//store the pin's MD5 hash
			try 
			{
				MessageDigest md = MessageDigest.getInstance("MD5");
				this.hashPin =md.digest(pin.getBytes());
			} 
			catch (NoSuchAlgorithmException e) 
			{
				System.err.println("error, caught NoSuchAlgorithmException");
				e.printStackTrace();
				System.exit(1);
			}
			
			// get a new unique ID for the user
			this.uuid = theBank.getNewUserUUID();
			
			// create empty list of accounts
			this.accounts = new ArrayList<Account>();
			
			// print log message
			System.out.printf(" new User %s, %s with ID %s created\n", firstName, lastName, this.uuid);
		}
	/*
	 * Create a new User
	 * param: firstName- user's first name
	 * 		  lastName- user's last name
	 * 		  pin- the user's pin
	 * 		  theBank- the bank object that the user is apart of
	 */
	
	public void addAccount(Account anAcc) 
	{
		this.accounts.add(anAcc);
	}
	/*
	 * Add an account for the user
	 * @param anAcc -  the account to add
	 */
	
	public String getUUID() 
	{
		return this.uuid;
	}
	/*
	 * @return - user UUID
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	public String getLastName()
	{
		return this.lastName;
	}
	public boolean validatePin(String aPin)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.hashPin);
			} 
	    catch (NoSuchAlgorithmException e) 
		{
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	/*
	 * Check if pin is valid or not
	 * @param aPin - The pin to check
	 */
	
	public int numAccounts() 
	{
		return this.accounts.size();
	}
	/*
	 * get the number of accounts
	 * @return- return the size of accounts arrayList
	 */
	public void printAccountsSummary() 
	{
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a<accounts.size(); a++)
		{
			System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}

	public void printAccTransHistory(int accIdx)
	{
		this.accounts.get(accIdx).printTransHistory();		
	}

	public double getAcctBalance(int acctidx) 
	{
		
		return this.accounts.get(acctidx).getBalance();
	}

	public String AcctUUID(int acctIdx)
	{
		
		return this.accounts.get(acctIdx).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) 
	{
		this.accounts.get(acctIdx).addTransaction(amount, memo);
		
	}
}
