package cn.itcast.annotation;

import java.lang.annotation.*;

/**
 * 描述需要执行的类名和方法名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pro {
    String className();
    String methodName();
}

/*
  当某个类使用了这个注解，程序会自动在内存中生成一个该注解接口的子类实现对象，
  注解所带的参数如：@Pro(className = "cn.itcast.annotation.Demo1", methodName = "show")

  像下面这样
  public class ProImpl implements Pro{
        public String className(){
            return "cn.itcast.annotation.Demo1";
        }
        public String methodName(){
            return "show";
        }

  }
 */
