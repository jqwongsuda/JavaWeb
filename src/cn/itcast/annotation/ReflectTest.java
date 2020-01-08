package cn.itcast.annotation;

import java.lang.reflect.Method;

/**
 * 框架类
 */
@Pro(className = "cn.itcast.annotation.Demo1", methodName = "show")
public class ReflectTest {
    public static void main(String[] args) throws Exception{

        //1.解析注解
        //1.1 获取该类的字节码文件对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //2.获取上边字节码对象的注解对象
        //  其实就是在内存中生成了一个该注解接口的子类实现对象
        Pro annotation = reflectTestClass.getAnnotation(Pro.class);
        //3.获取注解对象中定义的抽象方法，获取返回值
        String className = annotation.className();
        String methodName = annotation.methodName();
        System.out.println(className);
        System.out.println(methodName);

        //4.加载类进内存
        Class cls = Class.forName(className);
        //5.创建对象
        Object obj = cls.newInstance();
        //6.获取方法对象
        Method method = cls.getMethod(methodName);
        //7.执行方法
        method.invoke(obj);
    }
}
