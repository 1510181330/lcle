package com.example.maven.word.Entities.Person;

public class DisPerson {

    private int id;
    private String name;
    private String password;
    private String sex;
    private String QQ;
    private String phone;
    private String depname;

    public DisPerson(int id, String name, String password, String sex, String QQ, String phone, String depname) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.QQ = QQ;
        this.phone = phone;
        this.depname = depname;
    }

    public DisPerson() {
        super();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    @Override
    public String toString() {
        return "DisPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", QQ='" + QQ + '\'' +
                ", phone='" + phone + '\'' +
                ", depname='" + depname + '\'' +
                '}';
    }
}
