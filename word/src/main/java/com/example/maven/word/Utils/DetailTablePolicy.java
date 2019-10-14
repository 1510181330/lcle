package com.example.maven.word.Utils;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.example.maven.word.Entities.WordEntities.TableDetail;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;



import java.util.List;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    private final int startRow = 1;

    @Override
    public void render(XWPFTable xwpfTable, Object date) {
       if(date == null){
            return ;
        }
        TableDetail tabledetail = (TableDetail)date;
        List<RowRenderData> allItems = tabledetail.getBody1();
        if (null != allItems) {
            xwpfTable.removeRow(startRow);
            // 循环插入行
            for (int i = 0; i < allItems.size(); i++) {
                XWPFTableRow insertNewTableRow = xwpfTable.insertNewTableRow(startRow);
                for (int j = 0; j < 9; j++)
                {
                    insertNewTableRow.createCell();
                }
                // 合并单元格
                //TableTools.mergeCellsHorizonal(xwpfTable, startRow, 0, 3);
                // 渲染单行人工费数据
                MiniTableRenderPolicy.Helper.renderRow(xwpfTable, startRow, allItems.get(i));
            }
        }
    }
}