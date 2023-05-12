package javafundamentals.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class UserInfoResponseDto {

    private BigInteger id;

    private String name;

    private String nickName;

    private String mobileNumber;

    private String roleName;

    private String createdDateTime;

    private String emailAddress;
}
