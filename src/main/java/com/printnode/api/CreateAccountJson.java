package com.printnode.api;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

/**
 * Object that serializes an object for JSON in a CreateAccount request.
 * */
public class CreateAccountJson {


    /**
     * Account object for this account creation.
     * This keeps it in the same form as the requests on the API docs.
     * */
    @SerializedName("Account") private Account account;

    /**
     * An array of descriptions for new API-Keys.
     * */
    @SerializedName("ApiKeys") private String[] apiKeys;

    /**
     * A HashMap of tags to add.
     * */
    @SerializedName("Tags") private HashMap<String, String> tags = new HashMap<String, String>();

    /**
     * For account creation, firstname, lastname, email and password are all required to be set.
     * The rest of them can be set via setters.
     *
     * @param firstname First name of new account holder.
     * @param lastname Last name of new account holder.
     * @param email Email of the new account holder.
     * @param password password of the new account.
     * */
    public CreateAccountJson(final String firstname,
            final String lastname,
            final String email,
            final String password) {
        account = new Account(firstname, lastname, email, password);
    }

    /**
     * @return Account object of the account to be created.
     * @see Account
     * */
    public final Account getAccount() {
        return account;
    }

    /**
     * @param tagname name of tag to be added.
     * @param tagvalue value of tag to be added.
     * */
    public final void addTag(final String tagname, final String tagvalue) {
        tags.put(tagname, tagvalue);
    }

    /**
     * @param newApiKeys Name of apikey descriptions to be added.
     * */
    public final void setApiKeys(final String[] newApiKeys) {
        apiKeys = newApiKeys;
    }

}
