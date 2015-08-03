package com.printnode.api;

import com.google.gson.JsonObject;

/**
 * Object for a computer.
 * */
public class Computer {

    /**
     * The respose body that made this computer.
     * */
    private String json;

    /**
     * The id of the computer.
     * */
    private int id;
    /**
     * The name of the computer.
     * */
    private String name = null;
    /**
     * The inet of the computer.
     * */
    private String inet = null;
    /**
     * The inet6 of the computer.
     * */
    private String inet6 = null;
    /**
     * The hostname of the computer.
     * */
    private String hostname = null;
    /**
     * the version of the client this computer is running.
     * */
    private String version = null;
    /**
     * The version of JRE this computer is running.
     * */
    private String jre = null;
    /**
     * The date this computer was added to PrintNode.
     * */
    private String createTimestamp = null;
    /**
     * The current state of the computer.
     * */
    private String state = null;


    /**
     * Parses a JsonObject into Computer.
     * Firstly, it begins iterating over the object.
     * If the object we are converting is a solo JsonPrimitive, we map it directly to the variable.
     * If the object we are converting is an array of JsonPrimitives,
     * we firstly create an array of the same size as it.
     * Then, iterate over it.
     * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
     * If the object is a JsonObject with different mappings, it is mapped to a Java Object.
     * @param response JsonObject of the response.
     * @see JsonObject
     * @see JsonArray
     * @see JsonPrimitive
     * @see JsonElement
     * */
    public Computer(final JsonObject response) {
        if (!response.get("id").isJsonNull()) {
            id = response.get("id").getAsInt();
        }
        if (!response.get("name").isJsonNull()) {
            name = response.get("name").getAsString();
        }
        if (!(response.get("inet").isJsonNull())) {
            inet = response.get("inet").getAsString();
        }
        if (!response.get("inet6").isJsonNull()) {
            inet6 = response.get("inet6").getAsString();
        }
        if (!response.get("hostname").isJsonNull()) {
            hostname = response.get("hostname").getAsString();
        }
        if (!response.get("version").isJsonNull()) {
            version = response.get("version").getAsString();
        }
        if (!response.get("jre").isJsonNull()) {
            jre = response.get("jre").getAsString();
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
     * @return id of the computer.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return name of the computer.
     * */
    public final String getName() {
        return name;
    }

    /**
     * @return Inet of the computer.
     * */
    public final String getInet() {
        return inet;
    }

    /**
     * @return Inet6 of the computer.
     * */
    public final String getInet6() {
        return inet6;
    }

    /**
     * @return the hostname of the computer.
     * */
    public final String getHostname() {
        return hostname;
    }

    /**
     * @return the client version the computer is running.
     * */
    public final String getVersion() {
        return version;
    }

    /**
     * @return the JRE of the computer.
     * */
    public final String getJre() {
        return jre;
    }

    /**
     * @return the timestamp of when the computer was added to the account.
     * */
    public final String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @return The state of the computer.
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
