package com.printnode.api;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Very large capabilities object for looking at printer capabilities.
 * PrintNode API Docs Link:
 * <a href=https://www.printnode.com/docs/api/curl/#printer-capabilitie://www.printnode.com/docs/api/curl/#printer-capabilities>Capabilities</a> 
 * */
public class Capabilities{

	private String json;

	private String[] bins;
	private boolean collate;
	private int copies;
	private boolean color;
	private String[] dpis;
	private boolean duplex;
	private int[][] extent = new int[2][];
	private String[] medias;
	private int[] nup;
	private HashMap<String,int[]> papers;
	private boolean supports_custom_paper_size;

	/**
	 * Parses a JsonObject into Capabilities.
	 * Firstly, it begins iterating over the object.
	 * If the object we are converting is a solo JsonPrimitive, we map it directly to the variable.
	 * If the object we are converting is an array of JsonPrimitives, we firstly create an array of the same size as it.
	 * Then, iterate over it.
	 * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
	 * If the object is a JsonObject with different mappings, it is mapped to a Java Object. (particularly, such as this class.)
	 * @param response JsonObject of the response.
	 * @see com.google.gson.JsonObject
	 * @see com.google.gson.JsonArray
	 * @see com.google.gson.JsonPrimitive
	 * @see com.google.gson.JsonElement
	 * */
	public Capabilities(JsonObject response){
		if(!response.get("bins").isJsonNull()){
			JsonArray jsonBins = response.get("bins").getAsJsonArray();
			bins = new String[jsonBins.size()];
			for(int i = 0;i < jsonBins.size();i++){
				bins[i] = jsonBins.get(i).getAsString();
			}
		}

		if(!response.get("collate").isJsonNull()){
			collate = response.get("collate").getAsBoolean();
		}
		if(!response.get("copies").isJsonNull()){
			copies = response.get("copies").getAsInt();
		}
		if(!response.get("color").isJsonNull()){
			color = response.get("color").getAsBoolean();
		}
		if(!response.get("dpis").isJsonNull()){
			JsonArray jsonDpis = response.get("dpis").getAsJsonArray();
			dpis = new String[jsonDpis.size()];
			for(int i = 0;i < jsonDpis.size();i++){
				dpis[i] = jsonDpis.get(i).getAsString();
			}
		}
		if(!response.get("duplex").isJsonNull()){
			duplex = response.get("duplex").getAsBoolean();
		}
		if(!response.get("extent").isJsonNull()){
			JsonArray jsonExtent = response.get("extent").getAsJsonArray();
			for (int i = 0;i < jsonExtent.size();i++){
				JsonArray extentValue = jsonExtent.get(i).getAsJsonArray();
				int[] extentArray = new int[extentValue.size()];
				for (int j = 0;j < extentValue.size();j++){
					extentArray[j] = extentValue.get(j).getAsInt();
				}
				extent[i] = extentArray;
			}
		}
		if(!response.get("medias").isJsonNull()){
			JsonArray jsonMedias = response.get("medias").getAsJsonArray();
			medias = new String[jsonMedias.size()];
			for(int i = 0;i < jsonMedias.size();i++){
				medias[i] = jsonMedias.get(i).getAsString();
			}
		}
		if(!response.get("nup").isJsonNull()){
			JsonArray jsonNup = response.get("nup").getAsJsonArray();
			nup = new int[jsonNup.size()];
			for(int i = 0;i < jsonNup.size();i++){
				nup[i] = jsonNup.get(i).getAsInt();
			}
		}
		if(!response.get("papers").isJsonNull()){
			JsonObject jsonPapers = response.get("papers").getAsJsonObject();
			Set<Map.Entry<String,JsonElement>> papersEntries = jsonPapers.entrySet();
			for (Map.Entry<String,JsonElement> paperEntry : papersEntries) {
				JsonArray jsonPaperDimensions = paperEntry.getValue().getAsJsonArray();
				int[] paperDimensions = new int[2];
				for(int i = 0;i < jsonPaperDimensions.size();i++){
					paperDimensions[i] = jsonPaperDimensions.get(i).getAsInt();
				}
				papers.put(paperEntry.getKey(),paperDimensions);
			}

		}
		if(!response.get("supports_custom_paper_size").isJsonNull()){
			supports_custom_paper_size = response.get("supports_custom_paper_size").getAsBoolean();
		}

		json = response.toString();
	}

	/**
	 * @return Array of bins
	 * */
	public String[] getBins(){
		return bins;
	}

	/**
	 * @return boolean of collate
	 * */
	public boolean getCollate(){
		return collate;
	}

	/**
	 * @return maximum number of copies for printer
	 * */
	public int getCopies(){
		return copies;
	}

	/**
	 * @return whether the printer supports color
	 * */
	public boolean getColor(){
		return color;
	}

	/**
	 * @return an array of DPis that the printer supports.
	 * */
	public String[] getDpis(){
		return dpis;
	}


	/**
	 * @return boolean of duplex
	 * */
	public boolean getDuplex(){
		return duplex;
	}

	/**
	 * @return a matrix, where [0][x] is minimum dimensions, and [1][x] is maximum dimensions.
	 * */
	public int[][] getExtent(){
		return extent;
	}

	/**
	 * @return array of media names the printer supports.
	 * */
	public String[] getMedias(){
		return medias;
	}

	/**
	 * @return The number of pages to print per page for compact layour printing.
	 * */
	public int[] getNup(){
		return nup;
	}

	/**
	 * @return Hashmap of paper types possible.
	 * */
	public HashMap<String,int[]> getPapers(){
		return papers;
	}

	/**
	 * @return whether the printer supports custom paper size.
	 * */
	public boolean getCustomPaperSizeSupport(){
		return supports_custom_paper_size;
	}

	/**
	 * @return The original response string.
	 * */
	public String toString(){
		return json;
	}

}
