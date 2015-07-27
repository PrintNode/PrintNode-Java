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

###Prerequisites

* org.apache.commons
* org.apache.http
* com.google.gson
* org.apache.commons-logging
Then place these files in lib/ or add them to your CLASSPATH.

The included *compile.sh* will then compile all the files and place them in PrintNode-Java/build/.

##Maven

###Prerequisites

* Change `/src/test/java/com/printnode/api/AppTest.java to use your own API-Key.

Running `maven install dependency:copy-dependencies package` in the base folder will then build a PrintNode-Java.jar into target/ and will put all the dependencies required in target/dependency/.

##Docs

A *javadoc* is included in PrintNode-Java/docs/.

