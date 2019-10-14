package com.example.maven.word.Test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {

    //用于暂时存储数据
    private List<Object> datas = new ArrayList<>();

    public ExcelListener() {
        super();
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
