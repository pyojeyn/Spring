package jpql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class MemberDTO {

    private String username;
    private int age;

//    public MemberDTO(String username, int age) {
//        this.username = username;
//        this.age = age;
//    }
}
