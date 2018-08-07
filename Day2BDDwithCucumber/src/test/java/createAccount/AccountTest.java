package createAccount;

import static org.junit.Assert.*;

import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AccountTest {

	private Customer customer;
	
	private IAccountService accountService;
	
	@Before
	public void init() {
		  customer = new Customer();
		  customer.setFirstName("Khishore");
		  customer.setLastName("Kumar");
		  Address address=new Address();
		  address.setDoorNo("12");
		  address.setCity("chennai");
		  customer.setAddress(address);
		  
		  accountService=new AccountServiceImpl();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void test_customer_with_null() throws InvalidCustomer, InvalidOpeningBalance {
		customer=null;
		exception.expect(InvalidCustomer.class);
		exception.expectMessage("Sorry! Customer refers NULL!");
		accountService.createAccount(customer, 1000);
	}
	
}
