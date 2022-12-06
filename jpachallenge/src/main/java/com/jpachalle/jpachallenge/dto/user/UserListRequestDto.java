package com.jpachalle.jpachallenge.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UserListRequestDto {

    private String startDate;

    private String endDate;

    private String emailAddress;

    private String name;
}
