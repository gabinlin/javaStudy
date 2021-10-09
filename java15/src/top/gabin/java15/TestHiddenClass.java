package top.gabin.java15;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestHiddenClass {

    public static void main(String[] args) {
        Dog animal = new Dog();
        Animal instance = (Animal) Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("洗手");
                return method.invoke(animal, args);
            }
        });
        instance.eat();
        System.out.println(instance.getClass().getName());
    }

}

interface Animal {
    void eat();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("eat");
    }
}
