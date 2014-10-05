import java.util.ArrayList;
import java.util.HashMap;

/**
 * COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved. 
 * CS 151 Kim Assignment #1
 * @author Ly Dang
 * @version 1.01 2014/9/7
 */

/**
 * Interfaces the ATM network and the bank's account management station.
 */
public class BankComputer
{
	private int compNum;
	private ArrayList<ATM> atms;
	private Bank bank;
	private ArrayList<CashCard> logOfFailedAttempts;
	private HashMap<CashCard, Integer> logOfDispenses;

	/**
	 * Constructs the bank computer with a number.
	 * @param compNum the number of the computer
	 */
	public BankComputer(int compNum)
	{
		this.compNum = compNum;
		atms = new ArrayList<ATM>();
		bank = null;
		logOfFailedAttempts = new ArrayList<CashCard>();
		logOfDispenses = new HashMap<CashCard, Integer>();
	}
	
	/**
	 * Links this computer with an ATM.
	 * @param atm the ATM to link
	 */
	public void linkATM(ATM atm)
	{
		atms.add(atm);
	}

	/**
	 * Unlinks this computer with an ATM.
	 * @param atm the ATM to unlink
	 */
	public void unlinkATM(ATM atm)
	{
		atms.remove(atm);
	}
	
	/**
	 * Gets the bank this computer is linked to.
	 * @return the bank this computer is linked to
	 */
	public Bank getBank()
	{
		return bank;
	}
	
	/**
	 * Sets the bank of this computer.
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank)
	{
		this.bank = bank;
	}
	
	/**
	 * Returns a string representation of this computer's state.
	 * @return state of computer
	 */
	public String toString()
	{
		return "Bank Computer #" + compNum + " - Bank ID: " + bank.getBankID(); 
	}
	
	/**
	 * Checks whether a cash card belongs to this computer's bank.
	 * @param cardBankID the ID of the bank that the cash card belongs to
	 * @return true if the cash card belongs to this computer's bank, else false
	 */
	public boolean verifyID(String cardBankID)
	{
		if (bank.getBankID().equals(cardBankID))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the user has entered the correct password.
	 * @param inputFromUser the user's inputed password
	 * @param insertedCard the card the user entered
	 * @return true if the password is correct, else false
	 */
	public boolean verifyPassword(String inputFromUser, CashCard insertedCard)
	{
		if (bank.verifyPassword(inputFromUser, insertedCard))
			return true;
		else
			return false;
	}
	
	/**
	 * Adds the given card to the log of failed attempts
	 * @param insertedCard card to add
	 */
	public void addToLog(CashCard insertedCard)
	{
		logOfFailedAttempts.add(insertedCard);
	}
	
	/**
	 * Counts how many times a user has failed to authorize the given card.
	 * @param insertedCard the card to check
	 * @return 0 to 3 depending on how many times the user has failed to 
	 * authorize the card
	 */
	public int countFailedAttempts(CashCard insertedCard)
	{
		int count = 0;
		for (int i = 0; i < logOfFailedAttempts.size(); i++)
		{
			if (insertedCard.equals(logOfFailedAttempts.get(i)))
				count++;
		}
		return count;
	}
	
	/**
	 * Verifies if the user has entered an amount available in his/her account.
	 * @param requestedAmount amount the user is requesting
	 * @param chosenAccount the account from which the user wants to withdraw
	 * @return true if the amount is valid, else false
	 */
	public boolean verifyAmount(int requestedAmount, Account chosenAccount)
	{
		if (requestedAmount <= chosenAccount.getBalance())
		{
			bank.reduceBalance(requestedAmount, chosenAccount);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Logs the daily dispenses in a HashMap.
	 * @param insertedCard the card from which the user made the transaction
	 * @param amount the amount the user is withdrawing
	 */
	public void logDispense(CashCard insertedCard, int amount)
	{
		if (logOfDispenses.containsKey(insertedCard))
			logOfDispenses.put(insertedCard, logOfDispenses.get(insertedCard) + amount);
		// First time the cash card has withdrawn today
		else
			logOfDispenses.put(insertedCard, amount);
	}
	
	public HashMap<CashCard, Integer> getLogOfDispenses()
	{
		return logOfDispenses;
	}
}
