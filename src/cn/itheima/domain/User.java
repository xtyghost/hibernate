package cn.itheima.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author ghost
 * @Package: cn.itheima.domain
 * @Description 用户的实体类
 * @create 2018-03-03 11:54
 **/
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GenericGenerator(strategy = "uuid",name = "myuuid")
    @GeneratedValue(generator = "myuuid")
    private  String id;
    private  String name;
    @OneToOne(targetEntity = IdCard.class,mappedBy = "user")
    private  IdCard idCard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
