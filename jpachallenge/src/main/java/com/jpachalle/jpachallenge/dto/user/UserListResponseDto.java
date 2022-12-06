package com.jpachalle.jpachallenge.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserListResponseDto {

    private BigInteger id;

    private String name;

    private Timestamp createDateTime;

    private String emailAddress;

    private String mobileNumber;

}
