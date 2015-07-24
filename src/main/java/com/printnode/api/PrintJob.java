package com.printnode.api;

import com.google.gson.*;
import com.printnode.api.Printer;

/**
 * Object for a PrintJob.
 * */
public class PrintJob{

	private String json;

	private int id;
	private Printer printer;
	private String title;
	private String contentType;
	private String source;
	private String expireAt;
	private String createTimestamp;
	private String state;

	/**
	 * Parses a JsonObject into PrintJob.
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
	public PrintJob(JsonObject response){
		if(!response.get("id").isJsonNull()){
			id = response.get("id").getAsInt();
		}
		if(!response.get("printer").isJsonNull()){
			printer = new Printer(response.get("printer").getAsJsonObject());
		}
		if(!response.get("title").isJsonNull()){
			title = response.get("title").getAsString();
		}
		if(!response.get("contentType").isJsonNull()){
			contentType = response.get("contentType").toString();
		}
		if(!response.get("source").isJsonNull()){
			source = response.get("source").getAsString();
		}
		if(!response.get("createTimestamp").isJsonNull()){
			createTimestamp = response.get("createTimestamp").getAsString();
		}
		if(!response.get("expireAt").isJsonNull()){
			expireAt = response.get("expireAt").getAsString();
		}
		if(!response.get("state").isJsonNull()){
			state = response.get("state").getAsString();
		}
		json = response.toString();
	}

	/**
	 * @return id of this printjob.
	 * */
	public int getId(){
		return id;
	}

	/**
	 * @return Printer object relative to this printjob.
	 * @see Printer
	 * */
	public Printer getPrinter(){
		return printer;
	}

	/**
	 * @return Title of this printjob.
	 * */
	public String getTitle(){
		return title;
	}

	/**
	 * @return content type of this printjob.
	 * */
	public String getContentType(){
		return contentType;
	}

	/**
	 * @return source of the printjob.
	 * */
	public String getSource(){
		return source;
	}

	/**
	 * @return timestamp of when the printjob was created.
	 * */
	public String getCreateTimestamp(){
		return createTimestamp;
	}

	/**
	 * @return time after timestamp when it expires.
	 * */
	public String getExpireAt(){
		return expireAt;
	}

	/**
	 * @return state of the printjob.
	 * */
	public String getState(){
		return state;
	}

	/**
	 * @return original response string.
	 * */
	public String toString(){
		return json;
	}

}
