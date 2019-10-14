package com.example.maven.word.Entities.WordEntities;

import com.deepoove.poi.data.TextRenderData;

public class Record {

    private int id;
    private String gradle;
    private String classs;
    private String week;
    private String allNumber;
    private String section;
    private String lession;
    private String teacher;
    private String absence;
    private String person;

    public Record(int id, String gradle, String classs, String week, String allNumber, String section, String lession, String teacher, String absence, String person) {
        this.id = id;
        this.gradle = gradle;
        this.classs = classs;
        this.week = week;
        this.allNumber = allNumber;
        this.section = section;
        this.lession = lession;
        this.teacher = teacher;
        this.absence = absence;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradle() {
        return gradle;
    }

    public void setGradle(String gradle) {
        this.gradle = gradle;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(String allNumber) {
        this.allNumber = allNumber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLession() {
        return lession;
    }

    public void setLession(String lession) {
        this.lession = lession;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Record() {
        super();
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", gradle='" + gradle + '\'' +
                ", classs='" + classs + '\'' +
                ", week='" + week + '\'' +
                ", allNumber='" + allNumber + '\'' +
                ", section='" + section + '\'' +
                ", lession='" + lession + '\'' +
                ", teacher='" + teacher + '\'' +
                ", absence='" + absence + '\'' +
                ", person='" + person + '\'' +
                '}';
    }
}
