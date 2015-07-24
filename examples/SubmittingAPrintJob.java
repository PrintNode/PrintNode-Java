import java.io.*;
import com.printnode.api.*;

public class SubmittingAPrintJob{
	public static void main(String args[]) throws IOException{

		//Firstly, we'll create a new Auth object.
		Auth myAuth = new Auth();

		//We're going to set our credentials for future use with Auth.setApiKey.
		myAuth.setApiKey("MyApiKey");

		//We then make an APIClient, which saves our authorization to use with requests.
		APIClient aClient = new APIClient(myAuth);

		//If we're going to submit a printjob, we need a printer. We'll get one from our list of printers.
		Printer[] myPrinters = aClient.getPrinters("");
		Printer myPrinterForPrintJob = myPrinters[0];

		//We now need to create a PrintJobJson object so we can put use it in a request.
		//This takes 5 arguments:
		//printerId,title,contentType,content and source.
		PrintJobJson myPrintJobCreation = new PrintJobJson(myPrinterForPrintJob.getId(),"PrintNode-Java","pdf_uri","http://a.test.pdf","From PrintNode-Java");

		//We'll then make the request by using APIClient.createPrintJob
		int myPrintJobId = aClient.createPrintJob(myPrintJobCreation);

		//We can then look at the information of the PrintJob by using the id we just got on creation
		PrintJob myPrintJob = aClient.getPrintJobs(Integer.toString(myPrintJobId))[0];
	}
}
