package com.printnode.api;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Object for a Whoami.
 * */
public class Whoami{

	private String json;

	private int id;
	private String firstname;
	private String lastname;
	private boolean canCreateSubAccounts;
	private String email;
	private String creatorEmail;
	private String creatorRef;
	private int[] childAccounts;
	private int credits;
	private int numComputers;
	private int totalPrints;
	private String[] versions;
	private String[] connected;
	private HashMap<String,String> Tags;
	private String state;
	private String[] permissions;


	/**
	 * Parses a JsonObject into Whoami.
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
	public Whoami(JsonObject response){
		if(!response.get("id").isJsonNull()){
			id = response.get("id").getAsInt();
		}
		if(!response.get("firstname").isJsonNull()){
			firstname = response.get("firstname").getAsString();
		}
		if(!(response.get("lastname").isJsonNull())){
			lastname = response.get("lastname").getAsString();
		}
		if(!response.get("canCreateSubAccounts").isJsonNull()){
			canCreateSubAccounts = response.get("canCreateSubAccounts").getAsBoolean();
		}
		if(!response.get("email").isJsonNull()){
			email = response.get("email").getAsString();
		}
		if(!response.get("creatorEmail").isJsonNull()){
			creatorEmail = response.get("creatorEmail").getAsString();
		}
		if(!response.get("creatorRef").isJsonNull()){
			creatorRef = response.get("creatorRef").getAsString();
		}
		if(!response.get("childAccounts").isJsonNull()){
			JsonArray jsonChildAccounts = response.get("childAccounts").getAsJsonArray();
			childAccounts = new int[jsonChildAccounts.size()];
			for(int i = 0;i < jsonChildAccounts.size();i++){
				childAccounts[i] = jsonChildAccounts.get(i).getAsInt();
			}
		}
		if(!response.get("credits").isJsonNull()){
			credits = response.get("credits").getAsInt();
		}
		if(!response.get("numComputers").isJsonNull()){
			numComputers = response.get("numComputers").getAsInt();
		}
		if(!response.get("totalPrints").isJsonNull()){
			totalPrints = response.get("totalPrints").getAsInt();
		}
		if(!response.get("versions").isJsonNull()){
			JsonArray jsonVersions = response.get("versions").getAsJsonArray();
			versions = new String[jsonVersions.size()];
			for(int i = 0;i < jsonVersions.size();i++){
				versions[i] = jsonVersions.get(i).getAsString();
			}
		}
		if(!response.get("connected").isJsonNull()){
			JsonArray jsonConnected = response.get("connected").getAsJsonArray();
			connected = new String[jsonConnected.size()];
			for(int i = 0;i < jsonConnected.size();i++){
				connected[i] = jsonConnected.get(i).getAsString();
			}
		}
		if(!response.get("Tags").isJsonNull() && !response.get("Tags").isJsonArray()){

			JsonObject jsonTags = response.get("Tags").getAsJsonObject();
			Set<Map.Entry<String,JsonElement>> tagsEntries = jsonTags.entrySet();
			Tags = new HashMap<String,String>();
			for (Map.Entry<String,JsonElement> tagEntry : tagsEntries) {
				String jsonTagValue = tagEntry.getValue().getAsString();
				Tags.put(tagEntry.getKey(),jsonTagValue);
			}
		}
		if(!response.get("state").isJsonNull()){
			state = response.get("state").getAsString();
		}
		if(response.has("permissions") && !response.get("permissions").isJsonNull()){
			JsonArray jsonPermissions = response.get("permissions").getAsJsonArray();
			permissions = new String[jsonPermissions.size()];
			for(int i = 0;i < jsonPermissions.size();i++){
				permissions[i] = jsonPermissions.get(i).getAsString();
			}
		}
		json = response.toString();
	}

	/**
	 * @return id of this account.
	 * */
	public int getId(){
		return id;
	}

	/**
	 * @return first name of the account holder.
	 * */
	public String getFirstname(){
		return firstname;
	}

	/**
	 * @return last name of the account holder.
	 * */
	public String getLastname(){
		return lastname;
	}

	/**
	 * @return whether this account can make sub accounts (i.e is a parent account.)
	 * */
	public boolean getCanCreateSubAccounts(){
		return canCreateSubAccounts;
	}

	/**
	 * @return the email for this account.
	 * */
	public String getEmail(){
		return email;
	}

	/**
	 * @return the email of the creator of this account.
	 * */
	public String getCreatorEmail(){
		return creatorEmail;
	}

	/**
	 * @return creator reference for this account.
	 * */
	public String getCreatorRef(){
		return creatorRef;
	}

	/**
	 * @return array of child account ids for this account.
	 * */
	public int[] getChildAccounts(){
		return childAccounts;
	}

	/**
	 * @return number of printing credits.
	 * */
	public int getCredits(){
		return credits;
	}

	/**
	 * @return Number of computers used by this account.
	 * */
	public int getNumComputers(){
		return numComputers;
	}

	/**
	 * @return total prints done by this account.
	 * */
	public int getTotalPrints(){
		return totalPrints;
	}

	/**
	 * @return an array of client versions used for this account.
	 * */
	public String[] getVersions(){
		return versions;
	}

	/**
	 * @return an array of connected clients.
	 * */
	public String[] getConnected(){
		return connected;
	}

	/**
	 * @return a hashamp of the tags this account has.
	 * */
	public HashMap<String,String> getTags(){
		return Tags;
	}

	/**
	 * @return the state of this account.
	 * */
	public String getState(){
		return state;
	}

	/**
	 * @return an array of permissions for this account.
	 * */
	public String[] getPermissions(){
		return permissions;
	}

	/**
	 * @return the original response string.
	 * */
	public String toString() {
		return json;
	}

}
