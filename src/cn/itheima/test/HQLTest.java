package cn.itheima.test;

import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;
import cn.itheima.utils.SessionUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.junit.Test;

import java.util.List;

/**
 * @author ghost
 * @Package: cn.itheima.test
 * @Description 关于Hql的Test类
 * @create 2018-03-03 14:44
 **/
public class HQLTest {
    /**
     * 　　* @Description: 数据准备
     * 　　* @param
     * 　　* @return
     * 　　* @throws
     * 　　* @author ghost
     * 　　* @date 2018/3/3 19:13
     */
    @Test
    public void test1() {
        Session session = SessionUtils.openSession();
        session.beginTransaction();
        //创建用户
        Customer customer = new Customer();
        customer.setName("王五");
        int n = 100;
        for (int i = 0; i < n; i++) {
            Order order = new Order();
            order.setMoney(134d + i * 10);
            order.setReceiverInfo("孟买");
            order.setCustomer(customer);
            session.saveOrUpdate(order);
        }
        session.getTransaction().commit();
        //关闭session
        session.close();
    }

    @Test
    public void test2() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //变写hql语句
        String hql = "from Customer";
        //链式编程
        List list = session.createQuery(hql).list();
        System.out.println(list);

        transaction.commit();
        //关闭session
        session.close();
    }

    /**
     * 　　* @Description: 分页查询
     * 　　* @param
     * 　　* @return
     * 　　* @throws
     * 　　* @author ghost
     * 　　* @date 2018/3/3 19:54
     */
    @Test
    public void test4() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //创建查询对象
        Query query = session.createQuery("from Order order by money");

        query.setFirstResult(6);
        query.setMaxResults(6);
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        transaction.commit();
        //关闭session
        session.close();
    }

    /**
     * 　　* @Description: 投影查询
     * 　　* @param
     * 　　* @return
     * 　　* @throws
     * 　　* @author ghost
     * 　　* @date 2018/3/3 20:06
     */
    @Test
    public void test5() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select new cn.itheima.domain.Order (money,receiverInfo) from Order ";
        Query query = session.createQuery(hql);
        List list = query.list();
        System.out.println(list);
        transaction.commit();
        //关闭session
        session.close();
    }

    /**
     * 　　* @Description: 条件检索
     * 　　* @param
     * 　　* @return
     * 　　* @throws
     * 　　* @author ghost
     * 　　* @date 2018/3/3 20:51
     */
    @Test
    public void test6() {
        Session session = SessionUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Customer.class);
        Criterion name = Restrictions.like("name", "n%");
        SimpleExpression id = Restrictions.eq("id", 3);
        //添加条件
        criteria.add(id);
        criteria.add((Criterion) name);

        Customer customer = (Customer) criteria.uniqueResult();
        System.out.println(customer);
        Criteria criteria1 = session.createCriteria(Order.class);
        LogicalExpression and = Restrictions.and(Restrictions.gt("money", 321d), Restrictions.eq("customer", customer));
        criteria1.add(and);
        //执行语句
        List list = criteria1.list();
        System.out.println(list);
        transaction.commit();
        //关闭session
        session.close();
    }
}
