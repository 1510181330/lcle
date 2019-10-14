package com.example.maven.word.Entities.WordEntities;


import com.deepoove.poi.data.TextRenderData;

public class InstructorRowBean {

    private TextRenderData gradle;
    private TextRenderData instructor;
    private TextRenderData classs;
    private TextRenderData teacher;
    private TextRenderData absence;
    private TextRenderData allNumber;
    private TextRenderData person;

    public InstructorRowBean(TextRenderData gradle, TextRenderData instructor, TextRenderData classs, TextRenderData teacher, TextRenderData absence, TextRenderData allNumber, TextRenderData person) {
        this.gradle = gradle;
        this.instructor = instructor;
        this.classs = classs;
        this.teacher = teacher;
        this.absence = absence;
        this.allNumber = allNumber;
        this.person = person;
    }

    public TextRenderData getGradle() {
        return gradle;
    }

    public void setGradle(TextRenderData gradle) {
        this.gradle = gradle;
    }

    public TextRenderData getInstructor() {
        return instructor;
    }

    public void setInstructor(TextRenderData instructor) {
        this.instructor = instructor;
    }

    public TextRenderData getClasss() {
        return classs;
    }

    public void setClasss(TextRenderData classs) {
        this.classs = classs;
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

    public TextRenderData getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(TextRenderData allNumber) {
        this.allNumber = allNumber;
    }

    public TextRenderData getPerson() {
        return person;
    }

    public void setPerson(TextRenderData person) {
        this.person = person;
    }

    public InstructorRowBean() {
        super();
    }

    @Override
    public String toString() {
        return "InstructorRowBean{" +
                "gradle=" + gradle +
                ", instructor=" + instructor +
                ", classs=" + classs +
                ", teacher=" + teacher +
                ", absence=" + absence +
                ", allNumber=" + allNumber +
                ", person=" + person +
                '}';
    }
}
