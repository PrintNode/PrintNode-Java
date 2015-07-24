import static org.junit.Assert.*;
import org.junit.Test;

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
import com.printnode.api.*;
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

public class AppTest {

	public APIClient makeClient() throws IOException{
		Auth anAuth = new Auth();
		anAuth.setApiKey("");
		APIClient aClient = new APIClient(anAuth);
		return aClient;
	}

	@Test
	public void testAuth() throws IOException{
		Auth testAuth = new Auth();
		testAuth.setApiKey("");

		APIClient testClient = new APIClient(testAuth);

		Whoami aRequest = testClient.getWhoami();

		boolean test = (aRequest.getCanCreateSubAccounts() == true);

		assertTrue("Failed: Should be int.",test);
	}

	@Test
	public void testComputers() throws IOException{
		APIClient aClient = makeClient();

		Computer[] computers = aClient.getComputers("");

		assertTrue("Failed: Should be 'created'",computers[0].getState().equals("created"));
	}

	@Test
	public void testPrinters() throws IOException{
		APIClient aClient = makeClient();

		Printer[] printers = aClient.getPrinters("");

		assertTrue("Failed: Should be 'created'",printers[0].getComputer().getState().equals("created"));
	}

	@Test
	public void testPrintJobs() throws IOException{
		APIClient aClient = makeClient();

		PrintJob[] printjobs = aClient.getPrintJobs("");

		assertTrue("Failed: should be 'created'",printjobs[0].getPrinter().getComputer().getState().equals("created"));
	}

	@Test
	public void testSubmitPrintJob() throws IOException{
		APIClient aClient = makeClient();

		PrintJobJson myPrintJobCreation = new PrintJobJson(59097,"PrintNode-Java","pdf_uri", "http://something","From PrintNode-Java Client");

		int response = aClient.createPrintJob(myPrintJobCreation);
	}

	@Test
	public void testAccounts() throws IOException{
		APIClient aClient = makeClient();

		CreateAccountJson accountInfo = new CreateAccountJson("A","Person","AnEmail@SomeEmailProvider.Email","AStrongPassword");

		accountInfo.setApiKeys(new String[]{"abc","def"});

		CreateAccountObject accountResponse = aClient.createAccount(accountInfo);

		String firstname = accountResponse.getAccount().getFirstname();

		APIClient childClient = makeClient();

		childClient.setChildAccountById(accountResponse.getAccount().getId());

		Account newAccountInfo = accountResponse.getAccount();

		newAccountInfo.setFirstname("The");

		newAccountInfo.setId(-1);

		Whoami ModifiedAccount = childClient.modifyAccount(newAccountInfo);

		assertTrue("Failed: account was not modified",!(ModifiedAccount.getFirstname().equals(firstname)));

		boolean deleteAccount = childClient.deleteAccount();

		assertTrue("Failed: account was not deleted, so account did not exist",deleteAccount);
	}


	@Test
	public void testTagsFunctionality() throws IOException{
		APIClient aClient = makeClient();

		String tag = aClient.createTag("AnAmazingTag","AGreatValue");

		assertTrue("Failed: should equal created.",tag.equals("created") || tag.equals("update"));

		String tagGotten = aClient.getTags("AnAmazingTag");

		assertTrue("Failed: Was not 'AGreatValue'",tagGotten.equals("AGreatValue"));

		boolean tagDelete = aClient.deleteTag("AnAmazingTag");

		assertTrue("Failed: was false or something else", tagDelete);
	}

	@Test
	public void testAPIKeyFunctionality() throws IOException{
		APIClient aClient = makeClient();

		CreateAccountJson accountInfo = new CreateAccountJson("A","Person","AnEmail@SomeEmailProvider.Email","AStrongPassword");

		CreateAccountObject accountResponse = aClient.createAccount(accountInfo);

		APIClient childClient = makeClient();

		childClient.setChildAccountById(accountResponse.getAccount().getId());

		String apiKey = childClient.createApiKey("Development");

		String apiKeyGotten = childClient.getApiKeys("Development");

		assertTrue("Failed: gotten apikey did not equal apikey created",apiKeyGotten.equals(apiKey));

		boolean apiKeyDelete = childClient.deleteApiKey("Development");

		assertTrue("Failed: was false or something else", apiKeyDelete);

	}

	@Test
	public void testGetLatestClient() throws IOException{
		APIClient aClient = makeClient();

		Download latestClient = aClient.getLatestClient("osx");

		assertTrue("Failed: was not osx",latestClient.getOs().equals("osx"));
	}

	@Test
	public void testClients() throws IOException{
		APIClient aClient = makeClient();

		Client[] clients = aClient.getClients("10-15");

		int[] clientsDisabled = aClient.modifyClientDownloads(Integer.toString(clients[0].getId()), false);

		assertTrue("Failed: client was not modified",clientsDisabled[0] == clients[0].getId());
	}

}
