package com.printnode.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Object for a Whoami.
 * */
public class Scale {

    /**
     * response body that made this Whoami.
     * */
    private String json;

    /**
     * The id of this account.
     * */
    private int[] mass = new int[2];
    /**
     * The firstname of this account.
     * */
    private String deviceName;
    /**
     * The lastname of the authenticated account.
     * */
    private int deviceNum;
    /**
     * Whether this account can create sub accounts or not.
     * */
    private String port;
    /**
     * The email registered to this account.
     * */
    private int count;
    /**
     * The email of the creator of this account.
     * */
    private HashMap<String, Integer> measurement;
    /**
     * The reference the creator set to this account.
     * */
    private String clientReportedCreateTimestamp;
    /**
     * An array of child account ids.
     * */
    private int ntpOffset;
    /**
     * The amount of credits this account has.
     * */
    private int ageOfData;
    /**
     * The amount of computers this account has.
     * */
    private int computerId;
    /**
     * The total prints this account has done.
     * */
    private String vendor;
    /**
     * The versions this account uses.
     * */
    private String product;
    /**
     * The accounts connected via this account.
     * */
    private int vendorId;
    /**
     * The tags associated with this account.
     * */
    private int productId;


    /**
     * Parses a JsonObject into Whoami.
     * Firstly, it begins iterating over the object.
     * If the object we are converting is a solo JsonPrimitive,
     * we map it directly to the variable.
     * If the object we are converting is an array of JsonPrimitives,
     * we firstly create an array of the same size as it.
     * Then, iterate over it.
     * If the object is a JsonObject with the same mappings each time, it is mapped to a HashMap.
     * If the object is a JsonObject with different mappings, it is mapped to a Java Object.
     * @param response JsonObject of the response.
     * @see JsonObject
     * @see JsonArray
     * @see com.google.gson.JsonPrimitive
     * @see JsonElement
     * */
    public Scale(final JsonObject response) {
        if (!response.get("mass").isJsonNull()) {
            JsonArray jsonMass = response.get("mass").getAsJsonArray();
            for (int i = 0; i < jsonMass.size(); i++) {
                mass[i] = jsonMass.get(i).isJsonNull() ? -1 : jsonMass.get(i).getAsInt();
            }
        }
        if (!response.get("deviceName").isJsonNull()) {
            deviceName = response.get("deviceName").getAsString();
        }
        if (!(response.get("deviceNum").isJsonNull())) {
            deviceNum = response.get("deviceNum").getAsInt();
        }
        if (!response.get("port").isJsonNull()) {
            port = response.get("port").getAsString();
        }
        if (!response.get("count").isJsonNull()) {
            count = response.get("count").getAsInt();
        }
        if (!response.get("measurement").isJsonNull()) {
            JsonObject jsonMeasurement = response.get("measurement").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> measurementEntries = jsonMeasurement.entrySet();
            measurement = new HashMap<String, Integer>();
            for (Map.Entry<String, JsonElement> measurementEntry : measurementEntries) {
                int jsonMeasurementValue = measurementEntry.getValue().getAsInt();
                measurement.put(measurementEntry.getKey(), jsonMeasurementValue);
            }
        }
        if (!response.get("clientReportedCreateTimestamp").isJsonNull()) {
            clientReportedCreateTimestamp = response.get("clientReportedCreateTimestamp").getAsString();
        }
        if (!response.get("ntpOffset").isJsonNull()) {
            ntpOffset = response.get("ntpOffset").getAsInt();
        }
        if (!response.get("ageOfData").isJsonNull()) {
            ageOfData = response.get("ageOfData").getAsInt();
        }
        if (!response.get("computerId").isJsonNull()) {
            computerId = response.get("computerId").getAsInt();
        }
        if (!response.get("vendor").isJsonNull()) {
            vendor = response.get("vendor").getAsString();
        }
        if (!response.get("product").isJsonNull()) {
            product = response.get("product").getAsString();
        }
        if (!response.get("vendorId").isJsonNull()) {
            vendorId = response.get("vendorId").getAsInt();
        }
        if (!response.get("productId").isJsonNull()) {
            productId = response.get("productId").getAsInt();
        }
        json = response.toString();
    }

    /**
     * @return id of this account.
     * */
    public final int[] getMass() {
        return mass;
    }

    /**
     * @return first name of the account holder.
     * */
    public final String getDeviceName() {
        return deviceName;
    }

    /**
     * @return last name of the account holder.
     * */
    public final int getDeviceNum() {
        return deviceNum;
    }

    /**
     * @return whether this account can make sub accounts (i.e is a parent account.)
     * */
    public final String getPort() {
        return port;
    }

    /**
     * @return the email for this account.
     * */
    public final int getCount() {
        return count;
    }

    /**
     * @return the email of the creator of this account.
     * */
    public final HashMap<String, Integer> getMeasurement() {
        return measurement;
    }

    /**
     * @return creator reference for this account.
     * */
    public final String getClientReportedCreateTimestamp() {
        return clientReportedCreateTimestamp;
    }

    /**
     * @return array of child account ids for this account.
     * */
    public final int getNtpOffset() {
        return ntpOffset;
    }

    /**
     * @return number of printing credits.
     * */
    public final int getAgeOfData() {
        return ageOfData;
    }

    /**
     * @return Number of computers used by this account.
     * */
    public final int getComputerId() {
        return computerId;
    }

    /**
     * @return total prints done by this account.
     * */
    public final String getVendor() {
        return vendor;
    }

    /**
     * @return an array of client versions used for this account.
     * */
    public final String getProduct() {
        return product;
    }

    /**
     * @return an array of connected clients.
     * */
    public final int getVendorId() {
        return vendorId;
    }

    /**
     * @return a hashamp of the tags this account has.
     * */
    public final int getProductId() {
        return productId;
    }

    /**
     * @return the original response string.
     * */
    public final String toString() {
        return json;
    }

}
