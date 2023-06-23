package javafundamentals.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompareTwoList {
    public static void main(String[] args) {
        // Map List 와 String List 비교해서 중복값만 그대로 추출
        List<Map<String, Object>> mapList = new ArrayList<>();

        for(int i=0; i<3; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("name", "jane" + i);
            map.put("age", 10 + 1);
            mapList.add(map);
        }
        System.out.println("mapList");
        for (Map<String, Object> stringObjectMap : mapList) {

            System.out.println(stringObjectMap.get("name"));
        }

        List<String> stringList = new ArrayList<>();
        for(int i=1; i<4; i++){
            stringList.add("jane" + i);
        }
        System.out.println("===========");
        System.out.println("stringList");
        for (String s : stringList) {
            System.out.println(s);
        }



        List<Map<String, Object>> duplicatedList = mapList
                .stream().filter(ml -> stringList.stream().anyMatch(Predicate.isEqual(ml.get("name"))))
                .collect(Collectors.toList());
        System.out.println("===========");
        System.out.println("final");
        for (Map<String, Object> dMap : duplicatedList) {
            System.out.println(dMap.get("name"));
        }



    }
}
