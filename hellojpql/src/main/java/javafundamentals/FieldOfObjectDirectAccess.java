package javafundamentals;

import javafundamentals.dto.UserInfoResponseDto;

import java.math.BigInteger;

public class FieldOfObjectDirectAccess {

    public static void main(String[] args) {
        UserInfoResponseDto testUser = UserInfoResponseDto.builder().id(new BigInteger("1"))
                .name("김아무개")
                .nickName("KIM")
                .roleName("후")
                .emailAddress("test@test.com")
                .build();


        String hisName = testUser.name;
        String hisNickName = testUser.nickName;
        String hisNumber = testUser.mobileNumber;

        System.out.println("너의 이름은 = " + hisName);
        System.out.println("너의 닉네임은 = " + hisNickName);



    }
}
