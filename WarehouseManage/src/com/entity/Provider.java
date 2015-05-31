package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Provider entity. @author MyEclipse Persistence Tools
 */

public class Provider implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5316911357823774506L;
	private String providerId;
	private String providerName;
	private String providerAddress;
	private String providerPhonenumber;
	private String providerEmail;
	private String providerContact;
	private Set supplyBills = new HashSet(0);

	// Constructors

	/** default constructor */
	public Provider() {
	}

	/** minimal constructor */
	public Provider(String providerId, String providerName,
			String providerAddress, String providerEmail, String providerContact) {
		this.providerId = providerId;
		this.providerName = providerName;
		this.providerAddress = providerAddress;
		this.providerEmail = providerEmail;
		this.providerContact = providerContact;
	}

	/** full constructor */
	public Provider(String providerId, String providerName,
			String providerAddress, String providerPhonenumber,
			String providerEmail, String providerContact, Set supplyBills) {
		this.providerId = providerId;
		this.providerName = providerName;
		this.providerAddress = providerAddress;
		this.providerPhonenumber = providerPhonenumber;
		this.providerEmail = providerEmail;
		this.providerContact = providerContact;
		this.supplyBills = supplyBills;
	}

	// Property accessors

	public String getProviderId() {
		return this.providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderAddress() {
		return this.providerAddress;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}

	public String getProviderPhonenumber() {
		return this.providerPhonenumber;
	}

	public void setProviderPhonenumber(String providerPhonenumber) {
		this.providerPhonenumber = providerPhonenumber;
	}

	public String getProviderEmail() {
		return this.providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderContact() {
		return this.providerContact;
	}

	public void setProviderContact(String providerContact) {
		this.providerContact = providerContact;
	}

	public Set getSupplyBills() {
		return supplyBills;
	}

	public void setSupplyBills(Set supplyBills) {
		this.supplyBills = supplyBills;
	}


}