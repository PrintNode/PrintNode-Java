package com.printnode.api;

import com.google.gson.*;
import com.printnode.api.Computer;
import com.printnode.api.Capabilities;

/*
 * Object for a printer.
 * */
public class Printer{

	private String json;

	private int id;
	private Computer computer;
	private String name;
	private String description;
	private Capabilities capabilities;
	private String defaults;
	private String createTimestamp;
	private String state;

	/**
	 * Parses a JsonObject into Client.
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

	public Printer(JsonObject response){
		if(!response.get("id").isJsonNull()){
			id = response.get("id").getAsInt();
		}
		if(!response.get("computer").isJsonNull()){
			computer = new Computer(response.get("computer").getAsJsonObject());
		}
		if(!response.get("description").isJsonNull()){
			description = response.get("description").getAsString();
		}
		if(!response.get("capabilities").isJsonNull()){
			capabilities = new Capabilities(response.get("capabilities").getAsJsonObject());
		}
		if(!response.get("default").isJsonNull()){
			defaults = response.get("default").getAsString();
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
	 * @return the id of the printer.
	 * */
	public int getId(){
		return id;
	}

	/**
	 * @return the name of the printer.
	 * */
	public String getName(){
		return name;
	}

	/**
	 * @return the computer attached to this printer.
	 * @see Computer
	 * */
	public Computer getComputer(){
		return computer;
	}

	/**
	 * @return the description of the printer.
	 * */
	public String getDescription(){
		return description;
	}

	/**
	 * @return the capabilities object of the printer.
	 * @see Capabilities
	 * */
	public Capabilities getCapabilities(){
		return capabilities;
	}

	/**
	 * @return default settings for this printer.
	 * */
	public String getDefault(){
		return defaults;
	}

	/**
	 * @return timestamp of when the printer was added to the account.
	 * */
	public String getCreateTimestamp(){
		return createTimestamp;
	}

	/**
	 * @return current state of the printer.
	 * */
	public String getState(){
		return state;
	}

	/**
	 * @return the original response string.
	 * */
	public String toString(){
		return json;
	}

}
