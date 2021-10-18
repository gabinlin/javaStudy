package top.gabin.springBoot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.gabin.springBoot.properties.GabinTopConfig;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private GabinTopConfig gabinTopConfig;

    @Test
    void contextLoads() {
        System.out.println(gabinTopConfig);
    }

}
