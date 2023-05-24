package javafundamentals.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {

    private BigInteger id;

    private String name;

    private String nickName;

    private String mobileNumber;

    private String roleName;

    private String createdDateTime;

    private String emailAddress;

    private Boolean isRepresentative;

    private List<BoyFriend> boyFriends;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class BoyFriend{
        private Integer age;
        private String name;
    }

}
