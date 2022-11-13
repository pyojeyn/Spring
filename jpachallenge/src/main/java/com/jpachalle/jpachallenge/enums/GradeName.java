package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

@Getter
public enum GradeName {
    BRONZE("브론즈회원"), GOLD("골드회원");

    private String label;

    public static GradeName from(String val) {
        for (GradeName type : GradeName.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }

    GradeName(String label) {
        this.label = label;
    }
}
