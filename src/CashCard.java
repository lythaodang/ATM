import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved.
 * CS 151 Kim Assignment #1
 * @author Ly Dang
 * @version 1.01 2014/9/7
 */

/**
 * Representation of a customer's cash card.
 */
public class CashCard
{
	private String bankID;
	private int cardNum;
	private GregorianCalendar expireDate;
	private ArrayList<Account> accounts;

	/**
	 * Constructs the cash card with a bank ID, a card number, and an
	 * expiration date.
	 * @param bankID the ID of the bank the cash card belongs to
	 * @param cardNum the number of the card
	 * @param expireDate the date on which the card expires
	 */
	public CashCard(String bankID, int cardNum, GregorianCalendar expireDate)
	{
		this.bankID = bankID;
		this.cardNum = cardNum;
		this.expireDate = expireDate;
		accounts = new ArrayList<Account>();
	}

	/**
	 * Gets the an array list of accounts this card can access.
	 * @return array list of accounts this card can access
	 */
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}

	/**
	 * Gets this card's expiration date.
	 * @return the date in which this card expires
	 */
	public GregorianCalendar getExpireDate()
	{
		return expireDate;
	}

	/**
	 * Gets the ID of the bank this card is associated with.
	 * @return ID of the bank this card belongs to
	 */
	public String getBankID()
	{
		return bankID;
	}

	/**
	 * Gets this card's number.
	 * @return card number
	 */
	public int getCardNum()
	{
		return cardNum;
	}

	/**
	 * Adds an account to the array list of accounts that this card can
	 * access.
	 * @param account account to add
	 */
	public void addAccount(Account account)
	{
		accounts.add(account);
	}

	/**
	 * Returns the string representation of the cash card's state.
	 * @return state of cash card
	 */
	public String toString()
	{
		String print = "\nCash Card (Bank ID: " + bankID + ", Card Number: #"
				+ cardNum + ") accesses ";
		
		// Accounts the card accesses
		for (int i = 0; i < accounts.size(); i++)
		{
			print = print + accounts.get(i).getType() + " Account (#"
					+ accounts.get(i).getAccountNum() + ")";
			if (i != accounts.size() - 1)
				print = print + " and ";
			else
				print = print + ", ";
		}

		// Expiration date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return print + " expires on: " + sdf.format(expireDate.getTime());
	}
}