# PrintNode-Java

PrintNode is a cloud printing service which allows you to connect any printer to your application using our PrintNode Client and easy to use JSON API.

This quick start guide covers using the Java API Library. There are examples to show how to use the API Library. It assumes that you have a [PrintNode](https://www.printnode.com) account.

## Quick Start

Firstly, you will want to import `java.io.IOException`, as a lot of the methods throw IOExceptions because of the `org.apache.http` packages throw them, and are used extensively throughout PrintNode-Java. A .jar file is included in PrintNode-Java/jar/.

Here is some sample code:

```Java
import java.io.IOException;
import com.printnode.api.*;

public class Example{
	public static void main(String args[] throws IOException { //To avoid try catches
		Auth myAuth = new Auth()
		myAuth.setApiKey("MyApiKey");

		APIClient myClient = new APIClient(myAuth);

		Computer[] myComputers = myClient.getComputers("");
	}
}
```

##Compiling from source

The included *compile.sh* will compile all the files and place them in PrintNode-Java/build/.

##Docs

A *javadoc* is included in PrintNode-Java/docs/.

