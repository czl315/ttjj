package utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForEachTest {
    public static void main(String[] args) {
        List<String> collect = new ArrayList<>();
//        collect.add("a");
//        collect.add("b");
//        collect.add("c");
//        List<String> collect = null;
        collect.forEach(s -> {
            String str = s;
            System.out.println(str);
        });
    }
}
