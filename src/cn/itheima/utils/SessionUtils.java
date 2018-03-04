package cn.itheima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ghost
 * @Package: cn.itheima.utils
 * @Description 一个用于获取session的工厂类
 * @create 2018-03-03 11:49
 **/
public class SessionUtils {
    private static SessionFactory factory;
    private static Configuration configuration;
    static {
         configuration= new Configuration().configure();
        factory=configuration.buildSessionFactory();
    }
      /**
      　　* @Description: 用于获取sessin的方法
      　　* @param
      　　* @return 
      　　* @throws
      　　* @author ghost
      　　* @date 2018/3/3 11:53 
      　　*/
    public static Session openSession(){
        return factory.openSession();
    }
}
