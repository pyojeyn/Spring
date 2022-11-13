package com.jpachalle.jpachallenge.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserResponseDto {

    @Length(min = 1, max = 32)
    @NotBlank
    private String roleName;

    @Length(min = 5, max = 254)
    @NotBlank
    private String emailAddress;

    @Length(min = 10, max = 11)
    @NotBlank
    private String directInwardDialNumber;

    @Length(min = 10, max = 11)
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String multiFactorAuthenticationType;

    private String emailAgreedDateTime;

    private String smsAgreedDateTime;
}
