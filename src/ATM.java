import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved. 
 * CS 151 Kim Assignment #1
 * @author Ly Dang
 * @version 1.01 2014/9/7
 */

/**
 * Stimulates an automated teller machine. It gathers transaction
 * information from the user, validates and processes the information through
 * the bank computer, and dispenses cash to the user.
 */
public class ATM
{
	private String atmNum;
	private int maxPerDay;
	private int maxPerTransaction;
	private int minCashPermitted;
	private int cashStartOfDay;
	private int cashAvail;
	private BankComputer computer;
	private CashCard insertedCard;
	private ArrayList<CashCard> logOfInsertedCards;
	private Scanner in;

	/**
	 * Constructs an ATM with the parameters listed in REQUIREMENT #1.
	 * @param maxPerDay maximum cash a user can withdraw per day
	 * @param maxPerTransaction maximum cash a user can withdraw per transaction
	 * @param cashPermitted minimum cash that will permit a transaction
	 * @param cashStartOfDay total fund at start of day
	 */
	public ATM(String atmNum, int maxPerDay, int maxPerTransaction, int minCashPermitted,
			int cashStartOfDay)
	{
		this.atmNum = atmNum;
		this.maxPerDay = maxPerDay;
		this.maxPerTransaction = maxPerTransaction;
		this.minCashPermitted = minCashPermitted;
		this.cashStartOfDay = cashStartOfDay;
		cashAvail = cashStartOfDay;
		computer = null;
		insertedCard = null;
		logOfInsertedCards = new ArrayList<CashCard>();
		in = ATMSystem.in;
	}

	/**
	 * Links this ATM and the given computer to each other.
	 * @param computer the computer with which the ATM links
	 */
	public void linkComputer(BankComputer computer)
	{
		this.computer = computer;
		computer.linkATM(this);
	}

	/**
	 * Unlinks the given computer and this ATM from each other.
	 * @param computer the computer from which the ATM unlinks
	 */
	public void unlinkComputer(BankComputer computer)
	{
		this.computer = null;
		computer.unlinkATM(this);
	}
	
	/**
	 * Gets the ATM's string representation in this simulation.
	 * @return bank ID followed by the ATM's number
	 */
	public String getATMNum()
	{
		return atmNum;
	}
	
	/**
	 * Sets the current amount of cash inside of the ATM.
	 * @param amount amount to set
	 */
	public void setCashAvail(int amount)
	{
		cashAvail = amount;
	}
	
	/**
	 * Returns a string representation of the ATM's state.
	 * @return the ATM's state
	 */
	public String toString()
	{
		return "ATM " + atmNum + " from bank " + computer.getBank().getBankID()
				+ "\nThe maximum amount of cash a card can withdraw per day: " 
				+ maxPerDay
				+ "\nThe maximum amount of cash a card can withdraw per transaction: " 
				+ maxPerTransaction 
				+ "\nThe minimum cash in the ATM to permit a transaction: "  
				+ minCashPermitted
				+ "\nThe total fund in the ATM at start of day: " 
				+ cashStartOfDay
				+ "\nThe amount of cash available: "
				+ cashAvail
				+ "\nIs ATM out of order?: "
				+ usable();
	}
	
	/**
	 * Checks to see if this ATM is usable.
	 * @return true if this ATM has enough cash and does not have a card already
	 * inserted, else false
	 */
	public boolean usable()
	{
		return (cashAvail >= minCashPermitted && insertedCard == null);
	}
	
	/**
	 * Checks if the ATM has enough cash to permit a transaction.
	 * @param insertedCard the card the user inserts
	 */
	public void setInsertedCard(CashCard insertedCard)
	{
		if (usable())
		{
			this.insertedCard = insertedCard;
			checkExpireDate();
		}
		else
		{
			System.out.println("This ATM is out of order. "
					+ "Please choose a different one.");
		}
	}

	/**
	 * "Remove" card from ATM.
	 */
	public void removeCard()
	{
		insertedCard = null;
	}
	
	/**
	 * Checks the expiration date of the inserted cash card. 
	 */
	public void checkExpireDate()
	{
		if (insertedCard.getExpireDate().after(new GregorianCalendar()))
		{
			logOfInsertedCards.add(insertedCard);
			authorizeCard();
		}
		else
		{
			System.out.println("This card has expired. Card has been ejected.");
			insertedCard = null;
		}
	}

	/**
	 * Verifies bank ID of cash card with bank computer. If the card is supported
	 * by this ATM, then the ATM initializes password verification. Otherwise,
	 * the card is returned.
	 */
	public void authorizeCard()
	{
		if (computer.verifyID(insertedCard.getBankID()))
		{
			System.out.println("Card accepted. Card logged.");
			verifyPassword();
		}
		else
		{
			System.out.println("Card not supported by this ATM. "
					+ "Card has been ejected.");
			insertedCard = null;
		}
	}

	/**
	 * Verifies the password entered with the bank computer. If the password is
	 * correct, then the ATM initiates transaction dialog. Otherwise, the card is
	 * ejected. If the password has been entered incorrectly more than three
	 * times, the ATM will keep the card.
	 */
	private void verifyPassword()
	{
		System.out.println("Please enter your password:");
		String input = "";
		input = in.nextLine();
		if (computer.verifyPassword(input, insertedCard))
		{
			System.out.println("Card authorized.");
			transactionDialog();
		}
		/* Adds the inserted card to a log of failed attempts inside the bank
		 * computer
		*/
		else
		{
			computer.addToLog(insertedCard);
			/* If the user has attempted authorization with this card three times,
			 *	the card will be kept by the ATM
			*/
			if (computer.countFailedAttempts(insertedCard) == 3)
				System.out.println("Wrong password."
						+ " You have attempted to authorize this account 3 times."
						+ " Your card has been confiscated."
						+ " Please call the bank.");
			else
			{
				System.out.println("Wrong password. Card has been ejected. Attempt logged.");
				insertedCard = null;
			}
		}
	}

	/**
	 * Prompts user for the account from which they would like to withdraw from.
	 * Initiates withdrawal after choice.
	 */
	private void transactionDialog()
	{
		System.out.println("Please choose from one of the accounts below: "
				+ "Note: Enter the number corresponding to the account.");
		Account chosenAccount = displayAccounts();
		if (chosenAccount == null)
			removeCard();
		else
			withdraw(chosenAccount);
	}
	
	/**
	 * Displays the accounts that the inserted cash card can access for user
	 * selection.
	 * @return the account the user would like to withdraw from
	 */
	public Account displayAccounts()
	{
		int count = 1;
		ArrayList<Account> accounts = insertedCard.getAccounts();
		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.println("(" + count + ") " + accounts.get(i).getType()
					+ " (#" + accounts.get(i).getAccountNum() + ") ");
			count++;
		}
		System.out.println("(" + count + ") Cancel");
		int input = Integer.parseInt(in.nextLine());
		if (input == count)
		{
			System.out.println("Your transaction has been canceled."
					+ "\nCard has been ejected.");
			return null;
		}
		else
			return accounts.get(input - 1);
	}

	/**
	 * Prompts user for the amount he/she would like to withdraw. It prompts
	 * until cash is withdrawn.
	 * @param chosenAccount the account the user chooses to withdraw from
	 */
	private void withdraw(Account chosenAccount)
	{
		System.out.println("Please enter an amount you would like to withdraw: "
				+ "(You can only enter whole number amounts.)");
		int withdrawalAmount = Integer.parseInt(in.nextLine());
		// Check if the amount is within the ATM's pre-defined limits
		if (withdrawalAmount <= maxPerTransaction && validPerDay())
		{
			/* Check if the account the user would like to withdraw from has
			 * enough cash. If the account has enough cash, then initiate
			 * withdrawal.
			*/
			if (computer.verifyAmount(withdrawalAmount, chosenAccount))
			{
				System.out.println("Amount approved. Cash dispensed."
						+ " Card ejected.");
				System.out.println("You have $" + chosenAccount.getBalance() 
						+ " remaining in your account.");
				dispenseCash(withdrawalAmount);
				removeCard();
			}
			// The amount exceeds the cash available in the account
			else
			{
				System.out.println("Amount denied. Amount greater than balance."
						+ " Please try again.");
				withdraw(chosenAccount);
			}
		}
		// The amount exceeds the ATM's pre-defined limits
		else
		{
			System.out.println("Amount denied.\nAmount greater than ATM limit."
					+ " Please try again.");
			withdraw(chosenAccount);
		}
	}

	/**
	 * Verifies if the cash card can still withdraw money today.
	 * @return true if the card still is within the withdrawal limit for the 
	 * day, else return false
	 */
	private boolean validPerDay()
	{
		if (computer.getLogOfDispenses().containsKey(insertedCard))
		{
			if (computer.getLogOfDispenses().get(insertedCard) <= maxPerDay)
				return true;
			else
				return false;
		}
		else
			return true;
	}
	
	/**
	 * The ATM dispenses cash and logs the dispense.
	 * @param amount the amount the user withdrew
	 */
	private void dispenseCash(int amount)
	{
		// Decreases the ATM's available cash
		// No other transactions have occurred
		if (cashAvail == cashStartOfDay)
			cashAvail = cashStartOfDay - amount;
		// Other transactions have occurred 
		else
			cashAvail = cashAvail - amount;
		
		// Logs the card
		// Increase the amount the cash card has withdrawn today
		computer.logDispense(insertedCard, amount);
	}
}
