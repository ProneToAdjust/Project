package com.projectdemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable{
	
	/**
	 * The name of the account.
	 */
	private String name;
	
	/**
	 * The account ID number.
	 */
	private String uuid;
	
	/**
	 * The User object that owns this account.
	 */
	private User holder;
	
	/**
	 * The list of transactions for this account.
	 */
	private ArrayList<Transaction> transactions;
	
	/**
	 * Create new Account instance
	 * @param name		the name of the account
	 * @param holder	the User object that holds this account
	 * @param theBank	the bank that issues the account
	 */
	public Account(String name, User holder, Bank theBank) {
		
		// set the account name and holder
		this.name = name;
		this.holder = holder;
		
		// get next account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		// init transactions
		this.transactions = new ArrayList<Transaction>();
		
	}
	
	/**
	 * Get the account number.
	 * @return	the uuid
	 */
	protected String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Add a new transaction in this account.
	 * @param amount	the amount transacted
	 */
	protected void addTransaction(double amount) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);
		
	}
	
	/**
	 * Add a new transaction in this account.
	 * @param amount	the amount transacted
	 * @param memo		the transaction memo
	 */
	protected void addTransaction(double amount, String memo) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
		
	}
	
	/**
	 * Get the balance of this account by adding the amounts of the 
	 * transactions.
	 * @return	the balance value
	 */
	protected double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}
	
	/**
	 * Get summary line for account
	 * @return	the summary line
	 */
	protected String getSummaryLine() {
		
		// get the account's balance
		double balance = this.getBalance();
		
		// format summary line depending on whether balance is negative
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, 
					this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, 
					this.name);
		}
		
	}
	
	/**
	 * Print transaction history for account
	 */
	protected ArrayList<Transaction> getTransHistory() {
		return transactions;
	}
}
