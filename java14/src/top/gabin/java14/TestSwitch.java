package top.gabin.java14;

import java.util.Random;

public class TestSwitch {

    public static void main(String[] args) {
        int i = new Random().nextInt();
        String a = switch (i + "") {
            case "1", "first" -> "one";
            case "2", "second" -> "two";
            default -> "other";
        };
        System.out.println(a);
    }
}
