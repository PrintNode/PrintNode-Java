package com.printnode.api;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Object for a State.
 * */
public class State implements Serializable {

    /**
     * The response body that made this State.
     * */
    private String json;

    /**
     * The ID of the PrintJob relative to this State.
     * */
    private int printJobId;
    /**
     * The state of this PrintJob.
     * */
    private String state;
    /**
     * A Human-Readable message about this PrintJob.
     * */
    private String message;
    /**
     * The version of the client for this PrintJob.
     * */
    private String clientVersion;
    /**
     * How old the PrintJob is.
     * */
    private int age;
    /**
     * When the PrintJob was created.
     * */
    private String createTimestamp;


    /**
     * Parses a JsonObject into State.
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
    public State(final JsonObject response) {
        if (!response.get("printJobId").isJsonNull()) {
            printJobId = response.get("printJobId").getAsInt();
        }
        if (!(response.get("message").isJsonNull())) {
            message = response.get("message").getAsString();
        }
        if (!response.get("clientVersion").isJsonNull()) {
            clientVersion = response.get("clientVersion").getAsString();
        }
        if (!response.get("age").isJsonNull()) {
            age = response.get("age").getAsInt();
        }
        if (!response.get("createTimestamp").isJsonNull()) {
            createTimestamp = response.get("createTimestamp").getAsString();
        }
        if (!response.get("state").isJsonNull()) {
            state = response.get("state").getAsString();
        }
        json = response.toString();
    }

    /**
     * @return id of the printjob this state is for.
     * */
    public final int getPrintJobId() {
        return printJobId;
    }

    /**
     * @return human-readable information about this state.
     * */
    public final String getMessage() {
        return message;
    }

    /**
     * @return client version the printjob was made from.
     * */
    public final String getClientVersion() {
        return clientVersion;
    }

    /**
     * @return age of printjob.
     * */
    public final int getAge() {
        return age;
    }

    /**
     * @return the timestamp of when the printjob was created.
     * */
    public final String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @return the state of the printjob.
     * */
    public final String getState() {
        return state;
    }

    /**
     * @return The original response string.
     * */
    public final String toString() {
        return json;
    }

}
