package cn.itheima.domain;

import javax.persistence.*;

/**
 * @author ghost
 * @Package: cn.itheima.domain
 * @Description
 * @create 2018-03-03 15:04
 **/
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double money;
    private String  receiverInfo;
    @ManyToOne(targetEntity = Customer.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "c_customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(Double money, String receiverInfo) {
        this.money = money;
        this.receiverInfo = receiverInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(String receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (money != null ? !money.equals(order.money) : order.money != null) return false;
        return receiverInfo != null ? receiverInfo.equals(order.receiverInfo) : order.receiverInfo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (receiverInfo != null ? receiverInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", money=" + money +
                ", receiverInfo='" + receiverInfo + '\'' +
                '}';
    }
}
