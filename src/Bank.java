import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
   COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved.
   CS 151 Kim Assignment #1
   @author Ly Dang
   @version 1.01 2014/9/7
*/

/**
 * Representation of a bank.
 */
public class Bank
{
	private String bankID;
	private BankComputer computer;
	private HashMap<Customer, ArrayList<Account>> customers;
	private HashMap<CashCard, String> cashCards;
	private int nextCardNum;
	
	/**
	 * Constructs a bank with a bank ID.
	 * @param bankID ID of the bank
	 */
	public Bank(String bankID)
	{
		this.bankID = bankID;
		customers = new HashMap<Customer, ArrayList<Account>>();
		cashCards = new HashMap<CashCard, String>();
		nextCardNum = 1;
	}

	/**
	 * Sets the bank's computer and sets this bank as the computer's bank.
	 * @param computer the computer to set
	 */
	public void setComputer(BankComputer computer)
	{
		this.computer = computer;
		computer.setBank(this);
	}
	
	/**
	 * Gets the computer of this bank.
	 * @return this bank's computer
	 */
	public BankComputer getComputer()
	{
		return computer;
	}
	
	/**
	 * Gets this bank's ID.
	 * @return the bank's ID
	 */
	public String getBankID()
	{
		return bankID;
	}
	
	/**
	 * Adds a customer to this bank along with its associated accounts.
	 * @param customer customer to add
	 * @param acct1 account to add (may be null)
	 * @param acct2 account to add (may be null)
	 */
	public void addCustomer(Customer customer, Account acct1, Account acct2)
	{
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(acct1);
		accounts.add(acct2);
		customers.put(customer, accounts);
	}
	
	/**
	 * Removes a customer from this bank.
	 * @param customer customer to remove
	 */
	public void removeCustomer(Customer customer)
	{
		customers.remove(customer);
	}
	
	/**
	 * Issues a card to a customer. Cash card numbers start from 1.
	 * @param expirationDate expiration date of the card
	 * @param password password of the card
	 * @return issued card
	 */
	public CashCard issueCard(GregorianCalendar expirationDate, String password)
	{
		CashCard newCard = new CashCard(bankID, nextCardNum, expirationDate);
		cashCards.put(newCard, password);
		nextCardNum++;
		return newCard;
	}
	
	/**
	 * Verifies a user's inputed password.
	 */
	public boolean verifyPassword(String input, CashCard insertedCard)
	{
		if (cashCards.containsKey(insertedCard))
		{
			if (cashCards.get(insertedCard).equals(input))
				return true;
		}
		return false;
	}
	
	/**
	 * Reduces an accounts balance.
	 * @param amount amount to reduce
	 * @param account account to reduce balance of
	 */
	public void reduceBalance(int amount, Account account)
	{
		account.setBalance(account.getBalance() - amount);
	}
	
	/**
	 * Returns a string representation of the bank's state.
	 * @return the bank's state
	 */
	public String toString()
	{
		String print = "Bank " + bankID + " (" + customers.size() 
				+ " customers)\n";
		int custCount = 1;
		for (Customer c: customers.keySet())
		{
			CashCard card = c.getCashCard();
			print = print + "Customer " + custCount + ": " + c.getName() 
					+ card.toString() + ", password: " + cashCards.get(card) + "\n";
			custCount++;
		}
		return print;
	}
}
