package com.cg.mypaymentapp.pl;



import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;



public class Client 
{
	WalletService service;

	Client() {
		service = new WalletServiceImpl();
	}


	public void menu() {
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Fund Transfer");
		System.out.println("0) Exit Application");

		Scanner console = new Scanner(System.in);

		System.out.println("Enter your choice");
		int choice = console.nextInt();

		switch(choice) {
		case 1:
			Customer customer = new Customer();
			Wallet wallet = new Wallet();

			System.out.print("Enter name: ");
			String name = console.next();

			System.out.print("Enter mobileNumber: ");
			String mobileNumber = console.next();

			System.out.print("Enter Amount: ");
			BigDecimal amount = console.nextBigDecimal();

			try 
			{
				customer = service.createAccount(name, mobileNumber, amount);
				System.out.println("Your account is successfully created...");
				System.out.println();
			} 
			catch (InvalidInputException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter mobile number");
			mobileNumber = console.next();

			try {
				customer = service.showBalance(mobileNumber);
				System.out.print("The balance in account " + customer.getName());
				System.out.println(" is " + customer.getWallet().getBalance());
			} catch (InvalidInputException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			break;

		case 3:
			System.out.println("Enter mobile number");
			mobileNumber = console.next();

			System.out.println("Enter amount to be deposited");
			amount = console.nextBigDecimal();

			try 
			{
				customer = service.depositAmount(mobileNumber, amount);
				System.out.println("Successfully deposited");
				System.out.println("Account balance is: " + customer.getWallet().getBalance());
				System.out.println();
			} 
			catch (InvalidInputException e2) 
			{
				e2.printStackTrace();
			}



			break;

		case 4:
			System.out.println("Enter mobile number");
			mobileNumber = console.next();

			System.out.println("Enter amount to be withdrawn");
			amount = console.nextBigDecimal();

			try 
			{
				customer = service.withdrawAmount(mobileNumber, amount);
				System.out.println("Successfully withdrawn");
				System.out.println("Account balance is: " + customer.getWallet().getBalance());	System.out.println();
			}
			catch (InvalidInputException e1) 
			{
				e1.printStackTrace();
			} catch (InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 5:
			System.out.print("Enter source mobile number: ");
			String sourceMobile = console.next();

			System.out.print("Enter target mobile number: ");
			String targetMobile = console.next();

			System.out.println("Enter amount to be transferred");
			amount = console.nextBigDecimal();

			try 
			{
				customer = service.fundTransfer(sourceMobile, targetMobile, amount);
				System.out.println("Amount has successfully transferred from account " + customer.getName());
				System.out.println("And now your balance is " + customer.getWallet().getBalance());
				System.out.println();

			} 
			catch (InvalidInputException e) {
				e.printStackTrace();
			} catch (InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			break;

		case 0:
			System.out.println("Thank you for using our services");
			System.exit(0);

		default:
			System.out.println("Please enter valid choice");
			break;
		}


	}

	public static void main(String[] args) 
	{
		Client client = new Client();
		while(true) {
			client.menu();
		}
	}
	
	
}

