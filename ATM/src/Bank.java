import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	/*
	 * Name of bank
	 */
	private ArrayList<User> users;
	/*
	 * List of users
	 */
	private ArrayList<Account> accounts;
	/*
	 * List of accounts
	 */
	
	public Bank (String name)
	{
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	/*
	 * Create a new Bank object with empty lists of users and accounts
	 * @param name - bank name
	 */
	
	//create a new User UUID
	public String getNewUserUUID() 
	{
		//inits
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		
		do 
		{
			//generate the number
			uuid = "";
			for (int c = 0; c< len; c++) 
			{
				uuid +=((Integer)rng.nextInt(10)).toString();
			}
			
			//check to see if it's unique
			nonUnique = false;
			for(User u : this.users) {
				if(uuid.compareTo(u.getUUID())==0) {
					nonUnique = true;
					break;
				}
			}
		}
		while(nonUnique);
		
		return uuid;
	}
	
	//create a new Account UUID
	public String getNewAccountUUID() 
	{
		//inits
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;
		
		do 
		{
			//generate the number
			uuid = "";
			for (int c = 0; c< len; c++) 
			{
				uuid +=((Integer)rng.nextInt(10)).toString();
			}
			
			//check to see if it's unique
			nonUnique = false;
			for(Account a : this.accounts) {
				if(uuid.compareTo(a.getUUID())==0) {
					nonUnique = true;
					break;
				}
			}
		}
		while(nonUnique);
		
		return uuid;
	}
	/*
	 * add an account
	 * @param anAcc - the account to add
	 */
	public void addAccount(Account anAcc)
	{
		this.accounts.add(anAcc);
	}
	/*
	 * Create a new user of the Bank
	 * @param firstName - user's first name
	 * @param lastName - user's last name
	 * @param pin - user's pin
	 * @return - the new User object 
	 */
	public User addUser(String firstName, String lastName, String pin) {
		// create a new User object and add to list
		User newUser = new User(firstName,lastName,pin,this);
		this.users.add(newUser);
		
		//create a savings account for the user and add to User and Bank lists
		Account newAccount = new Account("Savings", newUser, this);
		//add to holder and bank lists
				newUser.addAccount(newAccount);
				this.accounts.add(newAccount);
				
				return newUser;
	}
	public User userLogin (String userID, String pin)
	{
		// search through list of users
		for (User u : users)
		{
			
			//check user ID is correct
			if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)) 
			{
				return u;
			}
		}
		// if we gaven't found the user or the pin was incorrect
		return null;
	}
	
	public String getName()
	{
		return this.name;
	}
}
