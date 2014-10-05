/**
   COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved.
   CS 151 Kim Assignment #1
   @author Ly Dang
   @version 1.01 2014/9/7
*/

/**
 * A user's account. It may be a savings or checking account.
 */
public class Account
{
	private String type;
	private int accountNum;
	private double balance;
	
	/**
	 * Constructs the account with type and account number. The account is 
	 * pre-loaded with $40.
	 * @param type type of the account (either savings or checking)
	 * @param accountNum the account's number
	 */
	public Account(String type, int accountNum)
	{
		this.type = type;
		this.accountNum = accountNum;
		balance = 40.0;
	}
	
	/**
	 * Gets the account's type.
	 * @return the account's type (either savings or checking)
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Gets the acount's number.
	 * @return account's number
	 */
	public int getAccountNum()
	{
		return accountNum;
	}
	
	/**
	 * Gets the account's balance.
	 * @return account's balance
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * Sets the account's balance.
	 * @param balance balance to set
	 */
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
}