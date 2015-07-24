package com.printnode.api;

import com.google.gson.*;

/**
 * Object for latest downloadable clients.
 * */
public class Download{

	private String json;

	private String edition;
	private String version;
	private String os;
	private String filename;
	private String filesize;
	private String sha1;
	private String releaseTimestamp;
	private String url;


	/**
	 * Parses a JsonObject into Download.
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

	public Download(JsonObject response){
		if(!response.get("version").isJsonNull()){
			version = response.get("version").getAsString();
		}
		if(!response.get("edition").isJsonNull()){
			edition = response.get("edition").getAsString();
		}
		if(!response.get("os").isJsonNull()){
			os = response.get("os").getAsString();
		}
		if(!response.get("filename").isJsonNull()){
			filename = response.get("filename").getAsString();
		}
		if(!response.get("filesize").isJsonNull()){
			filesize = response.get("filesize").getAsString();
		}
		if(!response.get("sha1").isJsonNull()){
			sha1 = response.get("sha1").getAsString();
		}
		if(!response.get("releaseTimestamp").isJsonNull()){
			releaseTimestamp = response.get("releaseTimestamp").getAsString();
		}
		if(!response.get("url").isJsonNull()){
			url = response.get("url").getAsString();
		}
		json = response.toString();
	}

	/**
	 * @return version of the client.
	 * */
	public String getVersion(){
		return version;
	}

	/**
	 * @return Edition of the client.
	 * */
	public String getEdition(){
		return edition;
	}

	/**
	 * @return OS of the client.
	 * */
	public String getOs(){
		return os;
	}

	/**
	 * @return File name of the client.
	 * */
	public String getFilename(){
		return filename;
	}

	/**
	 * @return File size of the client.
	 * */
	public String getFilesize(){
		return filesize;
	}

	/**
	 * @return Sha1 hash of the client.
	 * */
	public String getSha1(){
		return sha1;
	}

	/**
	 * @return Release timestamp of the client.
	 * */
	public String getReleaseTimestamp(){
		return releaseTimestamp;
	}

	/**
	 * @return URL where the client can be downloaded from.
	 * */
	public String getUrl(){
		return url;
	}

	/**
	 * @return the original response string.
	 * */
	public String toString(){
		return json;
	}
}
