Ly Dang
CS 151 Assignment #1
Read me

First I will explain the individual inputs that I used in input.txt. Then I will explain what my classes do to fulfil each requirement. More specific method explanations are documented in the .java files with javadoc. NOTE: Some requirements cannot be shown through input.

Here are the states of the objects I initialized. Scroll down to line 62 for interaction. 
States of the two banks (all accounts are preloaded with $40)
Bank A (3 customers)
Customer 1: Ly
Cash Card (Bank ID: A, Card Number: #2) accesses Savings Account (#113),  expires on: 09/20/2014, password: passwordA_2
Customer 2: Kim
Cash Card (Bank ID: A, Card Number: #3) accesses Checking Account (#114),  expires on: 04/08/2016, password: passwordA_3
Customer 3: James
Cash Card (Bank ID: A, Card Number: #1) accesses Savings Account (#111) and Checking Account (#112),  expires on: 10/13/2014, password: passwordA_1

Bank B (3 customers)
Customer 1: Rich
Cash Card (Bank ID: B, Card Number: #3) accesses Checking Account (#224),  expires on: 10/13/2018, password: passwordB_3
Customer 2: Jake
Cash Card (Bank ID: B, Card Number: #2) accesses Savings Account (#223),  expires on: 10/13/2017, password: passwordB_2
Customer 3: Tim
Cash Card (Bank ID: B, Card Number: #1) accesses Savings Account (#221) and Checking Account (#222),  expires on: 10/13/2016, password: passwordB_1

States of bank computers (1 per bank)
Bank Computer #1 - Bank ID: A
Bank Computer #2 - Bank ID: B

States of four ATMs (2 per bank computer)
REQUIREMENT #1: parameters of ATMs
ATM A 1 from bank A
The maximum amount of cash a card can withdraw per day: 50
The maximum amount of cash a card can withdraw per transaction: 25
The minimum cash in the ATM to permit a transaction: 25
The total fund in the ATM at start of day: 100
The amount of cash available: 100
Is ATM out of order?: true

ATM A 2 from bank A
The maximum amount of cash a card can withdraw per day: 50
The maximum amount of cash a card can withdraw per transaction: 25
The minimum cash in the ATM to permit a transaction: 25
The total fund in the ATM at start of day: 100
The amount of cash available: 0
Is ATM out of order?: false

ATM B 1 from bank B
The maximum amount of cash a card can withdraw per day: 100
The maximum amount of cash a card can withdraw per transaction: 50
The minimum cash in the ATM to permit a transaction: 50
The total fund in the ATM at start of day: 100
The amount of cash available: 100
Is ATM out of order?: true

ATM B 2 from bank B
The maximum amount of cash a card can withdraw per day: 50
The maximum amount of cash a card can withdraw per transaction: 25
The minimum cash in the ATM to permit a transaction: 25
The total fund in the ATM at start of day: 100
The amount of cash available: 0
Is ATM out of order?: false

Below is the interaction part of output.txt combined with input.txt (includes explanations)
This simulation ends when all ATMs are out of order.
Choose a bank: A or B <--- must be capital letters
> A
Choose an ATM: 1 or 2 <--- must be 1 or 2
> 2
Enter your card: <--- choose a card from the above states (first enter bankID then a space then card number)
> A 1
This ATM is out of order. Please choose a different one. <--- I chose an ATM that is out of order to test REQUIREMENT #2
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> A 2 
This card has expired. Card has been ejected. <--- I chose an expired card to test REQUIREMENT #3
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> B 1
Card not supported by this ATM. Card has been ejected. <--- REQUIREMENT #5
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> A 1
Card accepted. Card logged. <--- REQUIREMENT #4
Please enter your password:
> passwordA_1
Card authorized.
Please choose from one of the accounts below: Note: Enter the number corresponding to the account. <--- REQUIREMENT #6
(1) Savings (#111) 
(2) Checking (#112) 
(3) Cancel
> 1
Please enter an amount you would like to withdraw: (You can only enter whole number amounts.)
> 30
Amount denied.
Amount greater than ATM limit. Please try again.
Please enter an amount you would like to withdraw: (You can only enter whole number amounts.)
> 25
Amount approved. Cash dispensed. Card ejected.
You have $15.0 remaining in your account.
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> A 1
Card accepted. Card logged.
Please enter your password:
> wrong
Wrong password. Card has been ejected. Attempt logged. <--- REQUIREMENT #5
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> A 1
Card accepted. Card logged.
Please enter your password:
> wrongx2
Wrong password. Card has been ejected. Attempt logged.
Choose a bank: A or B
> A
Choose an ATM: 1 or 2
> 1
Enter your card:
> A 1
Card accepted. Card logged.
Please enter your password:
> wrongx3
Wrong password. You have attempted to authorize this account 3 times. Your card has been confiscated. Please call the bank. <--- REQUIREMENT #7
Choose a bank: A or B
> B
Choose an ATM: 1 or 2
> 1
Enter your card:
> B 1
Card accepted. Card logged.
Please enter your password:
> passwordB_1
Card authorized.
Please choose from one of the accounts below: Note: Enter the number corresponding to the account.
(1) Savings (#221) 
(2) Checking (#222) 
(3) Cancel
> 1
Please enter an amount you would like to withdraw: (You can only enter whole number amounts.)
> 60
Amount denied.
Amount greater than ATM limit. Please try again. <--- REQUIREMENT #8
Please enter an amount you would like to withdraw: (You can only enter whole number amounts.)
> 40
Amount approved. Cash dispensed. Card ejected. <--- REQUIREMENT #9
You have $0.0 remaining in your account.
Choose a bank: A or B
> B
Choose an ATM: 1 or 2
> 1
Enter your card:
> B 2
Card accepted. Card logged.
Please enter your password:
> passwordB_2
Card authorized.
Please choose from one of the accounts below: Note: Enter the number corresponding to the account.
(1) Savings (#223) 
(2) Cancel
> 1
Please enter an amount you would like to withdraw: (You can only enter whole number amounts.)
> 40
Amount approved. Cash dispensed. Card ejected.
You have $0.0 remaining in your account.

States will print out at the end of these transactions.

CLASS DESCRIPTIONS
My program has seven classes: 
1) ATMSystem 
	a) main() in this class
	b) initializes all objects of the other classes 
	c) prints out the objects' states using their toString()
	d) while loop is the first step in user interaction
		i) prompts user for the ID of the bank the user would like to withdraw from (A or B)
		ii) prompts user for the ATM the user would like to use (1 or 2)
		iii) prompts user for the card the user would like to insert (A 1, A 2, A 3, B 1, B 2, or B 3)
			1) what the user enters for the card is the bankID + the card's number
		iv) sets the chosen ATM's insertedCard to the user's entered card (initializes ATM's interaction with the user)
2) ATM
	a) ATMs are initialized with the parameters given in REQUIREMENT #1 (atmNum is an additional parameter that I added)
	b) ATMSystem passes a card to the ATM...
		i) ATM will reject (these are the two things tested in usable())
			1) if ATM does not have enough cash (REQUIREMENT #2)
			2) if ATM already has a card inserted
	c) ATM checks if the inserted card is valid or expired (REQUIREMENT #3) 
	d) ATM logs the card once it checks that the card is valid (REQUIREMENT #4)
	e) ATM checks if the card is supported via the computer (REQUIREMENT #5)
	f) ATM prompts user for password and checks with the bank computer to see if it is correct (REQUIREMENT #5)
	g) if the user enters a password incorrectly a third time using any ATM (this is checked through a log of attempts), the card will be confiscated and the ATM will become unusable (REQUIREMENT #7)
	h) ATM will initialize transaction dialog if authorization is successful (REQUIREMENT #7)
	i) ATM will prompt user to choose an account to which the user's cash card accesses
	ATM prompts user for a withdrawal amount...
		i) ATM will reject if amount > ATM limit per day or per transaction (REQUIREMENT #8)
		ii) ATM will reject if the computer determines that amount > account's balance (REQUIREMENT #9)
		iii) ATM will dispense cash and log dispenses with the bank computer (REQUIREMENT #9)
		iv) the amount dispensed is reported to the bank (REQUIREMENT #9)
3) BankComputer
	a) checks if a cash card is supported by checking that it matches the computer's bank's ID (REQUIREMENT #10)
	b) checks with the bank if an entered password is correct
		i) sends true if the password is wrong (REQUIREMENT #11)
		ii) sends false if the password is correct (REQUIREMENT #12)
	c) checks if the user has entered an amount that is <= the account's balance (REQUIREMENT #13)
		i) returns false to the ATM if amount requested is to high (REQUIREMENT #14)
		ii) returns true to the ATM if the amount is valid (REQUIREMENT #15)
	d) notifies the bank to reduce an account's balance (REQUIREMENT #15)
	e) responsible for failed attempts log and log of dispenses
4) Bank
	a) reduces an account's balance
	b) manages accounts and customers
	c) manages cash cards and passwords
	d) returns true or false to computer depending on whether a user's input matches the password of the card
	e) issues cash cards
5) Customer
	a) Customer holds a cash card
6) Account
	a) can be either savings or checking (type)
	b) has a balance
7) CashCard 
	a) has an expiration date, a bank ID, and accounts to access