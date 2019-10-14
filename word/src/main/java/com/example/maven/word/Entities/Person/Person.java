package com.example.maven.word.Entities.Person;

public class Person {

    private int id;
    private String name;
    private String password;
    private Integer sex;
    private String QQ;
    private String phone;
    private String depname;
    private Integer priority;

    public Person(int id, String name, String password, Integer sex, String QQ, String phone, String depname, Integer priority) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.QQ = QQ;
        this.phone = phone;
        this.depname = depname;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Person() {
        super();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", QQ='" + QQ + '\'' +
                ", phone='" + phone + '\'' +
                ", depname='" + depname + '\'' +
                ", priority=" + priority +
                '}';
    }
}
