package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

@Getter
public enum CommerceName {

    COMMERCENAME1("커머스 이름1"), COMMERCENAME2("커머스 이름2");

    private String label;

    public static CommerceName from(String val) {
        for (CommerceName type : CommerceName.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }

    CommerceName(String label) {
        this.label = label;
    }

}
