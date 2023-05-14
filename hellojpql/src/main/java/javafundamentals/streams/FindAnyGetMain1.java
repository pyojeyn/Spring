package javafundamentals.streams;

import javafundamentals.dto.UserInfoResponseDto;

import java.math.BigInteger;
import java.util.List;

public class FindAnyGetMain1 {
    public static void main(String[] args) {

        UserInfoResponseDto danial = UserInfoResponseDto.builder()
                .name("다니엘")
                .mobileNumber("01011111111")
                .isRepresentative(true)
                .id(new BigInteger("1"))
                .nickName("공주")
                .build();

        UserInfoResponseDto mingee = UserInfoResponseDto.builder()
                .name("민지")
                .isRepresentative(true)
                .id(new BigInteger("2"))
                .nickName("민쥐")
                .mobileNumber("01022222222")
                .build();

        UserInfoResponseDto hearin = UserInfoResponseDto.builder()
                .name("해린")
                .isRepresentative(false)
                .id(new BigInteger("3"))
                .nickName("고양이")
                .mobileNumber("01033333333")
                .build();

        UserInfoResponseDto hani = UserInfoResponseDto.builder()
                .name("하니")
                .isRepresentative(false)
                .id(new BigInteger("4"))
                .nickName("귀요미")
                .mobileNumber("01044444444")
                .build();

        UserInfoResponseDto hyein = UserInfoResponseDto.builder()
                .name("혜인")
                .isRepresentative(false)
                .id(new BigInteger("5"))
                .nickName("막내")
                .mobileNumber("01055555555")
                .build();


        List<UserInfoResponseDto> girls = List.of(danial, mingee, hani, hyein, hearin);

        UserInfoResponseDto representativeGirl = girls.stream().filter(gs -> gs.getIsRepresentative())
                .findAny().get();

        System.out.println("대표 한명은 누구 ? " + representativeGirl.getName());


        /**
         * findAny() 는 stream 에서 임의의 요소를 반환하는 메소드.
         * 이 메소드는 병렬 스트림이든 순차 스트림이든 상관없이 스트림에서 첫번째로 발견되는 요소를 반환
         * 해당 요소를 Optional 객체에 감싸서 반환한다.
         * Optional 은 요소가 존재하는지 여부를 안전하기 처리하기 위한 컨테이너 객체이다.
         * findAny() 메소드는 주로 stream  에서 어떤 요소를 찾아야 하는지 중요하지 않을 때 사용된다.
         * 요소의 순서나 특정 조건에 대한 제약이 없고, 단순히 어떤 요소를 찾고자 할 때 사용할 수 있다.
         */
    }
}
