package com.printnode.api;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Object for latest downloadable clients.
 * */
public class Download implements Serializable {

    /**
     * The response body that made this Download.
     * */
    private String json;

    /**
     * The edition of this Download client.
     * */
    private String edition;
    /**
     * The version of this Download client.
     * */
    private String version;
    /**
     * The OS of this Download client.
     * */
    private String os;
    /**
     * The filename of this Download client.
     * */
    private String filename;
    /**
     * The filesize of this Download client.
     * */
    private String filesize;
    /**
     * The sha1 hash of this Download client.
     * */
    private String sha1;
    /**
     * The date this Download client was released.
     * */
    private String releaseTimestamp;
    /**
     * The url where this can be downloaded.
     * */
    private String url;


    /**
     * Parses a JsonObject into Download.
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

    public Download(final JsonObject response) {
        if (!response.get("version").isJsonNull()) {
            version = response.get("version").getAsString();
        }
        if (!response.get("edition").isJsonNull()) {
            edition = response.get("edition").getAsString();
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
     * @return the original response string.
     * */
    public final String toString() {
        return json;
    }
}
