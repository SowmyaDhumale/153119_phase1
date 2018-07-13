package com.cg.mypaymentapp.repo;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InvalidInputException;

public interface WalletRepo {

	public boolean save(Customer customer) throws InvalidInputException;

	public Customer findOne(String mobileNo) throws InvalidInputException;

	
}
