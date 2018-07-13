package com.cg.mypaymentapp.repo;

import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data = new HashMap<String, Customer>() ;
	
	public Map<String, Customer> getData() {
		return data;
	}


	Customer cust = new Customer();
	
	public void setData(Map<String, Customer> data) 
	{
		this.data = data;
	}

	public WalletRepoImpl() {
		super();
		this.data = data;
	}
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}
	

	@Override
	public boolean save(Customer customer) throws InvalidInputException {
		String mobileNo = customer.getMobileNo();
		data.put(mobileNo,  customer);
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) throws InvalidInputException {
		cust = data.get(mobileNo);
		if(cust == null)
			throw new InvalidInputException("Account not found...!!!");
		else
			return cust;
	}
}
