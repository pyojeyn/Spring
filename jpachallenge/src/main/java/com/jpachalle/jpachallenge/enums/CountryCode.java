package com.jpachalle.jpachallenge.enums;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum CountryCode {
    KR(Locale.KOREA.getCountry()), US(Locale.US.getCountry());

    private String label;

    public static CountryCode from(String val) {
        for (CountryCode type : CountryCode.values()) {
            if (type.getLabel().equals(val)) {
                return type;
            }
        }
        return null;
    }

    CountryCode(String label) {
        this.label = label;
    }
}
