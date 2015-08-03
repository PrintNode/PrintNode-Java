package com.printnode.api;

/**
 * Auth class required for any APIClient creation.
 * Will throw an exception if neither ApiKey or EmailPassword are set.
 * */
public class Auth {

    /**
     * API-Key to authenticate with.
     * */
    private String apiKey;

    /**
     * Email to authenticate with. Implies password is also set.
     * */
    private String email;

    /**
     * Password to authenticate with. Implies email is also set.
     * */
    private String password;

    /**
     * Default constructor.
     * */
    public Auth() {
    }

    /**
     * Sets the ApiKey. will not be set if EmailPassword are already set.
     *
     * @param newApiKey an ApiKey.
     * @throws APIException if email is already set.
     * */
    public final void setApiKey(final String newApiKey) throws APIException {
        if (email == null) {
            apiKey = newApiKey;
        } else {
            throw new APIException("API Key was set when email and password have already been set.");
        }
    }

    /**
     * Sets an Email and a Password. Will not be set if ApiKey is set.
     *
     * @param newEmail email of account to be authenticated
     * @param newPassword email of password to be authenticated.
     * @throws APIException if apikey is already set.
     * */
    public final void setEmailPassword(final String newEmail, final String newPassword) throws APIException {
        if (this.apiKey == null) {
            email = newEmail;
            password = newPassword;
        } else {
            throw new APIException("Email and Password were set when API Key has already been set.");
        }
    }

    /**
     * Gives the credentials for the APIClient.
     * Depending on what is set, sets the credentials to the set values.
     * If none are set, throws an exception.
     *
     * @return Credentials for APIClient.
     * @throws APIException if no credentials are set.
     * @see APIClient
     * */
    public final String[] getCredentials() throws APIException {
        String[] credentials = new String[2];
        if (apiKey != null) {
            credentials[0] = apiKey;
            credentials[1] = "";
        } else if (email != null) {
            credentials[0] = email;
            credentials[1] = password;
        } else {
            throw new APIException("Attempted to run a request with no credentials set.");
        }
        return credentials;
    }


}
