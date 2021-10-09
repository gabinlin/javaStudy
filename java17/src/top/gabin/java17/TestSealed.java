package top.gabin.java17;

public class TestSealed {
    public static void main(String[] args) {
        Bird bird = new Bird() {

        };
        System.out.println(bird);
    }
}

sealed class Person permits Student, Worker {}

// 无法被继承，覆盖修改
final class Student extends Person {

}

// 解除密封
non-sealed class Worker extends Person {

}

sealed interface Fly {}

// 解除密封，如果保持密封，则必须有子类
non-sealed interface Bird extends Fly {}
