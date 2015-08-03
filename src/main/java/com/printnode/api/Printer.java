package com.printnode.api;

import com.google.gson.JsonObject;

/**
 * Object for a printer.
 * */
public class Printer {

    /**
     * The response body that made this Printer.
     * */
    private String json;

    /**
     * The id of the printer.
     * */
    private int id;
    /**
     * The computer that relates to this printer.
     * */
    private Computer computer;
    /**
     * The name of the printer.
     * */
    private String name;
    /**
     * The description of the printer.
     * */
    private String description;
    /**
     * The capabilities of this printer.
     * */
    private Capabilities capabilities;
    /**
     * The default settings of this printer.
     * */
    private String defaults;
    /**
     * The date this printer was added to PrintNode.
     * */
    private String createTimestamp;
    /**
     * The current state of the printer.
     * */
    private String state;

    /**
     * Parses a JsonObject into Printer.
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

    public Printer(final JsonObject response) {
        if (!response.get("id").isJsonNull()) {
            id = response.get("id").getAsInt();
        }
        if (!response.get("computer").isJsonNull()) {
            computer = new Computer(response.get("computer").getAsJsonObject());
        }
        if (!response.get("description").isJsonNull()) {
            description = response.get("description").getAsString();
        }
        if (!response.get("capabilities").isJsonNull()) {
            capabilities = new Capabilities(response.get("capabilities").getAsJsonObject());
        }
        if (!response.get("default").isJsonNull()) {
            defaults = response.get("default").getAsString();
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
     * @return the id of the printer.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return the name of the printer.
     * */
    public final String getName() {
        return name;
    }

    /**
     * @return the computer attached to this printer.
     * @see Computer
     * */
    public final Computer getComputer() {
        return computer;
    }

    /**
     * @return the description of the printer.
     * */
    public final String getDescription() {
        return description;
    }

    /**
     * @return the capabilities object of the printer.
     * @see Capabilities
     * */
    public final Capabilities getCapabilities() {
        return capabilities;
    }

    /**
     * @return default settings for this printer.
     * */
    public final String getDefault() {
        return defaults;
    }

    /**
     * @return timestamp of when the printer was added to the account.
     * */
    public final String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @return current state of the printer.
     * */
    public final String getState() {
        return state;
    }

    /**
     * @return the original response string.
     * */
    public final String toString() {
        return json;
    }

}
