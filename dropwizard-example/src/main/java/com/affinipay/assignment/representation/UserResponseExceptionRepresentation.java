package com.affinipay.assignment.representation;

public class UserResponseExceptionRepresentation {
	
	protected String transactionId;
	
	protected String errormessage;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public UserResponseExceptionRepresentation(String transactionId, String errormessage) {
		super();
		this.transactionId = transactionId;
		this.errormessage = errormessage;
	}

}
