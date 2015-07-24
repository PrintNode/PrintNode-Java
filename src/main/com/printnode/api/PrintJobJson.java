package com.printnode.api;

import java.io.*;
import com.printnode.api.Options;
import org.apache.commons.codec.binary.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An object to be serialized into JSON for creating printjobs.
 * */
public class PrintJobJson {
	private int printerId;
	private String title;
	private String contentType;
	private String content;
	private String source;
	private Options options;
	private int expireAfter = -1;
	private int qty = -1;

	/**
	 * Creates an object to be serialized into JSON.
	 * Requires a contentType - if contentType is base_64, it is encoded into base64.
	 *
	 * @param printerId id of the printer which wil run the PrintJob.
	 * @param title title of the printjob.
	 * @param contentType Type of content. base64, uri, etc.
	 * @param content either a file, or a URL to a file. Depends on contentType.
	 * @param source Would be from the PrintNode-Java client.
	 * */
	public PrintJobJson(int printerId, String title, String contentType, String content, String source) throws IOException{
		this.printerId = printerId;
		this.title = title;
		this.contentType = contentType;
		if(contentType == "pdf_base64" || contentType == "raw_base64"){
			Path filePath = Paths.get(content);
			byte[] fileContent = Files.readAllBytes(filePath);
			this.content = new String(Base64.encodeBase64(fileContent));
		}else{
			this.content = content;
		}
		this.source = source;
		this.options = new Options();
	}

	/**
	 * @return Options object of this printjob.
	 * */
	public Options getOptions(){
		return options;
	}

	/**
	 * @param expireAfter set the timeout for this printjob.
	 * */
	public void setExpireAfter(int expireAfter){
		this.expireAfter = expireAfter;
	}

	/**
	 * @param qty set the number of times this printjob is sent to the queue.
	 * */
	public void setQty(int qty){
		this.qty = qty;
	}

}
