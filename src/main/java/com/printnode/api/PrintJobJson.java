package com.printnode.api;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An object to be serialized into JSON for creating printjobs.
 * */
public class PrintJobJson {
    /**
     * The id of the printer we're printing on.
     * */
    private int printerId;
    /**
     * The title of the PrintJob.
     * */
    private String title;
    /**
     * The type of content we are using.
     * */
    private String contentType;
    /**
     * If content-type xxx_base64:
     * This is a path to the file you want to upload.
     * If content-type xxx_uri:
     * This is a URL to the file you want printed.
     * */
    private String content;
    /**
     * Source of the PrintJob.
     * */
    private String source;
    /**
     * Options to be set on this PrintJob.
     * Add these via PrintJobJson.getOptions.set(option)..
     * */
    private Options options;
    /**
     * Set time to expire on this PrintJob.
     * */
    private int expireAfter = -1;
    /**
     * How many times you want to send this PrintJob.
     * */
    private int qty = 1;

    /**
     * Creates an object to be serialized into JSON.
     * Requires a contentType - if contentType is base_64, it is encoded into base64.
     *
     * @param newPrinterId id of the printer which wil run the PrintJob.
     * @param newTitle title of the PrintJob.
     * @param newContentType Type of content. base64, uri, etc.
     * @param newContent either a file, or a URL to a file. Depends on contentType.
     * @param newSource Would be from the PrintNode-Java client.
     * @throws IOException if "xxx_base64" is selected as content-type
     * and the file specified in content does not exist.
     * */
    public PrintJobJson(final int newPrinterId,
            final String newTitle,
            final String newContentType,
            final String newContent,
            final String newSource) throws IOException {
        printerId = newPrinterId;
        title = newTitle;
        contentType = newContentType;
        if (contentType == "pdf_base64" || contentType == "raw_base64") {
            Path filePath = Paths.get(newContent);
            byte[] fileContent = Files.readAllBytes(filePath);
            content = new String(Base64.encodeBase64(fileContent));
        } else {
            content = newContent;
        }
        source = newSource;
        options = new Options();
    }

    /**
     * @return Options object of this PrintJob.
     * */
    public final Options getOptions() {
        return options;
    }

    /**
     * @param newExpireAfter set the timeout for this PrintJob.
     * */
    public final void setExpireAfter(final int newExpireAfter) {
        expireAfter = newExpireAfter;
    }

    /**
     * @param newQty set the number of times this PrintJob is sent to the queue.
     * */
    public final void setQty(final int newQty) {
        qty = newQty;
    }

}
