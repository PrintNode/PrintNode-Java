package com.printnode.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.printnode.api.Account;
import com.printnode.api.Client;
import com.printnode.api.Computer;
import com.printnode.api.CreateAccountJson;
import com.printnode.api.CreateAccountObject;
import com.printnode.api.Download;
import com.printnode.api.Options;
import com.printnode.api.PrintJobJson;
import com.printnode.api.Printer;
import com.printnode.api.State;
import com.printnode.api.Whoami;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.AuthScope;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;

/**
 *
 * This is the main class used in PrintNode-Java. Controls every request.
 * @author JakeTorrance
 * @author PrintNode
 * */
public class APIClient{

	private static final TypeAdapter<Integer> IntAdapter = new TypeAdapter<Integer>(){
		@Override public void write(JsonWriter out, Integer value) throws IOException {
			if(value == -1){
				out.nullValue();
			}else{
				out.value(value);
			}
		}
		@Override public Integer read(JsonReader in) throws IOException {
			return in.nextInt();
		}

	};

	static final TypeAdapter<Boolean> BoolAdapter = new TypeAdapter<Boolean>() {
		@Override public void write(JsonWriter out, Boolean value) throws IOException {
			if(value == null){
				out.nullValue();
			}else{
				out.value(value);
			}
		}
		@Override public Boolean read (JsonReader in) throws IOException {
			return in.nextBoolean();
		}
	};

	private final static String API_URL = "https://api.printnode.com";

	private String[] childHeaders = new String[2];

	private CredentialsProvider credentials;

	/**
	 * default constructor for the APIClient.
	 * 
	 * @param auth an Auth object which the APIClient will then save into a CredentialsProvider object.
	 * @see Auth
	 * */
	public APIClient(Auth auth){
		String[] credentialsArray = auth.getCredentials();
		credentials = new BasicCredentialsProvider();
		credentials.setCredentials(
			new AuthScope(null,-1),
			new UsernamePasswordCredentials(credentialsArray[0],credentialsArray[1])
			);
		childHeaders[0] = "";
		childHeaders[1] = "";

	}

	/**
	 * Sets the headers to be authenticated via X-Child-Account-By-Id.
	 *
	 * @param id id of child account.
	 * */
	public void setChildAccountById(int id){
		childHeaders[0] = "X-Child-Account-By-Id";
		childHeaders[1] = Integer.toString(id);
	}

	/**
	 * Sets the headers to be authenticated via X-Child-Account-By-Email.
	 *
	 * @param email email of child account.
	 * */
	public void setChildAccountByEmail(String email){
		childHeaders[0] = "X-Child-Account-By-Email";
		childHeaders[1] = email;
	}

	/**
	 * Sets the headers to be authenticated via X-Child-Account-By-CreatorRef.
	 *
	 * @param creatorRef creator refence of child account.
	 * */
	public void setChildAccountByCreatorRef(String creatorRef){
		childHeaders[0] = "X-Child-Account-By-CreatorRef";
		childHeaders[1] = creatorRef;
	}

	/**
	 * Deletes apikey specified by the parameter.
	 *
	 * @param description apikey name to be deleted.
	 * @return Boolean whether apikey was deleted or not.
	 * */
	public boolean deleteApiKey(String description) throws IOException{
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		boolean result;
		try{
			HttpDelete httpdelete = new HttpDelete(API_URL+"/account/apikey/"+description);
			httpdelete.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpdelete);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonPrimitive responseParse = new JsonParser().parse(responseString).getAsJsonPrimitive();
				result = responseParse.getAsBoolean();
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return result;
	}

	/**
	 * Deletes tag specified by the parameter.
	 *
	 * @param tagname Tag to be deleted.
	 * @return Boolean whether tag was deleted or not.
	 * */
	public boolean deleteTag(String tagname) throws IOException{
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		boolean result;
		try{
			HttpDelete httpdelete = new HttpDelete(API_URL+"/account/tag/"+tagname);
			httpdelete.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpdelete);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonPrimitive responseParse = new JsonParser().parse(responseString).getAsJsonPrimitive();
				result = responseParse.getAsBoolean();
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return result;
	}

	/**
	 * Deletes account.
	 * This can only be used when specified a child account is specified by email, id or creatorRef.
	 * Otherwise, will throw an APIException as the response will throw a non 2xx status code.
	 *
	 * @return Boolean whether account was deleted or not.
	 * @see APIException
	 * */
	public boolean deleteAccount() throws IOException{
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		boolean result;
		try{
			HttpDelete httpdelete = new HttpDelete(API_URL+"/account/");
			httpdelete.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpdelete);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonPrimitive responseParse = new JsonParser().parse(responseString).getAsJsonPrimitive();
				result = responseParse.getAsBoolean();
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return result;
	}

	/**
	 * Given a set of clients, such as "10-15" or "10,11,13" or just "10", will set whether clients in the set are enabled.
	 * If you are unsure what is possible for clientSet, check the PrintNode API docs on printnode.com.
	 *
	 * @param clientSet set of clients as a string.
	 * @param enabled whether you are disabling or enabling the clients.
	 * @return An array of ids relative to clients with changed values.
	 * */
	public int[] modifyClientDownloads(String clientSet, boolean enabled) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		int[] results;
		try{
			HttpPatch httppost = new HttpPatch(API_URL+"/download/clients/"+clientSet);
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			httppost.addHeader("Content-Type","application/json");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, IntAdapter)
				.registerTypeAdapter(int.class,IntAdapter)
				.registerTypeAdapter(Boolean.class,BoolAdapter)
				.registerTypeAdapter(boolean.class,BoolAdapter)
				.create();
			JsonObject jObject = new JsonObject();
			jObject.addProperty("enabled",enabled);
			String json = gson.toJson(jObject);
			StringEntity jsonEntity = new StringEntity(json);
			httppost.setEntity(jsonEntity);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				results = new int[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					results[i] = responseParse.get(i).getAsInt();
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return results;
	}

	/**
	 * Given an Account object, modifies an account.
	 * This can only be used when a child account is specified by email, id or creatorRef.
	 *
	 * @param accountInfo account object. Requires atleast one value set. Having id set will throw an exception, unelss set to -1.
	 * @return Whoami object of the modified account.
	 * @see Account
	 * @see Whoami
	 * */
	public Whoami modifyAccount(Account accountInfo) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Whoami account;
		try{
			HttpPatch httppost = new HttpPatch(API_URL+"/account/");
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			httppost.addHeader("Content-Type","application/json");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, IntAdapter)
				.registerTypeAdapter(int.class,IntAdapter)
				.registerTypeAdapter(Boolean.class,BoolAdapter)
				.registerTypeAdapter(boolean.class,BoolAdapter)
				.create();
			String json = gson.toJson(accountInfo);
			StringEntity jsonEntity = new StringEntity(json);
			httppost.setEntity(jsonEntity);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonObject responseParse = new JsonParser().parse(responseString).getAsJsonObject();
				account = new Whoami(responseParse);
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return account;
	}

	/**
	 * Given a name and value for a tag, creates that tag.
	 *
	 * @param tagName name of tag.
	 * @param tagValue value of tag.
	 * @return String which will be "created" when no errors happen.
	 * */
	public String createTag(String tagName, String tagValue) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		String tag;
		try{
			HttpPost httppost = new HttpPost(API_URL+"/account/tag/"+tagName);
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			httppost.addHeader("Content-Type","application/json");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, IntAdapter)
				.registerTypeAdapter(int.class,IntAdapter)
				.registerTypeAdapter(Boolean.class,BoolAdapter)
				.registerTypeAdapter(boolean.class,BoolAdapter)
				.create();
			String json = gson.toJson(tagValue);
			StringEntity jsonEntity = new StringEntity(json);
			httppost.setEntity(jsonEntity);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				String responseParse = new JsonParser().parse(responseString).getAsString();
				tag = responseParse;
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return tag;
	}

	/**
	 * Given a description for an apikey, creates that apikey.
	 *
	 * @param description reference for the apikey.
	 * @return The value of the apikey.
	 * */
	public String createApiKey(String description) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		String apikey;
		try{
			HttpPost httppost = new HttpPost(API_URL+"/account/apikey/"+description);
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				String responseParse = new JsonParser().parse(responseString).getAsString();
				apikey = responseParse;
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return apikey;
	}

	/**
	 * Given a CreateAccountJson object, creates an account.
	 *
	 * @param accountInfo CreateAccountJson object with values set.
	 * @return CreateAccountObject. This is practically the same as the CreateAccountJson object with some other additions.
	 * @see CreateAccountJson
	 * @see CreateAccountObject
	 * @see Account
	 * */
	public CreateAccountObject createAccount(CreateAccountJson accountInfo) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		CreateAccountObject account;
		try{
			HttpPost httppost = new HttpPost(API_URL+"/account/");
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			httppost.addHeader("Content-Type","application/json");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, IntAdapter)
				.registerTypeAdapter(int.class,IntAdapter)
				.registerTypeAdapter(Boolean.class,BoolAdapter)
				.registerTypeAdapter(boolean.class,BoolAdapter)
				.create();
			String json = gson.toJson(accountInfo);
			StringEntity jsonEntity = new StringEntity(json);
			httppost.setEntity(jsonEntity);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonObject responseParse = new JsonParser().parse(responseString).getAsJsonObject();
				account = new CreateAccountObject(responseParse);
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return account;
	}

	/**
	 * Given a PrintJobJson object, creates a PrintJob.
	 *
	 * @param printjobinfo PrintJobJson object with values set.
	 * @return id of printjob that was just created.
	 * @see PrintJobJson
	 * */
	public int createPrintJob(PrintJobJson printjobinfo) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		int printjob;
		try{
			HttpPost httppost = new HttpPost(API_URL+"/printjobs");
			httppost.addHeader(childHeaders[0],childHeaders[1]);
			httppost.addHeader("Content-Type","application/json");
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, IntAdapter)
				.registerTypeAdapter(int.class,IntAdapter)
				.registerTypeAdapter(Boolean.class,BoolAdapter)
				.registerTypeAdapter(boolean.class,BoolAdapter)
				.create();
			String json = gson.toJson(printjobinfo);
			StringEntity jsonEntity = new StringEntity(json);
			httppost.setEntity(jsonEntity);
			CloseableHttpResponse response = client.execute(httppost);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				int responseParse = new JsonParser().parse(responseString).getAsInt();
				printjob = responseParse;
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return printjob;
	}

	/**
	 * Returns a Whoami object.
	 *
	 * @return object of a /whoami/ request.
	 * @see Whoami
	 * */
	public Whoami getWhoami() throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Whoami whoami;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/whoami/");
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonObject responseParse = new JsonParser().parse(responseString).getAsJsonObject();
				whoami = new Whoami(responseParse);
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return whoami;

	}

	/**
	 * Given the OS of the client as a string, returns the latest available client.
	 * The parameter can ONLY be "osx" or "windows". either can have any case, so "OSX" or "Windows" also works.
	 *
	 * @param os os of client requested as a string.
	 * @return object of the request.
	 * @see Download
	 * */
	public Download getLatestClient(String os) throws APIException, IOException {
		if(!(os.toLowerCase().equals("windows")) && !(os.toLowerCase().equals("osx"))){
			throw new APIException("getLatestClient only takes the argment as 'osx' or 'windows'.");
		}
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Download download;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/download/client/"+os);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonObject responseParse = new JsonParser().parse(responseString).getAsJsonObject();
				download = new Download(responseParse);
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return download;

	}


	/**
	 * Given a set of clients, returns an array of these clients.
	 *
	 *
	 * @param clientSet set of clients.
	 * @return Array of client objects.
	 * @see Client
	 * */
	public Client[] getClients(String clientSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Client[] clients;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/download/clients/"+clientSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				clients = new Client[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					clients[i] = new Client(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return clients;

	}

	/**
	 * Given a set of computers, return an array of these computers.
	 *
	 * @param computerSet set of computers.
	 * @return Array of computer objects.
	 * @see Computer
	 * */
	public Computer[] getComputers(String computerSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Computer[] computers;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/computers/"+computerSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				computers = new Computer[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					computers[i] = new Computer(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return computers;

	}

	/**
	 * Given a set of states, return an array of states for each printjob in the set.
	 *
	 * @param printJobSet set of printjobs to find the states for.
	 * @return an array of an array of state objects.
	 * @see State
	 * */
	public State[][] getStates(String printJobSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		State[][] states;
		try{
			String endPointUrl = API_URL+"/printjobs/"+printJobSet;
			if(printJobSet == ""){
				endPointUrl = endPointUrl+"states/";
			}else{
				endPointUrl = endPointUrl+"/states/";
			}
			HttpGet httpget = new HttpGet(endPointUrl);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				states = new State[responseParse.size()][];
				for(int i = 0;i < responseParse.size();i++){
					JsonArray jsonSpecificPrinterStates = responseParse.get(i).getAsJsonArray();
					State[] specificPrinterStates = new State[jsonSpecificPrinterStates.size()];
					for(int j = 0;j < jsonSpecificPrinterStates.size();j++){
						specificPrinterStates[j] = new State(jsonSpecificPrinterStates.get(j).getAsJsonObject());
					}
					states[i] = specificPrinterStates;
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return states;

	}

	/**
	 * Given a set of printers, and a set of printjobs, return an array of printjobs relative to the set of printers.
	 *
	 * @param printerSet set of printers.
	 * @param printJobSet set of printjobs.
	 * @return Array of printjobs.
	 * @see PrintJob
	 * */
	public PrintJob[] getPrintJobsByPrinter(String printerSet,String printJobSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		PrintJob[] printjobs;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/printers/"+printerSet+"/printjobs/"+printJobSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				printjobs = new PrintJob[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					printjobs[i] = new PrintJob(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return printjobs;

	}

	/**
	 * Given a set of printjobs, return an array of printjobs.
	 *
	 * @param printJobSet set of printjobs.
	 * @return Array of printjobs.
	 * @see PrintJob
	 * */
	public PrintJob[] getPrintJobs(String printJobSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		PrintJob[] printjobs;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/printjobs/"+printJobSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				printjobs = new PrintJob[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					printjobs[i] = new PrintJob(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return printjobs;

	}

	/**
	 * Given a set of computers, and a set of printers, return an array of printers relative to the set of computers.
	 * If computerSet is a blank string, this will throw an APIException.
	 *
	 * @param computerSet set of computers.
	 * @param printerSet set of printers.
	 * @return Array of Printers.
	 * @see Printer
	 * */
	public Printer[] getPrintersByComputers(String computerSet, String printerSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Printer[] printers;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/computers/"+computerSet+"/printers/"+printerSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				printers = new Printer[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					printers[i] = new Printer(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return printers;

	}

	/**
	 * Given a set of printers, return an array of printers.
	 *
	 * @param printerSet set of printers.
	 * @return Array of Printers.
	 * @see Printer
	 * */
	public Printer[] getPrinters(String printerSet) throws IOException {
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		Printer[] printers;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/printers/"+printerSet);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				JsonArray responseParse = new JsonParser().parse(responseString).getAsJsonArray();
				printers = new Printer[responseParse.size()];
				for(int i = 0;i < responseParse.size();i++){
					printers[i] = new Printer(responseParse.get(i).getAsJsonObject());
				}
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return printers;

	}

	/**
	 * Given a reference to an apikey, return that apikey.
	 *
	 * @param apikey reference to the apikey, which can be found in a whoami request.
	 * @return value of the apikey.
	 * */
	public String getApiKeys(String apikey) throws IOException{
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		String apikeyvalue;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/account/apikey/"+apikey);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				String responseParse = new JsonParser().parse(responseString).getAsString();
				apikeyvalue = responseParse;
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return apikeyvalue;
	}

	/**
	 * Given a tagname, return the value of that tag.
	 *
	 * @param tagName name of tag.
	 * @return value of the tag.
	 * */
	public String getTags(String tagName) throws IOException{
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();
		String tagValue;
		try{
			HttpGet httpget = new HttpGet(API_URL+"/account/tag/"+tagName);
			httpget.addHeader(childHeaders[0],childHeaders[1]);
			CloseableHttpResponse response = client.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				checkResponseForExceptions(response.getStatusLine().getStatusCode(),responseString);
				String responseParse = new JsonParser().parse(responseString).getAsString();
				tagValue = responseParse;
			}finally{
				response.close();
			}
		}finally{
			client.close();
		}
		return tagValue;
	}

	private void checkResponseForExceptions(int statuscode, String response){
		if(!(Integer.toString(statuscode).startsWith("2"))){
			throw new APIException("\nAPI response error found with status code:"+statuscode+"\nThe response content was this:"+response);
		}
	}

}
