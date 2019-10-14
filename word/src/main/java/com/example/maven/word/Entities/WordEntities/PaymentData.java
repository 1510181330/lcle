package com.example.maven.word.Entities.WordEntities;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.MiniTableRenderData;

public class PaymentData {

    @Name("week")
    private String word_week;

    @Name("laber1")
    private String word_laber1;

    @Name("laber2")
    private String word_laber2;

    @Name("laber3")
    private String word_laber3;

    @Name("detail_table")
    private TableDetail tabledetail;

    private MiniTableRenderData miniTableDate;

    public PaymentData(String word_week, String word_laber1, String word_laber2, String word_laber3, TableDetail tabledetail, MiniTableRenderData miniTableDate) {
        this.word_week = word_week;
        this.word_laber1 = word_laber1;
        this.word_laber2 = word_laber2;
        this.word_laber3 = word_laber3;
        this.tabledetail = tabledetail;
        this.miniTableDate = miniTableDate;
    }

    public String getWord_week() {
        return word_week;
    }

    public void setWord_week(String word_week) {
        this.word_week = word_week;
    }

    public String getWord_laber1() {
        return word_laber1;
    }

    public void setWord_laber1(String word_laber1) {
        this.word_laber1 = word_laber1;
    }

    public String getWord_laber2() {
        return word_laber2;
    }

    public void setWord_laber2(String word_laber2) {
        this.word_laber2 = word_laber2;
    }

    public String getWord_laber3() {
        return word_laber3;
    }

    public void setWord_laber3(String word_laber3) {
        this.word_laber3 = word_laber3;
    }

    public TableDetail getTabledetail() {
        return tabledetail;
    }

    public void setTabledetail(TableDetail tabledetail) {
        this.tabledetail = tabledetail;
    }

    public MiniTableRenderData getMiniTableDate() {
        return miniTableDate;
    }

    public void setMiniTableDate(MiniTableRenderData miniTableDate) {
        this.miniTableDate = miniTableDate;
    }

    public PaymentData() {
        super();
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "word_week='" + word_week + '\'' +
                ", word_laber1='" + word_laber1 + '\'' +
                ", word_laber2='" + word_laber2 + '\'' +
                ", word_laber3='" + word_laber3 + '\'' +
                ", tabledetail=" + tabledetail +
                ", miniTableDate=" + miniTableDate +
                '}';
    }
}
