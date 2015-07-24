package com.printnode.api;

import java.io.*;

/** 
 * Account object. When being used, will generally be created via the blank constructor. The other constructor is for CreateAccountJson,
 * which requires firstname, lastname, email and password to be set.
 * */
public class Account {

	private int id = -1;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String creatorRef;

	/**
	 * Default constructor.
	 * */
	public Account(){
	}

	/**
	 * Constructor for CreateAccountJson. Should only be ran through there.
	 *
	 * @param firstname First name of account holder.
	 * @param lastname lastname of account holder.
	 * @param email email of account holder.
	 * @param password password of account holder.
	 * */
	public Account(String firstname, String lastname, String email, String password){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	/**
	 * @param id id to be set.
	 * */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * @param firstname firstname to be set.
	 * */
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	/**
	 * @param lastname lastname to be set.
	 * */
	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	/**
	 * @param email email to be set.
	 * */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * @param password password to be set.
	 * */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * @param creatorRef creator reference to be set.
	 * */
	public void setCreatorRef(String creatorRef){
		this.creatorRef = creatorRef;
	}

	/**
	 * @return id of account.
	 * */
	public int getId(){
		return id;
	}

	/**
	 * @return firstname of account.
	 * */
	public String getFirstname(){
		return firstname;
	}

	/**
	 * @return lastname of account.
	 * */
	public String getLastname(){
		return lastname;
	}

	/**
	 * @return email of account.
	 * */
	public String getEmail(){
		return email;
	}

	/**
	 * @return password of account.
	 * */
	public String getPassword(){
		return password;
	}

	/**
	 * @return creatorRef of account.
	 * */
	public String getCreatorRef(){
		return creatorRef;
	}

}
