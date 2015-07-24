package com.printnode.api;

import java.io.*;
import com.printnode.api.Account;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.google.gson.*;

/**
 * Object that correctly de-serializes the response from a CreateAccount request.
 * */
public class CreateAccountObject {

	private Account account;

	private HashMap<String,String> apiKeys = new HashMap<String,String>();

	private HashMap<String,String> tags = new HashMap<String,String>();

	private String json;

	/**
	 * Parses a JsonObject into CreateAccountObject.
	 * Firstly, it begins iterating over the object.
	 * If the object we are converting is a solo JsonPrimitive, we map it directly to the variable.
	 * If the object we are converting is an array of JsonPrimitives, we firstly create an array of the same size as it.
	 * Then, iterate over it.
	 * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
	 * If the object is a JsonObject with different mappings, it is mapped to a Java Object. 
	 * @param response JsonObject of the response.
	 * @see JsonObject
	 * @see JsonArray
	 * @see JsonPrimitive
	 * @see JsonElement
	 * */
	public CreateAccountObject(JsonObject response){
		json = response.toString();
		if(!response.get("Account").isJsonNull()){
			JsonObject accountObj = response.get("Account").getAsJsonObject();;
			account = new Account();
			if(!accountObj.get("id").isJsonNull()){
				account.setId(accountObj.get("id").getAsInt());
			}
			if(!accountObj.get("creatorRef").isJsonNull()){
				account.setCreatorRef(accountObj.get("creatorRef").getAsString());
			}
			if(!accountObj.get("firstname").isJsonNull()){
				account.setFirstname(accountObj.get("firstname").getAsString());
			}
			if(!accountObj.get("lastname").isJsonNull()){
				account.setLastname(accountObj.get("lastname").getAsString());
			}
			if(!accountObj.get("email").isJsonNull()){
				account.setEmail(accountObj.get("email").getAsString());
			}
		}
		if(!response.get("ApiKeys").isJsonNull() && !response.get("ApiKeys").isJsonArray()){
			Set<Map.Entry<String,JsonElement>> apiKeySet = response.get("ApiKeys").getAsJsonObject().entrySet();
			for (Map.Entry<String,JsonElement> apiKeyEntry : apiKeySet) {
				apiKeys.put(apiKeyEntry.getKey(),apiKeyEntry.getValue().getAsString());
			}
		}
		if(!response.get("Tags").isJsonNull() && !response.get("Tags").isJsonArray()){
			Set<Map.Entry<String,JsonElement>> tagSet = response.get("Tags").getAsJsonObject().entrySet();
			for (Map.Entry<String,JsonElement> tagEntry : tagSet) {
				tags.put(tagEntry.getKey(),tagEntry.getValue().getAsString());
			}
		}
	}

	/**
	 * @return the original response string.
	 * */
	public String toString(){
		return json;
	}

	/**
	 * @return the tags HashMap.
	 * */
	public HashMap<String,String> getTags(){
		return tags;
	}

	/**
	 * @return Account object for this account.
	 * @see Account
	 * */
	public Account getAccount(){
		return account;
	}

	/**
	 * @return Hashmap of ApiKey references and their corresponding values.
	 * */
	public HashMap<String,String> getApiKeys(){
		return apiKeys;
	}

}
