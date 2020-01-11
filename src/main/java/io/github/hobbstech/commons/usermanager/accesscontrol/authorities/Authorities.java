package io.github.hobbstech.commons.usermanager.accesscontrol.authorities;

public enum Authorities {

    CREATE_USER("Create User"),
    UPDATE_USER("Update User"),
    DISABLE_USER("Disable User"),
    LOCK_USER("Lock User"),

    CREATE_GROUP_AUTHORITY("Can assign authority to group"),
    DELETE_GROUP_AUTHORITY("Can delete authority from group"),

    CREATE_GROUP_USER("Can assign group to a user"),

    CREATE_USER_AUTHORITY("Can assign authority to a user"),
    DELETE_USER_AUTHORITY("Can delete authority from user"),

    // Parameters

    DELETE_ETHNICITY("Can delete ethnicity"),
    CREATE_ETHNICITY("Can create ethnicity"),
    UPDATE_ETHNICITY("Can update ethnicity"),

    DELETE_NATIONALITY("Can delete nationality"),
    CREATE_NATIONALITY("Can create nationality"),
    UPDATE_NATIONALITY("Can update nationality"),

    DELETE_DEPARTMENT("Can delete department"),
    CREATE_DEPARTMENT("Can create department"),
    UPDATE_DEPARTMENT("Can update department"),

    DELETE_BANK("Can delete bank"),
    CREATE_BANK("Can create bank"),
    UPDATE_BANK("Can update bank");


    public final String description;

    Authorities(String description) {
        this.description = description;
    }
}
