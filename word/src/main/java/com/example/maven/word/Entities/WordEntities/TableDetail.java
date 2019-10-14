package com.example.maven.word.Entities.WordEntities;

import com.deepoove.poi.data.RowRenderData;

import java.util.List;

public class TableDetail {

    private List<RowRenderData> body1;

    public TableDetail(List<RowRenderData> body1) {
        this.body1 = body1;
    }

    public List<RowRenderData> getBody1() {
        return body1;
    }

    public void setBody1(List<RowRenderData> body1) {
        this.body1 = body1;
    }

    public TableDetail() {
        super();
    }

    @Override
    public String toString() {
        return "TableDetail{" +
                "body1=" + body1 +
                '}';
    }
}
