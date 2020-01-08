package cn.itcast.annotation.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 简单的测试框架
 *
 * 当主方法执行后，会自动执行被检测的所有方法，判断方法是否有异常，记录到文件中
 */
public class TestCheck {
    public static void main(String[] args) throws IOException {
        //1.创建计算器对象
        Calculator c = new Calculator();
        //2.获取字节码文件对象
        Class cls = c.getClass();
        //3.获取所有方法
        Method[] methods = cls.getMethods();
        //4.判断方法上是否有Check注解
        int number = 0;//出现异常的次数
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
        for(Method method : methods)
        {
            //5.有，就执行
            if(method.isAnnotationPresent(Check.class)) {
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    //6.捕获异常，并记录日志
                    number++;
                    bw.write(method.getName() + "方法出异常了。。。");
                    bw.newLine();
                    bw.write("异常的名称：" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因：" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("------------------------------------------");
                }
            }
        }
        bw.write("本次测试一共出现" + number + "次异常");
        bw.flush();
        bw.close();


    }
}
