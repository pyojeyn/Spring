package javafundamentals;

import javafundamentals.dto.Girls;
import javafundamentals.dto.UserInfoResponseDto;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForAndForMain {
    public static void main(String[] args) {
        UserInfoResponseDto danial = UserInfoResponseDto.builder()
                .name("다니엘")
                .mobileNumber("01011111111")
                .isRepresentative(true)
                .id(new BigInteger("1"))
                .nickName("공주")
                .build();

        Girls yeri = Girls.builder().name("예리").build();


        UserInfoResponseDto.BoyFriend 돌쇠 = UserInfoResponseDto.BoyFriend.builder()
                .age(23)
                .name("돌쇠")
                .build();
        UserInfoResponseDto.BoyFriend 민호 = UserInfoResponseDto.BoyFriend.builder()
                .age(23)
                .name("민호")
                .build();
        UserInfoResponseDto.BoyFriend 침착 = UserInfoResponseDto.BoyFriend.builder()
                .age(29)
                .name("침착")
                .build();
        UserInfoResponseDto.BoyFriend 강호 = UserInfoResponseDto.BoyFriend.builder()
                .age(24)
                .name("강호")
                .build();
        UserInfoResponseDto.BoyFriend 삼식 = UserInfoResponseDto.BoyFriend.builder()
                .age(25)
                .name("삼식")
                .build();

        Girls.BoyFriend 지수 = Girls.BoyFriend.builder().name("침착").age(24).build();
        Girls.BoyFriend 지훈 = Girls.BoyFriend.builder().name("강호").age(22).build();
        Girls.BoyFriend 강훈 = Girls.BoyFriend.builder().name("삼식").age(21).build();
        Girls.BoyFriend 민수 = Girls.BoyFriend.builder().name("민호").age(21).build();
        Girls.BoyFriend 강수 = Girls.BoyFriend.builder().name("돌쇠").age(21).build();

        List<UserInfoResponseDto.BoyFriend> 남자친구들 = List.of(돌쇠, 민호, 침착, 강호, 삼식);
        List<Girls.BoyFriend> 걸스남자친구들 = List.of(지수, 지훈, 강훈, 민수, 강수);
        danial.setBoyFriends(남자친구들);
        yeri.setBoyFriends(걸스남자친구들);

        // stream 으로 걸렀을 때
//        List<Girls.BoyFriend> 나이같은애들 =  danial.getBoyFriends().stream().map((ub) -> {
//            System.out.println("userInfo  "+ub.getName());
//            Girls.BoyFriend 걸스남자친구들ㅎ = yeri.getBoyFriends().stream()
//                    .filter((gb) ->gb.getName().equals(ub.getName()))
//                    .findAny().orElseThrow();
////                    .findAny().orElse(Girls.BoyFriend.builder().name("이름없음").build());
//            return 걸스남자친구들ㅎ;
//        }).collect(Collectors.toList());

        List<Girls.BoyFriend> 나이같은애들 = new ArrayList<>();
        List<UserInfoResponseDto.BoyFriend> usersBF = danial.getBoyFriends();
        List<Girls.BoyFriend> girlBF = yeri.getBoyFriends();
        for (UserInfoResponseDto.BoyFriend boyFriend : usersBF) {

            System.out.println("user.name " + boyFriend.getName());
            String name = boyFriend.getName();

            Girls.BoyFriend gBF = null;

            for (Girls.BoyFriend friend : girlBF) {
                System.out.println("girlBF.name " + friend.getName());
                if(name.equals(friend.getName())){
                    gBF = friend;
                    나이같은애들.add(gBF);
                    break;
                }
            }
        }


        for (Girls.BoyFriend boyFriend : 나이같은애들) {
            if(boyFriend != null){
                System.out.println("=================================");
                System.out.println(boyFriend.getName());
            }

        }
    }


}
