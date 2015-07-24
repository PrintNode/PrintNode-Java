package com.printnode.api;

import java.io.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import com.google.gson.*;
import java.lang.*;

import com.printnode.api.APIException;

/**
 * Auth class required for any APIClient creation. Will throw an exception if neither ApiKey or EmailPassword are set.
 * */
public class Auth {

	private final static String API_URL = "https://apidev.printnode.com";

	private String API_KEY;

	private String EMAIL;

	private String PASSWORD;

	/**
	 * Default constructor.
	 * */
	public Auth() {
	}

	/**
	 * Sets the ApiKey. will not be set if EmailPassword are already set.
	 *
	 * @param ApiKey an ApiKey.
	 * */
	public void setApiKey(String ApiKey) throws APIException{
		if(this.EMAIL == null){
			this.API_KEY = ApiKey;
		}else{
			throw new APIException("API Key was set when email and password have already been set.");
		}
	}

	/**
	 * Sets an Email and a Password. Will not be set if ApiKey is set.
	 *
	 * @param Email email of account to be authenticated
	 * @param Password email of password to be authenticated.
	 * */
	public void setEmailPassword(String Email, String Password) throws APIException{
		if(this.API_KEY == null){
			this.EMAIL = Email;
			this.PASSWORD = Password;
		}else{
			throw new APIException("Email and Password were set when API Key has already been set.");
		}
	}

	/**
	 * Gives the credentials for the APIClient.
	 * Depending on what is set, sets the credentials to the set values.
	 * If none are set, throws an exception.
	 *
	 * @return Credentials for APIClient.
	 * @see APIClient
	 * */
	public String[] getCredentials() throws APIException{
		String[] credentials = new String[2];
		if(this.API_KEY != null){
			credentials[0] = this.API_KEY;
			credentials[1] = "";
		}else if(this.EMAIL != null){
			credentials[0] = this.EMAIL;
			credentials[1] = this.PASSWORD;
		}else{
			throw new APIException("Attempted to run a request with no credentials set.");
		}
		return credentials;
	}


}
