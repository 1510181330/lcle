package com.example.maven.word.Entities.Person;

import java.util.LinkedList;

public class TargetTable {

    private Integer id;
    private LinkedList<Integer> PerId;
    private LinkedList<String> PerName;
    private LinkedList<String> PerPhone;

    private LinkedList<Integer> PerId1;
    private LinkedList<String> PerName1;
    private LinkedList<String> PerPhone1;

    public TargetTable(Integer id, LinkedList<Integer> perId, LinkedList<String> perName, LinkedList<String> perPhone, LinkedList<Integer> perId1, LinkedList<String> perName1, LinkedList<String> perPhone1) {
        this.id = id;
        PerId = perId;
        PerName = perName;
        PerPhone = perPhone;
        PerId1 = perId1;
        PerName1 = perName1;
        PerPhone1 = perPhone1;
    }

    public TargetTable() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkedList<Integer> getPerId() {
        return PerId;
    }

    public void setPerId(LinkedList<Integer> perId) {
        PerId = perId;
    }

    public LinkedList<String> getPerName() {
        return PerName;
    }

    public void setPerName(LinkedList<String> perName) {
        PerName = perName;
    }

    public LinkedList<String> getPerPhone() {
        return PerPhone;
    }

    public void setPerPhone(LinkedList<String> perPhone) {
        PerPhone = perPhone;
    }

    public LinkedList<Integer> getPerId1() {
        return PerId1;
    }

    public void setPerId1(LinkedList<Integer> perId1) {
        PerId1 = perId1;
    }

    public LinkedList<String> getPerName1() {
        return PerName1;
    }

    public void setPerName1(LinkedList<String> perName1) {
        PerName1 = perName1;
    }

    public LinkedList<String> getPerPhone1() {
        return PerPhone1;
    }

    public void setPerPhone1(LinkedList<String> perPhone1) {
        PerPhone1 = perPhone1;
    }

    @Override
    public String toString() {
        return "TargetTable{" +
                "id=" + id +
                ", PerId=" + PerId +
                ", PerName=" + PerName +
                ", PerPhone=" + PerPhone +
                ", PerId1=" + PerId1 +
                ", PerName1=" + PerName1 +
                ", PerPhone1=" + PerPhone1 +
                '}';
    }
}
