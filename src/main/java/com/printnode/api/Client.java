package com.printnode.api;

import com.google.gson.JsonObject;
/**
 * Object for a client.
 * */
public class Client {

    /**
     * The body of the response that made this client.
     * */
    private String json;

    /**
     * The id of the client.
     * */
    private int id;
    /**
     * Whether the client is enabled or not.
     * */
    private boolean enabled;
    /**
     * What edition this client is.
     * */
    private String edition;
    /**
     * The version of the client.
     * */
    private String version;
    /**
     * The OS of the client.
     * */
    private String os;
    /**
     * The filename of the client.
     * */
    private String filename;
    /**
     * The filesize of the client.
     * */
    private String filesize;
    /**
     * The sha1 hash of the client.
     * */
    private String sha1;
    /**
     * The date this client was released.
     * */
    private String releaseTimestamp;
    /**
     * The URL where the client can be downloaded.
     * */
    private String url;

    /**
     * Parses a JsonObject into Client.<br>
     * <p>Firstly, it begins iterating over the object.
     * If the object we are converting is a solo JsonPrimitive, we map it directly to the variable.
     * If the object we are converting is an array of JsonPrimitives,
     * we firstly create an array of the same size as it.
     * Then, iterate over it.
     * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
     * If the object is a JsonObject with different mappings, it is mapped to a Java Object.</p>
     * @param response JsonObject of the response.
     * @see JsonObject
     * @see com.google.gson.JsonArray
     * @see com.google.gson.JsonPrimitive
     * @see com.google.gson.JsonElement
     * */

    public Client(final JsonObject response) {
        if (!response.get("id").isJsonNull()) {
            id = response.get("id").getAsInt();
        }
        if (!response.get("version").isJsonNull()) {
            version = response.get("version").getAsString();
        }
        if (!response.get("enabled").isJsonNull()) {
            enabled = response.get("enabled").getAsBoolean();
        }
        if (!response.get("edition").isJsonNull()) {
            edition = response.get("edition").getAsString();
        }
        if (!response.get("os").isJsonNull()) {
            os = response.get("os").getAsString();
        }
        if (!response.get("filename").isJsonNull()) {
            filename = response.get("filename").getAsString();
        }
        if (!response.get("filesize").isJsonNull()) {
            filesize = response.get("filesize").getAsString();
        }
        if (!response.get("sha1").isJsonNull()) {
            sha1 = response.get("sha1").getAsString();
        }
        if (!response.get("releaseTimestamp").isJsonNull()) {
            releaseTimestamp = response.get("releaseTimestamp").getAsString();
        }
        if (!response.get("url").isJsonNull()) {
            url = response.get("url").getAsString();
        }
        json = response.toString();
    }


    /**
     * @return id of client.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return whether the client is enabled for download.
     * */
    public final boolean getEnabled() {
        return enabled;
    }

    /**
     * @return version of the client.
     * */
    public final String getVersion() {
        return version;
    }

    /**
     * @return Edition of the client.
     * */
    public final String getEdition() {
        return edition;
    }

    /**
     * @return OS of the client.
     * */
    public final String getOs() {
        return os;
    }

    /**
     * @return File name of the client.
     * */
    public final String getFilename() {
        return filename;
    }

    /**
     * @return File size of the client.
     * */
    public final String getFilesize() {
        return filesize;
    }

    /**
     * @return Sha1 hash of the client.
     * */
    public final String getSha1() {
        return sha1;
    }

    /**
     * @return Release timestamp of the client.
     * */
    public final String getReleaseTimestamp() {
        return releaseTimestamp;
    }

    /**
     * @return URL where the client can be downloaded from.
     * */
    public final String getUrl() {
        return url;
    }

    /**
     * @return the response body if called tostring is called.
     * */
    public final String toString() {
        return json;
    }

}
