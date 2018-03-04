package cn.itheima.domain;

import javax.persistence.*;

/**
 * @author ghost
 * @Package: cn.itheima.domain
 * @Description 妻子的实体类
 * @create 2018-03-03 14:06
 **/
@Entity
@Table(name = "t_wife")
public class Wife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
    @OneToOne
    @PrimaryKeyJoinColumn
    private  Husband husband;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }
}
