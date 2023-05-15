package javafundamentals.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindAnyGetMain2 {
    public static void main(String[] args) {
        List<Integer> 숫자들 = Arrays.asList(1, 2, 3, 4, 5, 6);

        Optional<Integer> 결과 = 숫자들.stream().filter(n -> n > 4)
                .findAny();

        if (결과.isPresent()){
            System.out.println("FOUND = " + 결과.get());
        }else{
            System.out.println("없음");
        }
    }
}
