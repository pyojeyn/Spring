package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

@Getter
public enum RoleName {
    NORMAL("일반사용자"), SILVER("실버사용자"), MIDDLE("중급사용자");

    private String label;

    public static RoleName from(String val) {
        for (RoleName type : RoleName.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }

    RoleName(String label) {
        this.label = label;
    }
}
