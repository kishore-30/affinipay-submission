package com.affinipay.assignment.representation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "transactionId", "newtime" })
public class UserResponseRepresentation {

	public UserResponseRepresentation(String transactionId, String newtime) {
		super();

		this.newtime = newtime;
		this.transactionId = transactionId;
	}

	protected String newtime;

	protected String transactionId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getNewtime() {
		return newtime;
	}

	public void setNewtime(String newtime) {
		this.newtime = newtime;
	}

}
