package com.example.maven.word.Entities.WordEntities;

import com.deepoove.poi.data.TextRenderData;

public class RowBean {

    private TextRenderData gradle;
    private TextRenderData classs;
    private TextRenderData week;
    private TextRenderData allNumber;
    private TextRenderData section;
    private TextRenderData lession;
    private TextRenderData teacher;
    private TextRenderData absence;
    private TextRenderData person;

    public RowBean(TextRenderData gradle, TextRenderData classs, TextRenderData week, TextRenderData allNumber, TextRenderData section, TextRenderData lession, TextRenderData teacher, TextRenderData absence, TextRenderData person) {
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

    public TextRenderData getGradle() {
        return gradle;
    }

    public void setGradle(TextRenderData gradle) {
        this.gradle = gradle;
    }

    public TextRenderData getClasss() {
        return classs;
    }

    public void setClasss(TextRenderData classs) {
        this.classs = classs;
    }

    public TextRenderData getWeek() {
        return week;
    }

    public void setWeek(TextRenderData week) {
        this.week = week;
    }

    public TextRenderData getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(TextRenderData allNumber) {
        this.allNumber = allNumber;
    }

    public TextRenderData getSection() {
        return section;
    }

    public void setSection(TextRenderData section) {
        this.section = section;
    }

    public TextRenderData getLession() {
        return lession;
    }

    public void setLession(TextRenderData lession) {
        this.lession = lession;
    }

    public TextRenderData getTeacher() {
        return teacher;
    }

    public void setTeacher(TextRenderData teacher) {
        this.teacher = teacher;
    }

    public TextRenderData getAbsence() {
        return absence;
    }

    public void setAbsence(TextRenderData absence) {
        this.absence = absence;
    }

    public TextRenderData getPerson() {
        return person;
    }

    public void setPerson(TextRenderData person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "RowBean{" +
                "gradle=" + gradle +
                ", classs=" + classs +
                ", week=" + week +
                ", allNumber=" + allNumber +
                ", section=" + section +
                ", lession=" + lession +
                ", teacher=" + teacher +
                ", absence=" + absence +
                ", person=" + person +
                '}';
    }

    public RowBean() {
        super();
    }
}
