package org.cap.exception;

public class InvalidCustomer extends Exception{

	public InvalidCustomer(String Message) {
		super(Message);
	}
	public InvalidCustomer() {
		super();
	}
}
