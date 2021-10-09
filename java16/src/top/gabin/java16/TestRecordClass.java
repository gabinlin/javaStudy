package top.gabin.java16;

public class TestRecordClass {

    public static void main(String[] args) {
        Person person = new Person("hello");
        System.out.println(person.name());
    }

}

record Person(String name) {}
