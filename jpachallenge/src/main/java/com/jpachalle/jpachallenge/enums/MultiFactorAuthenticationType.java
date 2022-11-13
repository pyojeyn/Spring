package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

@Getter
public enum MultiFactorAuthenticationType {
    PHONE("phone"), EMAIL("email");

    private String label;

    public static MultiFactorAuthenticationType from(String val) {
        for (MultiFactorAuthenticationType type : MultiFactorAuthenticationType.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }

    MultiFactorAuthenticationType(String label) {
        this.label = label;
    }
}
