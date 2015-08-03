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
public class Whoami {

    /**
     * response body that made this Whoami.
     * */
    private String json;

    /**
     * The id of this account.
     * */
    private int id;
    /**
     * The firstname of this account.
     * */
    private String firstname;
    /**
     * The lastname of the authenticated account.
     * */
    private String lastname;
    /**
     * Whether this account can create sub accounts or not.
     * */
    private boolean canCreateSubAccounts;
    /**
     * The email registered to this account.
     * */
    private String email;
    /**
     * The email of the creator of this account.
     * */
    private String creatorEmail;
    /**
     * The reference the creator set to this account.
     * */
    private String creatorRef;
    /**
     * An array of child account ids.
     * */
    private int[] childAccounts;
    /**
     * The amount of credits this account has.
     * */
    private int credits;
    /**
     * The amount of computers this account has.
     * */
    private int numComputers;
    /**
     * The total prints this account has done.
     * */
    private int totalPrints;
    /**
     * The versions this account uses.
     * */
    private String[] versions;
    /**
     * The accounts connected via this account.
     * */
    private String[] connected;
    /**
     * The tags associated with this account.
     * */
    private HashMap<String, String> tags;
    /**
     * The state of this account.
     * */
    private String state;
    /**
     * The permissions this account has.
     * */
    private String[] permissions;


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
     * @see JsonPrimitive
     * @see JsonElement
     * */
    public Whoami(final JsonObject response) {
        if (!response.get("id").isJsonNull()) {
            id = response.get("id").getAsInt();
        }
        if (!response.get("firstname").isJsonNull()) {
            firstname = response.get("firstname").getAsString();
        }
        if (!(response.get("lastname").isJsonNull())) {
            lastname = response.get("lastname").getAsString();
        }
        if (!response.get("canCreateSubAccounts").isJsonNull()) {
            canCreateSubAccounts = response.get("canCreateSubAccounts").getAsBoolean();
        }
        if (!response.get("email").isJsonNull()) {
            email = response.get("email").getAsString();
        }
        if (!response.get("creatorEmail").isJsonNull()) {
            creatorEmail = response.get("creatorEmail").getAsString();
        }
        if (!response.get("creatorRef").isJsonNull()) {
            creatorRef = response.get("creatorRef").getAsString();
        }
        if (!response.get("childAccounts").isJsonNull()) {
            JsonArray jsonChildAccounts = response.get("childAccounts").getAsJsonArray();
            childAccounts = new int[jsonChildAccounts.size()];
            for (int i = 0; i < jsonChildAccounts.size(); i++) {
                childAccounts[i] = jsonChildAccounts.get(i).getAsInt();
            }
        }
        if (!response.get("credits").isJsonNull()) {
            credits = response.get("credits").getAsInt();
        }
        if (!response.get("numComputers").isJsonNull()) {
            numComputers = response.get("numComputers").getAsInt();
        }
        if (!response.get("totalPrints").isJsonNull()) {
            totalPrints = response.get("totalPrints").getAsInt();
        }
        if (!response.get("versions").isJsonNull()) {
            JsonArray jsonVersions = response.get("versions").getAsJsonArray();
            versions = new String[jsonVersions.size()];
            for (int i = 0; i < jsonVersions.size(); i++) {
                versions[i] = jsonVersions.get(i).getAsString();
            }
        }
        if (!response.get("connected").isJsonNull()) {
            JsonArray jsonConnected = response.get("connected").getAsJsonArray();
            connected = new String[jsonConnected.size()];
            for (int i = 0; i < jsonConnected.size(); i++) {
                connected[i] = jsonConnected.get(i).getAsString();
            }
        }
        if (!response.get("Tags").isJsonNull() && !response.get("Tags").isJsonArray()) {

            JsonObject jsonTags = response.get("Tags").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> tagsEntries = jsonTags.entrySet();
            tags = new HashMap<String, String>();
            for (Map.Entry<String, JsonElement> tagEntry : tagsEntries) {
                String jsonTagValue = tagEntry.getValue().getAsString();
                tags.put(tagEntry.getKey(), jsonTagValue);
            }
        }
        if (!response.get("state").isJsonNull()) {
            state = response.get("state").getAsString();
        }
        if (response.has("permissions") && !response.get("permissions").isJsonNull()) {
            JsonArray jsonPermissions = response.get("permissions").getAsJsonArray();
            permissions = new String[jsonPermissions.size()];
            for (int i = 0; i < jsonPermissions.size(); i++) {
                permissions[i] = jsonPermissions.get(i).getAsString();
            }
        }
        json = response.toString();
    }

    /**
     * @return id of this account.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return first name of the account holder.
     * */
    public final String getFirstname() {
        return firstname;
    }

    /**
     * @return last name of the account holder.
     * */
    public final String getLastname() {
        return lastname;
    }

    /**
     * @return whether this account can make sub accounts (i.e is a parent account.)
     * */
    public final boolean getCanCreateSubAccounts() {
        return canCreateSubAccounts;
    }

    /**
     * @return the email for this account.
     * */
    public final String getEmail() {
        return email;
    }

    /**
     * @return the email of the creator of this account.
     * */
    public final String getCreatorEmail() {
        return creatorEmail;
    }

    /**
     * @return creator reference for this account.
     * */
    public final String getCreatorRef() {
        return creatorRef;
    }

    /**
     * @return array of child account ids for this account.
     * */
    public final int[] getChildAccounts() {
        return childAccounts;
    }

    /**
     * @return number of printing credits.
     * */
    public final int getCredits() {
        return credits;
    }

    /**
     * @return Number of computers used by this account.
     * */
    public final int getNumComputers() {
        return numComputers;
    }

    /**
     * @return total prints done by this account.
     * */
    public final int getTotalPrints() {
        return totalPrints;
    }

    /**
     * @return an array of client versions used for this account.
     * */
    public final String[] getVersions() {
        return versions;
    }

    /**
     * @return an array of connected clients.
     * */
    public final String[] getConnected() {
        return connected;
    }

    /**
     * @return a hashamp of the tags this account has.
     * */
    public final HashMap<String, String> getTags() {
        return tags;
    }

    /**
     * @return the state of this account.
     * */
    public final String getState() {
        return state;
    }

    /**
     * @return an array of permissions for this account.
     * */
    public final String[] getPermissions() {
        return permissions;
    }

    /**
     * @return the original response string.
     * */
    public final String toString() {
        return json;
    }

}
