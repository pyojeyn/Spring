package javafundamentals.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Girls {
    private List<BoyFriend> boyFriends;
    private String name;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class BoyFriend{
        private Integer age;
        private String name;
    }
}
