package org.cap.service;

import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.AccountNotFound;
import org.cap.exception.InsufficientBalance;
import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.util.AccountUtil;

public class AccountServiceImpl  implements IAccountService{
	
	private IAccountDao accountDao=new AccountDaoImpl();

	public AccountServiceImpl(IAccountDao accountDao2) {
		this.accountDao=accountDao2;
	}
	public AccountServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Account createAccount(Customer customer, double amount) throws InvalidCustomer, InvalidOpeningBalance {
		if(customer!=null) {
			if(amount>=500) {
				Account account = new Account();
				account.setCustomer(customer);
				account.setOpeningBalance(amount);
				account.setAccountNo(AccountUtil.generateAccountNo());
				
				boolean flag = accountDao.addAccount(account);
				if(flag)
					return account;
				else
					return null;
			}
			else {
				throw new InvalidOpeningBalance("Sorry! InvalidOpeningBalance!");
			}
		}
		else {
			throw new InvalidCustomer("Sorry! Customer refers NULL!");
		}
		//return null;
	}
	@Override
	public Account findAccountById(int accountNo2) {
		
		return accountDao.findAccountById(accountNo2);
	}
	@Override
	public Account withdraw(int accountNo, double amount_withdrawn) throws AccountNotFound, InsufficientBalance {

		Account account = accountDao.findAccountById(accountNo);
		
		if(account!=null) {
			if(account.getOpeningBalance()>=amount_withdrawn)
				account.setOpeningBalance(account.getOpeningBalance()-amount_withdrawn);
			else
				throw new InsufficientBalance("Sorry! Insufficient balance.");
		}
		else
			throw new AccountNotFound ("Sorry! Account not found");
		
		
		accountDao.updated(accountNo, account.getOpeningBalance());
		
		return account;
	}
	
	@Override
	public Account deposit(int accountNo, double amount_deposit) throws AccountNotFound {
Account account = accountDao.findAccountById(accountNo);
		
		if(account!=null) {
				account.setOpeningBalance(account.getOpeningBalance()+amount_deposit);
		}
		else
			throw new AccountNotFound ("Sorry! Account not found");
		
		
		accountDao.updated(accountNo, account.getOpeningBalance());
		
		return account;
	}
	
	@Override
	public Account updated(int accountNo, double amount) {
		// TODO Auto-generated method stub
		return accountDao.updated(accountNo,amount);
	}
	
}
