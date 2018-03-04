package cn.itheima.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * @author ghost
 * @Package: cn.itheima.domain
 * @Description
 * @create 2018-03-03 11:56
 **/
@Entity
@Table(name = "t_idcard")
public class IdCard {
    @Id
    @GenericGenerator(strategy = "uuid",name = "myuuid")
    @GeneratedValue(generator = "myuuid")
    private  String id;
    private  String cardNum;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "c_user_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private  User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
