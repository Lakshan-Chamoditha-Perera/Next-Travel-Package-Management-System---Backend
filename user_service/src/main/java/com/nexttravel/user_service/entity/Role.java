package com.nexttravel.user_service.entity;

public enum Role {

    ADMIN("admin"),
    USER("user"),
    VEHICLE_ADMIN("vehicle_admin");

    private final String string_val;

    Role(String string_val) {
        this.string_val = string_val;
    }

    public String getString() {
        return this.string_val;
    }
}
