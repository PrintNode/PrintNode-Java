package com.printnode.api;

import com.google.gson.*;

/**
 * Object for a State.
 * */
public class State{

	private String json;

	private int printJobId;
	private String state;
	private String message;
	private String clientVersion;
	private int age;
	private String createTimestamp;


	/**
	 * Parses a JsonObject into State.
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
	public State(JsonObject response){
		if(!response.get("printJobId").isJsonNull()){
			printJobId = response.get("printJobId").getAsInt();
		}
		if(!(response.get("message").isJsonNull())){
			message = response.get("message").getAsString();
		}
		if(!response.get("clientVersion").isJsonNull()){
			clientVersion = response.get("clientVersion").getAsString();
		}
		if(!response.get("age").isJsonNull()){
			age = response.get("age").getAsInt();
		}
		if(!response.get("createTimestamp").isJsonNull()){
			createTimestamp = response.get("createTimestamp").getAsString();
		}
		if(!response.get("state").isJsonNull()){
			state = response.get("state").getAsString();
		}
		json = response.toString();
	}

	/**
	 * @return id of the printjob this state is for.
	 * */
	public int getPrintJobId(){
		return printJobId;
	}

	/**
	 * @return human-readable information about this state.
	 * */
	public String getMessage(){
		return message;
	}

	/**
	 * @return client version the printjob was made from.
	 * */
	public String getClientVersion(){
		return clientVersion;
	}

	/**
	 * @return age of printjob.
	 * */
	public int getAge(){
		return age;
	}

	/**
	 * @return the timestamp of when the printjob was created.
	 * */
	public String getCreateTimestamp(){
		return createTimestamp;
	}

	/**
	 * @return the state of the printjob.
	 * */
	public String getState(){
		return state;
	}

	/**
	 * @return The original response string.
	 * */
	public String toString() {
		return json;
	}

}
