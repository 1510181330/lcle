package com.example.maven.word.Entities;


public class classToTeacherMapper {

    private int id;
    private Integer gradle;
    private Integer weeknumber;
    private Integer totlenumber;
    private Integer sum;
    private String classes;
    private String techer;
    private String tphone;

    public classToTeacherMapper(int id, Integer gradle, Integer weeknumber, Integer totlenumber, Integer sum, String classes, String techer, String tphone) {
        this.id = id;
        this.gradle = gradle;
        this.weeknumber = weeknumber;
        this.totlenumber = totlenumber;
        this.sum = sum;
        this.classes = classes;
        this.techer = techer;
        this.tphone = tphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGradle() {
        return gradle;
    }

    public void setGradle(Integer gradle) {
        this.gradle = gradle;
    }

    public Integer getWeeknumber() {
        return weeknumber;
    }

    public void setWeeknumber(Integer weeknumber) {
        this.weeknumber = weeknumber;
    }

    public Integer getTotlenumber() {
        return totlenumber;
    }

    public void setTotlenumber(Integer totlenumber) {
        this.totlenumber = totlenumber;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getTecher() {
        return techer;
    }

    public void setTecher(String techer) {
        this.techer = techer;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public classToTeacherMapper() {
        super();
    }

    @Override
    public String toString() {
        return "classToTeacherMapper{" +
                "id=" + id +
                ", gradle=" + gradle +
                ", weeknumber=" + weeknumber +
                ", totlenumber=" + totlenumber +
                ", sum=" + sum +
                ", classes='" + classes + '\'' +
                ", techer='" + techer + '\'' +
                ", tphone='" + tphone + '\'' +
                '}';
    }
}
