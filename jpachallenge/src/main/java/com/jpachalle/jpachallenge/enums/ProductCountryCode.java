package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

@Getter
public enum ProductCountryCode {
    KR("kr"), GU("gu");

    private String label;

    public static ProductCountryCode from(String val) {
        for (ProductCountryCode type : ProductCountryCode.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }


    ProductCountryCode(String label) {
        this.label = label;
    }
}
