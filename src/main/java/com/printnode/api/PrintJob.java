package com.printnode.api;

import com.google.gson.JsonObject;

/**
 * Object for a PrintJob.
 * */
public class PrintJob {

    /**
     * The response body that made this PrintJob.
     * */
    private String json;

    /**
     * The ID of this PrintJob.
     * */
    private int id;
    /**
     * The Printer that relates to this PrintJob.
     * */
    private Printer printer;
    /**
     * The title of this PrintJob.
     * */
    private String title;
    /**
     * The Content type of this PrintJob.
     * */
    private String contentType;
    /**
     * The source of this PrintJob.
     * */
    private String source;
    /**
     * The time when this PrintJob will expire.
     * */
    private String expireAt;
    /**
     * The date of when this PrintJob was created.
     * */
    private String createTimestamp;
    /**
     * The state of this PrintJob.
     * */
    private String state;

    /**
     * Parses a JsonObject into PrintJob.
     * Firstly, it begins iterating over the object.
     * If the object we are converting is a solo JsonPrimitive, we map it directly to the variable.
     * If the object we are converting is an array of JsonPrimitives,
     * we firstly create an array of the same size as it.
     * Then, iterate over it.
     * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
     * If the object is a JsonObject with different mappings, it is mapped to a Java Object.
     * @param response JsonObject of the response.
     * @see JsonObject
     * @see com.google.gson.JsonArray
     * @see com.google.gson.JsonPrimitive
     * @see com.google.gson.JsonElement
     * */
    public PrintJob(final JsonObject response) {
        if (!response.get("id").isJsonNull()) {
            id = response.get("id").getAsInt();
        }
        if (!response.get("printer").isJsonNull()) {
            printer = new Printer(response.get("printer").getAsJsonObject());
        }
        if (!response.get("title").isJsonNull()) {
            title = response.get("title").getAsString();
        }
        if (!response.get("contentType").isJsonNull()) {
            contentType = response.get("contentType").toString();
        }
        if (!response.get("source").isJsonNull()) {
            source = response.get("source").getAsString();
        }
        if (!response.get("createTimestamp").isJsonNull()) {
            createTimestamp = response.get("createTimestamp").getAsString();
        }
        if (!response.get("expireAt").isJsonNull()) {
            expireAt = response.get("expireAt").getAsString();
        }
        if (!response.get("state").isJsonNull()) {
            state = response.get("state").getAsString();
        }
        json = response.toString();
    }

    /**
     * @return id of this PrintJob.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return Printer object relative to this PrintJob.
     * @see Printer
     * */
    public final Printer getPrinter() {
        return printer;
    }

    /**
     * @return Title of this PrintJob.
     * */
    public final String getTitle() {
        return title;
    }

    /**
     * @return content type of this PrintJob.
     * */
    public final String getContentType() {
        return contentType;
    }

    /**
     * @return source of the PrintJob.
     * */
    public final String getSource() {
        return source;
    }

    /**
     * @return timestamp of when the PrintJob was created.
     * */
    public final String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @return time after timestamp when it expires.
     * */
    public final String getExpireAt() {
        return expireAt;
    }

    /**
     * @return state of the PrintJob.
     * */
    public final String getState() {
        return state;
    }

    /**
     * @return original response string.
     * */
    public final String toString() {
        return json;
    }

}
