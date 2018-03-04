package cn.itheima.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.engine.spi.CascadeStyles;
import org.hibernate.mapping.Value;
import sun.awt.SunHints;

import javax.naming.Name;
import javax.persistence.*;

/**
 * @author ghost
 * @Package: cn.itheima.domain
 * @Description 丈夫的实体类
 * @create 2018-03-03 14:06
 **/
@Entity
@Table(name = "t_husband")
public class Husband {
    @Id
    @GenericGenerator(name = "myForeignKey",strategy = "foreign",parameters = {@Parameter(name ="property", value="wife")})
    @GeneratedValue(generator = "myForeignKey")
    private Integer id;
    private  String  name;
    @OneToOne
    @PrimaryKeyJoinColumn
    private  Wife   wife;

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

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
