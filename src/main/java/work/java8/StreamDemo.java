package work.java8;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import ttjj.dto.FundTrade;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * java8-stream
 */
public class StreamDemo {
    public static void main(String[] args) {
//        test01();//特性简介
//        test02WhatIsStream();//什么是 Stream
//        test03SteamType();//生成流两种类型
//        steam01();
//        steamForEach();
//        map();
//        mapDistinct();
        filter();

    }

    private static void filter() {
        System.out.println("filter 方法用于通过设置的条件过滤出元素。");
        List<FundTrade> fundTradeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FundTrade fundTrade1 = new FundTrade();
            fundTrade1.setFundInfo("测试" + i);
            fundTradeList.add(fundTrade1);

            FundTrade fundTrade2 = new FundTrade();
            fundTrade2.setFundInfo("测试" + i);
            fundTradeList.add(fundTrade2);
        }

        List<FundTrade> rs = new ArrayList<>();
        long count = fundTradeList.stream().filter(fundTrade -> {
            if("测试1".equals(fundTrade.getFundInfo())){
                return true;
            }
            return false;
        }).count();
        System.out.println(count);

    }

    private static void mapDistinct() {
        System.out.println("map 方法用于映射每个元素到对应的结果");
        List<FundTrade> fundTradeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FundTrade fundTrade1 = new FundTrade();
            fundTrade1.setFundInfo("测试" + i);
            fundTradeList.add(fundTrade1);

            FundTrade fundTrade2 = new FundTrade();
            fundTrade2.setFundInfo("测试" + i);
            fundTradeList.add(fundTrade2);
        }

        List<FundTrade> rs = new ArrayList<>();
        List<FundTrade> newRs = new ArrayList<>();
        rs = fundTradeList.stream().collect(
                collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(FundTrade::getFundInfo))), ArrayList::new
                )
        );

        System.out.println("源数据：");
        fundTradeList.stream().forEach(e -> {
            System.out.println("hashCode:" + e + "," + JSON.toJSONString(e));
        });
        System.out.println();

        System.out.println("目标数据:");
        rs.stream().forEach(e -> {
            System.out.println("hashCode:" + e + "," + JSON.toJSONString(e));
            FundTrade fundTrade = new FundTrade();
            BeanUtils.copyProperties(e, fundTrade);
            newRs.add(fundTrade);
        });
        System.out.println();

        System.out.println("目标数据线程安全对象：");
        newRs.stream().forEach(e -> {
            System.out.println("hashCode:" + e + "," + JSON.toJSONString(e));
        });
        System.out.println();
    }

    private static void map() {
        System.out.println("map 方法用于映射每个元素到对应的结果");
        List<Integer> integerList = Arrays.asList(1, 3, 5, 3, 5);
        List<Integer> integerList2 = integerList.stream().map(integer -> integer * integer).collect(Collectors.toList());
        integerList2.stream().forEach(integer -> {
            System.out.println(integer);
        });
    }

    private static void steamForEach() {
        System.out.println("Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。");
        List<Integer> integerList = Arrays.asList(1, 3, 5);
//        List<Integer> integerList = null;
        if (integerList == null) {
            System.out.println("空列表！");
            return;
        }
        integerList.stream().limit(10).forEach(integer -> {
            if (integer > 2) {
                System.out.println("大于2：" + integer);
            }
        });
    }

    private static void steam01() {
        List<String> stringList = Arrays.asList("bb", "", "c", null, "aaa");
//        List<String> filterRs = stringList.stream().filter(e -> StringUtils.isNotBlank(e)).collect(Collectors.toList());
        List<String> filterRs = stringList.stream().filter(e -> "c".equals(e)).collect(Collectors.toList());
        System.out.println("filterRs:");
        filterRs.stream().forEach(e -> {
            System.out.print(e + ",");
        });
        System.out.println();

        System.out.println("filterSortedRs:");
        List<String> filterSortedRs = stringList.stream().filter(e -> StringUtils.isNotBlank(e)).sorted().collect(Collectors.toList());
        filterSortedRs.stream().forEach(e -> {
            System.out.print(e + ",");
        });
        System.out.println();
    }

    /**
     * 生成流两种类型
     */
    private static void test03SteamType() {
        System.out.println("在 Java 8 中, 集合接口有两个方法来生成流：\n" +
                "\n" +
                "stream() − 为集合创建串行流。\n" +
                "\n" +
                "parallelStream() − 为集合创建并行流。");
    }

    /**
     *
     */
    private static void test02WhatIsStream() {
        System.out.println("什么是 Stream？\n" +
                "Stream（流）是一个来自数据源的元素队列并支持聚合操作\n" +
                "\n" +
                "元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。\n" +
                "数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。\n" +
                "聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。\n" +
                "和以前的Collection操作不同， Stream操作还有两个基础的特征：\n" +
                "\n" +
                "Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。\n" +
                "内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。");
    }

    /**
     *
     */
    private static void test01() {
        System.out.println("Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。\n" +
                "\n" +
                "Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。\n" +
                "\n" +
                "Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。\n" +
                "\n" +
                "这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。\n" +
                "\n" +
                "元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。");
    }
}
