package javafundamentals;

import java.util.HashMap;
import java.util.Map;

public class GetOrDefaultMain {
    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Jane", 100);
        scores.put("Mike", 82);

        int scoreOfJane = scores.getOrDefault("Jane", 0); // 100
        int scoreOfMike = scores.getOrDefault("Mike", 0); // 82
//        int scoreOfMerry = scores.get("Merry"); // NullPointerException
        int scoreOfMerry = scores.getOrDefault("Merry", 0); // 0

        System.out.println("scoreOfJane = " + scoreOfJane);
        System.out.println("scoreOfMike = " + scoreOfMike);
        System.out.println("scoreOfMerry = " + scoreOfMerry);

        /**
         * getOrDefault() 는 `Map` 인터페이스에서 제공되는 메소드.
         * 주어진 키에 해당하는 값이 있는 경우 그 값을 반환하고, 만약 키에 해당하는 값이 없는
         * 경우에는 기본 값을 반환.
         *
         * [메소드 시그니쳐]
         * V getOrDefault(Object key, V defaultValue)
         * - key : 찾고자 하는 요소의 키 (key)
         * - defaultValue : 키에 해당하는 값이 없을 때 반환할 기본값.
         */


    }
}
