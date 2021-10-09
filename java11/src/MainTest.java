import top.gabin.java9.module.TestModule;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {
        // 有引入的模块是正常的
        new TestModule();
        // 没有在模块中配置的不能使用
//        TestInterfacePrivateMethod

        new ArrayList<>().stream().map((var o) -> {
            return o;
        }).collect(Collectors.toList());
    }
}
