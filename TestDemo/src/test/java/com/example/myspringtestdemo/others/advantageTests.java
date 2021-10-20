package com.example.myspringtestdemo.others;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class advantageTests {

    private List<Integer> paramA;
    private List<Integer> paramB;
    private Map<Integer,Integer> pair;

    private int[] advantageCount(int[] A, int[] B) {
        paramA = Arrays.stream(A).boxed().sorted().collect(Collectors.toList());
        paramB = Arrays.stream(B).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        pair=new HashMap<>();
        for (Integer a : paramA) {
            for (Integer b : paramB) {
                if(a>b)
                    pair.put(b,a);
            }
        }
        int[] result=new int[paramA.size()];

        return result;
    }

    @Test
    void test() {
        advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11});
    }

}
