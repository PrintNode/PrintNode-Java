import java.io.*;
import com.printnode.api.*;

public class CreatingAnAccount{
	public static void main(String args[]) throws IOException{

		//Firstly, we'll create a new Auth object.
		Auth myAuth = new Auth();

		//We're going to set our credentials for future use with Auth.setApiKey.
		myAuth.setApiKey("MyApiKey");

		//We then make an APIClient, which saves our authorization to use with requests.
		APIClient aClient = new APIClient(myAuth);

		//First, we'll make a CreateAccountJson object to prepare our request.
		CreateAccountJson accountInfo = new CreateAccountJson("A","Person","Email@AnEmailProvider.com","AStrongPassword");

		//So, there are all the required variables to make an account. 
		//The rest are optional. We want some ApiKeys for our new account, so let's set some.
		accountInfo.setApiKeys(new String[]{"Development","Production"});

		//We're now ready to send the request, which we can do by using APIClient.createAccount().
		//We want to see the returned value, which is a CreateAccountObject.
		CreateAccountObject accountObject = aClient.createAccount(accountInfo);

		//Let's authenticate as this account via it's id.
		APIClient childClient = new APIClient(myAuth);
		childClient.setChildAccountById(accountObject.getId());

	}
}
