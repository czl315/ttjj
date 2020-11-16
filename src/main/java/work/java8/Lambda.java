package work.java8;

/**
 * java8新特性-lamdba
 */
public class Lambda {
    interface InterfaceOne {
        String method(String a, String b);
    }

    public static void main(String[] args) {
        test01();
    }

    static void test01(){
        System.out.println("Lambda 表达式，也可称为闭包.Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）");
        InterfaceOne anInterface = (String a, String b) -> String.join("_", a, b);
        System.out.println(anInterface.method("aaa", "bbb"));
    }


}
