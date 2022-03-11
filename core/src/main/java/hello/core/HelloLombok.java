package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hello");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("hellolombok.toString() = " + helloLombok);
    }
}

// 롬복 설치
// build.gradle 에 의존성 추가하고 Preferences -> Plugin -> lombok 검색 (이미 된경우는 안나와있어서 install 항목 클릭해서 확인)
// ** 중요 : Preferences -> Annotation Processors 검색 -> Enable annotation processing 체크 (재시작) 이거 무조건 해줘야됨!!!!!