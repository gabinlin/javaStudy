package top.gabin.java10;

public class TestLocalVariablesInterface {

    public static void main(String[] args) {
        // 接口可以进行钻石推断
        Fly<String> fly = new Fly<>() {
        };
        System.out.println(fly.test());
    }

}

interface Fly<T> {

    default T test() {
        return null;
    }

}
