package createAccount;

import static org.junit.Assert.*;

import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestMockAccount {

	
	private IAccountService accountService;
	
	@Mock
	private IAccountDao accountDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountDao);
	}
	
	@Test
	public void test_create_AccountMethod_withMock() throws InvalidCustomer, InvalidOpeningBalance{
		
		Customer customer = new Customer();
		customer.setFirstName("Khishore");
		customer.setLastName("Kumar");
		Address address = new Address();
		address.setCity("chennai");
		address.setDoorNo("11");
		customer.setAddress(address);
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setCustomer(customer);
		account.setOpeningBalance(2000);
		
		//ProxyDeclaration
		Mockito.when(accountDao.addAccount(account)).thenReturn(true);
		
		Account newAccount=accountService.createAccount(customer,2000);
		
		Mockito.verify(accountDao).addAccount(newAccount);
		
		assertEquals(newAccount.getOpeningBalance(),account.getOpeningBalance(),0.0);
		
		
	}

}
