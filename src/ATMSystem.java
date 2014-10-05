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
 * Tests the ATM network to see if it is working properly.
 */
public class ATMSystem
{
	// Static scanner so all classes can access it.
	static Scanner in;
	
	public static void main(String[] args)
	{
		in = new Scanner(System.in);
		// Initialize banks
		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");

		// Initialize and link bank computers to their respective banks
		BankComputer computerA = new BankComputer(1);
		BankComputer computerB = new BankComputer(2);
		bankA.setComputer(computerA);
		bankB.setComputer(computerB);

		// Initialize and link two ATMs to first computer
		ATM atmA_1 = new ATM("A 1", 50, 25, 25, 100);
		ATM atmA_2 = new ATM("A 2", 50, 25, 25, 100);
		atmA_1.linkComputer(computerA);
		atmA_2.linkComputer(computerA);
		atmA_2.setCashAvail(0); // for testing purposes
		
		// Initialize and link two ATMs to second computer
		ATM atmB_1 = new ATM("B 1", 100, 50, 50, 100);
		ATM atmB_2 = new ATM("B 2", 50, 25, 25, 100);
		atmB_1.linkComputer(computerB);
		atmB_2.linkComputer(computerB);
		atmB_2.setCashAvail(0); // for testing purposes

		// Initialize an array list and add ATMs to it
		ArrayList<ATM> atms = new ArrayList<ATM>();
		atms.add(atmA_1);
		atms.add(atmA_2);
		atms.add(atmB_1);
		atms.add(atmB_2);

		// Initialize customers
		Customer customerA_1 = new Customer("James");
		Customer customerA_2 = new Customer("Ly");
		Customer customerA_3 = new Customer("Kim");
		Customer customerB_1 = new Customer("Tim");
		Customer customerB_2 = new Customer("Jake");
		Customer customerB_3 = new Customer("Rich");
	
		// Initialize cash cards for bank A
		CashCard cashCardA_1 = bankA.issueCard
				(new GregorianCalendar(2014, 9, 13), "passwordA_1");
		CashCard cashCardA_2 = bankA.issueCard
				(new GregorianCalendar(), "passwordA_2");
		CashCard cashCardA_3 = bankA.issueCard
				(new GregorianCalendar(2013, 3, 8),"passwordA_3");

		// Initialize cash cards for bank B
		CashCard cashCardB_1 = bankB.issueCard
				(new GregorianCalendar(2016, 9, 13), "passwordB_1");
		CashCard cashCardB_2 = bankB.issueCard
				(new GregorianCalendar(2017, 9, 13), "passwordB_2");
		CashCard cashCardB_3 = bankB.issueCard
				(new GregorianCalendar(2018, 9, 13), "passwordB_3");

		// Initialize an array list and add cash cards to it
		ArrayList<CashCard> cashCards = new ArrayList<CashCard>();
		cashCards.add(cashCardA_1);
		cashCards.add(cashCardA_2);
		cashCards.add(cashCardA_3);
		cashCards.add(cashCardB_1);
		cashCards.add(cashCardB_2);
		cashCards.add(cashCardB_3);

		// Initialize accounts for bank A
		Account accountA_1_1 = new Account("Savings", 111);
		Account accountA_1_2 = new Account("Checking", 112);
		Account accountA_2 = new Account("Savings", 113);
		Account accountA_3 = new Account("Checking", 114);

		// Initialize accounts for bank B
		Account accountB_1_1 = new Account("Savings", 221);
		Account accountB_1_2 = new Account("Checking", 222);
		Account accountB_2 = new Account("Savings", 223);
		Account accountB_3 = new Account("Checking", 224);

		// Link customers to cash cards
		customerA_1.setCashCard(cashCardA_1);
		customerA_2.setCashCard(cashCardA_2);
		customerA_3.setCashCard(cashCardA_3);
		customerB_1.setCashCard(cashCardB_1);
		customerB_2.setCashCard(cashCardB_2);
		customerB_3.setCashCard(cashCardB_3);

		// Link accounts to cash cards
		cashCardA_1.addAccount(accountA_1_1);
		cashCardA_1.addAccount(accountA_1_2);
		cashCardA_2.addAccount(accountA_2);
		cashCardA_3.addAccount(accountA_3);
		cashCardB_1.addAccount(accountB_1_1);
		cashCardB_1.addAccount(accountB_1_2);
		cashCardB_2.addAccount(accountB_2);
		cashCardB_3.addAccount(accountB_3);

		// Add customers and accounts to their respective banks
		bankA.addCustomer(customerA_1, accountA_1_1, accountA_1_2);
		bankA.addCustomer(customerA_2, accountA_2, null);
		bankA.addCustomer(customerA_3, accountA_3, null);
		bankB.addCustomer(customerB_1, accountB_1_1, accountB_1_2);
		bankB.addCustomer(customerB_2, accountB_2, null);
		bankB.addCustomer(customerB_3, accountB_3, null);

		// Print state of banks
		System.out.println("States of the two banks "
				+ "(all accounts are preloaded with $40)");
		System.out.println(bankA.toString() + "\n" + bankB.toString());

		// Print state of bank computers
		System.out.println("States of bank computers (1 per bank)");
		System.out.println(computerA.toString() + "\n" + computerB.toString());

		// Print state of ATMs
		System.out.println("\nStates of four ATMs (2 per bank computer)");
		System.out.println(atmA_1.toString() + "\n\n" + atmA_2.toString()
				+ "\n\n" + atmB_1.toString() + "\n\n" + atmB_2.toString() + "\n");

		System.out.println("This simulation ends when all ATMs are out of order.");
		// Simulation loop will exit when all four ATMs are out of order
		while (atmA_1.usable() || atmA_2.usable() || atmB_1.usable()
				|| atmB_2.usable())
		{
			// Prompt user to select a bank
			System.out.println("Choose a bank: A or B");
			String bankID = "";
			bankID = in.nextLine();
			
			// Prompt user to select an ATM
			System.out.println("Choose an ATM: 1 or 2");
			String atmNum = "";
			atmNum = in.nextLine();
			atmNum = bankID + " " + atmNum;
			
			// Search for the ATM the user has selected
			ATM chosenATM = null;
			for (int i = 0; i < atms.size() && chosenATM == null; i++)
			{
				if (atmNum.equals(atms.get(i).getATMNum()))
					chosenATM = atms.get(i);
			}

			// Prompt user to enter a card number
			System.out.println("Enter your card:");
			String cardNum = "";
			cardNum = in.nextLine();
			
			// Search for the cash card the user has selected
			boolean cardFound = false;
			for (int i = 0; i < cashCards.size() && !cardFound; i++)
			{
				CashCard c = cashCards.get(i);
				if (cardNum.equals(c.getBankID() + " " + c.getCardNum()))
				{
					chosenATM.setInsertedCard(c);
					cardFound = true;
				}
			}
		}
		
		System.out.println("\nThese are the states after all the transactions.");
		// Print state of banks
		System.out.println("States of the two banks)");
		System.out.println(bankA.toString() + "\n" + bankB.toString());

		// Print state of bank computers
		System.out.println("States of bank computers (1 per bank)");
		System.out.println(computerA.toString() + "\n" + computerB.toString());

		// Print state of ATMs
		System.out.println("\nStates of four ATMs (2 per bank computer)");
		System.out.println(atmA_1.toString() + "\n\n" + atmA_2.toString()
				+ "\n\n" + atmB_1.toString() + "\n\n" + atmB_2.toString() + "\n");
		
		// Close scanner
		in.close();
	}
}