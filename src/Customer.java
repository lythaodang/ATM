/**
   COPYRIGHT (C) 2014 Ly Dang. All Rights Reserved.
   CS 151 Kim Assignment #1
   @author Ly Dang
   @version 1.01 2014/9/7
*/

/**
 * Representation of a customer.
 */
public class Customer
{
	private CashCard card;
	private String name;
	
	/**
	 * Constructs the customer with a name.
	 * @param name name of customer
	 */
	public Customer(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the customer's name.
	 * @return customer's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the customer's cash card.
	 * @param card card to set
	 */
	public void setCashCard(CashCard card)
	{
		this.card = card;
	}
	
	/**
	 * Gets the customer's cash card.
	 * @return customer's card
	 */
	public CashCard getCashCard()
	{
		return card;
	}
}
