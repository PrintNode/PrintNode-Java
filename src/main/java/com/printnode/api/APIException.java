package com.printnode.api;

import java.io.*;
import java.lang.*;

/**
 * Just a small Exception class for HTTP request based exceptions.
 * */
public class APIException extends RuntimeException {

	public APIException(String message) {
		super(message);
	}

}
