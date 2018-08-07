package createAccount;

import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Account;
import org.cap.model.Address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef {

	Customer customer;
	private double openingBalance;
	
	private IAccountService accountService;
	
	@Mock
	private IAccountDao accountDao;
	
	private int accountNo;
	private double amount_withdrawn;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		customer= new Customer();
		openingBalance=500;
		
		accountService = new AccountServiceImpl(accountDao);
	}

	@Given("^customer details$")
	public void for_customer_details() throws Throwable {
		  customer.setFirstName("Khishore");
		  customer.setLastName("Kumar");
		  Address address=new Address();
		  address.setDoorNo("12");
		  address.setCity("chennai");
		  customer.setAddress(address);
	}

	@When("^Valid customer$")
	public void valid_customer() throws Throwable {
	 assertNotNull(customer);
	}

	@When("^valid open balance$")
	public void valid_open_balance() throws Throwable {
	  assertTrue(openingBalance>=500);
	}

	@Then("^create new account$")
	public void create_new_account() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setCustomer(customer);
		account.setOpeningBalance(500);
		
		Mockito.when(accountDao.addAccount(account)).thenReturn(true);
		
		//Business logic
		Account account1=accountService.createAccount(customer,openingBalance);
		
		Mockito.verify(accountDao).addAccount(account1);
		assertNotNull(account1);
		assertEquals(openingBalance, account.getOpeningBalance(),0.0);
		assertEquals(account1.getAccountNo(), 1);
		
	}
	
	@Given("^Customer details$")
	public void customer_details() throws Throwable {
	   customer = null;
	}

	@When("^Invalid Customer$")
	public void invalid_Customer() throws Throwable {
	   assertNull(customer);
	}

	@Then("^throw 'Invalid Customer' error message$")
	public void throw_Invalid_Customer_error_message() throws Throwable {
	   try {
		   accountService.createAccount(customer, 3000);
	   }catch (InvalidCustomer e) {
		// TODO: handle exception
	   }
	}

	@Given("^customer details and opening balance$")
	public void customer_details_and_opening_balance() throws Throwable {
		  customer.setFirstName("Khishore");
		  customer.setLastName("Kumar");
		  Address address=new Address();
		  address.setDoorNo("12");
		  address.setCity("chennai");
		  customer.setAddress(address);
		  openingBalance=100;
	}

	@When("^Invalid opening balance$")
	public void invalid_opening_balance() throws Throwable {
		 assertTrue(openingBalance<500);
	}

	@Then("^throw 'Insufficient Balance' error message$")
	public void throw_Insufficient_Balance_error_message() throws Throwable {
		try {
			   accountService.createAccount(customer, openingBalance);
		   }catch (InvalidOpeningBalance e) {
			// TODO: handle exception
		   }
	}
	

	@Given("^Account number$")
	public void account_number() throws Throwable {
		accountNo=1;
	}

	@When("^Valid Account number$")
	public void valid_Account_number() throws Throwable {
  
	}

	@Then("^find account details$")
	public void find_account_details() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setCustomer(customer);
		account.setOpeningBalance(500);
		
		Mockito.when(accountDao.findAccountById(accountNo)).thenReturn(account);
		
		Account account2=accountService.findAccountById(accountNo);
		
		Mockito.verify(accountDao).findAccountById(accountNo);
		
		assertEquals(account.getAccountNo(), account2.getAccountNo());
	}

	@Given("^Account number (\\d+) and amount (\\d+)$")
	public void account_number_and_amount(int accNo, int amount) throws Throwable {
		this.accountNo=accNo;
		this.amount_withdrawn=amount;
	}

	@When("^Find account and do withdraw$")
	public void find_account_and_do_withdraw() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(this.accountNo);
		account.setCustomer(customer);
		account.setOpeningBalance(2000);
		
		Mockito.when(accountDao.findAccountById(this.accountNo)).thenReturn(account);
		Mockito.when(accountDao.updated(accountNo, 1000)).thenReturn(account);
		Account account2 = accountService.withdraw(accountNo,amount_withdrawn);
		Mockito.verify(accountDao).findAccountById(accountNo);
		assertEquals(1000,account.getOpeningBalance(),0.0);
	}

	@When("^Find account and do deposit$")
	public void find_account_and_do_deposit() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(this.accountNo);
		account.setCustomer(customer);
		account.setOpeningBalance(0);
		
		Mockito.when(accountDao.findAccountById(this.accountNo)).thenReturn(account);
		Mockito.when(accountDao.updated(accountNo, 1000)).thenReturn(account);
		Account account2 = accountService.deposit(accountNo,amount_withdrawn);
		Mockito.verify(accountDao).findAccountById(accountNo);
		assertEquals(1000,account.getOpeningBalance(),0.0);
	}
	
	@Then("^update the account details$")
	public void update_the_account_details() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(this.accountNo);
		account.setCustomer(customer);
		account.setOpeningBalance(1000);
		
		Mockito.when(accountDao.updated(accountNo, 1000)).thenReturn(account);
		
		Account account2=accountService.updated(accountNo, 1000);
		assertEquals(1000,account2.getOpeningBalance(),0.0);
	}
}
