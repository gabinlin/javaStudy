package top.gabin.java9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestTryResource {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");
        // 8应该必须在括号内进行赋值初始化
        try (fileInputStream) {

        } catch (Exception e) {

        }
    }
}
