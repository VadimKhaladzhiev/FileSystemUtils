package ru.sd7.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "users")
public class User {
    @XmlAttribute
    @Id
    private String id;
    @XmlElement
    @Indexed
    private String ic;
    @XmlElement
    private String name;
    @XmlElement
    private int age;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;

    public User() {
    }

    public User(String ic, String name, int age, Date createdDate) {
        this.ic = ic;
        this.name = name;
        this.age = age;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
