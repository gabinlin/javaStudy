package top.gabin.java16;

public class TestInstanceOf {

    public static void main(String[] args) {
        // String的话会有语法错误，认为没有意义（编译时）
        Object a = "a";
        // 这个语法感觉像是抄switch的。。。
        if (a instanceof String b) {
            System.out.println(b.length());
        }
    }
}
