package org.cap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.cap.model.Account;



public class AccountDaoImpl implements IAccountDao{

	@Override
	public boolean addAccount(Account account) {
		
		String sql="insert into account values(?,?,?)";
			try {
				PreparedStatement ps = getMySQLConnection().prepareStatement(sql);
				ps.setInt(1, account.getAccountNo());
				ps.setDouble(2, account.getOpeningBalance());
				ps.setString(3, account.getCustomer().getFirstName());
				
				int count=ps.executeUpdate();
				if(count>0)
						return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return false;	
	}
	
	private Connection getMySQLConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/BDD1","root", "India123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public Account findAccountById(int accountNo2) {
		return null;
	}

	@Override
	public Account updated(int accountNo, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
