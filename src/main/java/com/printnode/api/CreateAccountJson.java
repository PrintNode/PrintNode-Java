package com.printnode.api;

import java.io.*;
import com.printnode.api.Account;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

/**
 * Object that serializes an object for JSON in a CreateAccount request.
 * */
public class CreateAccountJson {


	@SerializedName("Account") private Account account;

	@SerializedName("ApiKeys") private String[] apiKeys;

	@SerializedName("Tags") private HashMap<String,String> tags = new HashMap<String,String>();

	/**
	 * For account creation, firstname, lastname, email and password are all required to be set.
	 * The rest of them can be set via setters.
	 *
	 * @param firstname First name of new account holder.
	 * @param lastname Last name of new account holder.
	 * @param email Email of the new account holder.
	 * @param password password of the new account.
	 * */
	public CreateAccountJson(String firstname, String lastname, String email, String password) throws IOException{
		account = new Account(firstname,lastname,email,password);
	}

	/**
	 * @return Account object of the account to be created.
	 * @see Account
	 * */
	public Account getAccount(){
		return account;
	}

	/**
	 * @param tagname name of tag to be added.
	 * @param tagvalue value of tag to be added.
	 * */
	public void addTag(String tagname, String tagvalue){;
		tags.put(tagname,tagvalue);
	}

	/**
	 * @param apiKeys Name of apikey descriptions to be added.
	 * */
	public void setApiKeys(String[] apiKeys){
		this.apiKeys = apiKeys;
	}

}
