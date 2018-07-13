package com.cg.mypaymentapp.service;

import java.math.BigDecimal;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;



public class WalletServiceImpl implements WalletService{

	private WalletRepo repo;

	public WalletServiceImpl() {
		repo = new WalletRepoImpl();
	}
	
    Customer customer=new Customer();
    Wallet wallet=new Wallet();
    
    
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws InvalidInputException {
		if(isValid(mobileNo)) {
			Wallet wallet = new Wallet();
			Customer customer = new Customer();

			wallet.setBalance(amount);
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);

			repo.save(customer);

			return customer;
		} 
		else throw new InvalidInputException(); 
		
					

	}

	public Customer showBalance(String mobileNo) throws InvalidInputException {
		Customer customer=repo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("your mobile no is invalid ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		if(isValid(sourceMobileNo) == false || isValid(targetMobileNo) == false) throw new InvalidInputException();
		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException 
	{
		if(isValid(mobileNo)) 
		{
			Customer customer = repo.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().add(amount));

			
			repo.save(customer);
			
				
			
		}
		return customer;
	}

	public boolean isValid(String mobileNo) {
		if(mobileNo.matches("[1-9][0-9]{9}")) 
		{
			return true;
		}		
		else 
			return false;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		if(isValid(mobileNo)) 
		{
			Customer customer = repo.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().subtract(amount));

			if(amount.compareTo(wallet.getBalance()) > 0) 
				throw new InsufficientBalanceException("Amount is not sufficient in your account");

			

			//if(repo.save(customer)) {
			repo.save(customer);
				return customer;
			}
		return customer;
	}
}
		

