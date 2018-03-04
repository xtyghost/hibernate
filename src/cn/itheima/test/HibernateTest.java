package cn.itheima.test;

import cn.itheima.domain.*;
import cn.itheima.utils.SessionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

/**
 * @author ghost
 * @Package: cn.itheima.test
 * @Description 用于测试的类
 * @create 2018-03-03 12:06
 **/
public class HibernateTest {
    @Test
    public void test1() {
        Session session = SessionUtils.openSession();
        session.beginTransaction();
        //创建一个人
        User user = new User();
        user.setName("nancy");
        //创建身份证对象
        IdCard idCard = new IdCard();
        idCard.setCardNum("1234567890");
        //身份证号关联人
        idCard.setUser(user);
        session.saveOrUpdate(idCard);
        session.getTransaction().commit();
        //关闭session
        session.close();
    }

    //测试级联删除
    @Test
    public void test2() {
        Session session = SessionUtils.openSession();
        session.beginTransaction();
        Husband husband = new Husband();
        husband.setName("lisi");
        //创建wife
        Wife wife = new Wife();
        wife.setName("张三");
        husband.setWife(wife);
        //级联保持
        session.saveOrUpdate(husband);
        session.getTransaction().commit();
        //关闭session
        session.close();
    }

    @Test
    public void test3() { Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //创建一个用户对象
        Customer customer = new Customer();
        customer.setName("nancy");
        //create two orders
        Order order = new Order();
        order.setMoney(231d);
        order.setReceiverInfo("南京2");
        Order order1 = new Order();
        order1.setReceiverInfo("西安2");
        order1.setMoney(2143d);
        //将订单和用户管连,维护外键
        order.setCustomer(customer);
        order1.setCustomer(customer);
        //将用户和订单关联,方便级联删除
        Set<Order> orders = customer.getOrders();
        orders.add(order);
        orders.add(order1);

        //提交保持数据
        session.saveOrUpdate(customer);
        transaction.commit();
        //关闭session
        session.close();
    }
    //有对多级联删除
    @Test
    public void test4() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1);
        System.out.println(customer);
        Set<Order> orders = customer.getOrders();
        System.out.println("看一下set的类型" + orders.getClass());
        for (Order order : orders) {
            System.out.println(order);
        }
        session.delete(customer);
        transaction.commit();

    }

    //删除order级联删除customer
    @Test
    public void test5() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer load = session.load(Customer.class, 2);
        Set<Order> orders = load.getOrders();
        //创建订单
        Order order = new Order();
        order.setMoney(314d);
        //建立短信
        order.setCustomer(load);
        session.delete(order);
        transaction.commit();
        //关闭session
        session.close();
    }
}
