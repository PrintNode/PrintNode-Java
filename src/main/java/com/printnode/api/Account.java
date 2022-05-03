package com.printnode.api;


import java.io.Serializable;

/**
 * Account object.
 * When being used, will generally be created via the blank constructor.
 * The other constructor is for CreateAccountJson,
 * which requires firstname, lastname, email and password to be set.
 * */
public class Account implements Serializable {

    /**
     * Id of this account. Not set if made from CreateAccountJson.
     * */
    private int id = -1;
    /**
     * Firstname of this account.
     * */
    private String firstname;
    /**
     * Lastname of this account.
     * */
    private String lastname;
    /**
     * Email of this account.
     * */
    private String email;
    /**
     * Password for this account.
     * */
    private String password;
    /**
     * CreatorReference of this account.
     * */
    private String creatorRef;
    /**
     * Default constructor.
     * */
    public Account() {
    }

    /**
     * Constructor for CreateAccountJson. Should only be ran through there.
     *
     * @param newFirstname First name of account holder.
     * @param newLastname lastname of account holder.
     * @param newEmail email of account holder.
     * @param newPassword password of account holder.
     * */
    public Account(final String newFirstname,
            final String newLastname,
            final String newEmail,
            final String newPassword) {
        firstname = newFirstname;
        lastname = newLastname;
        email = newEmail;
        password = newPassword;
    }

    /**
     * @param newId id to be set.
     * */
    public final void setId(final int newId) {
        id = newId;
    }

    /**
     * @param newFirstname firstname to be set.
     * */
    public final void setFirstname(final String newFirstname) {
        firstname = newFirstname;
    }

    /**
     * @param newLastname lastname to be set.
     * */
    public final void setLastname(final String newLastname) {
        lastname = newLastname;
    }

    /**
     * @param newEmail email to be set.
     * */
    public final void setEmail(final String newEmail) {
        email = newEmail;
    }

    /**
     * @param newPassword password to be set.
     * */
    public final void setPassword(final String newPassword) {
        password = newPassword;
    }

    /**
     * @param newCreatorRef creator reference to be set.
     * */
    public final void setCreatorRef(final String newCreatorRef) {
        creatorRef = newCreatorRef;
    }

    /**
     * @return id of account.
     * */
    public final int getId() {
        return id;
    }

    /**
     * @return firstname of account.
     * */
    public final String getFirstname() {
        return firstname;
    }

    /**
     * @return lastname of account.
     * */
    public final String getLastname() {
        return lastname;
    }

    /**
     * @return email of account.
     * */
    public final String getEmail() {
        return email;
    }

    /**
     * @return password of account.
     * */
    public final String getPassword() {
        return password;
    }

    /**
     * @return creatorRef of account.
     * */
    public final String getCreatorRef() {
        return creatorRef;
    }

}
