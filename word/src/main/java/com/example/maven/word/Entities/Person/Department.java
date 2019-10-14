package com.example.maven.word.Entities.Person;

public class Department {

    private Integer id;
    private String depname;

    public Department(Integer id, String depname) {
        this.id = id;
        this.depname = depname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Department() {
        super();
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depname='" + depname + '\'' +
                '}';
    }
}
