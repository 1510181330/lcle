package com.example.maven.word.Utils;

import com.example.maven.word.Service.ClassToTeacherMapperService;
import com.example.maven.word.Service.WordRecordSerivce;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import com.example.maven.word.Entities.WordEntities.Record;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Excel_Utils{

    public void createExcelForOne(ClassToTeacherMapperService ConvertDao, WordRecordSerivce recordSerivce, String args) throws Exception
    {
        //FileInputStream os = new FileInputStream("F:\\work\\template\\"+"signal.xls");
        FileInputStream os = new FileInputStream("/tmp/word/template/"+"signal.xls");
        //创建工作薄
        Workbook wb = Workbook.getWorkbook(os);
        //WritableWorkbook workbook = wb.createWorkbook(new File("F:\\work\\target\\"+args+"出勤率汇总文件（含辅导员）.xls"), wb);
        WritableWorkbook workbook = wb.createWorkbook(new File("/tmp/word/target/"+args+"出勤率汇总文件（含辅导员）.xls"), wb);
        //创建新的一页
        System.out.println("sheets : "+workbook.getNumberOfSheets());
        WritableSheet sheet = workbook.createSheet(args, workbook.getNumberOfSheets());
        //构造表头
        sheet.mergeCells(0, 0, 7, 0);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
        WritableFont bold = new WritableFont(WritableFont.createFont("仿宋"),18);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleFormate.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        Label title = new Label(0,0,args+"查课结果（含辅导员）", titleFormate);
        sheet.setRowView(0, 600, false);//设置第一行的高度
        sheet.addCell(title);

        //创建要显示的具体内容
        WritableFont color = new WritableFont(WritableFont.createFont("仿宋"), 14);//选择字体
        color.setColour(Colour.BLACK);
        WritableCellFormat colorFormat = new WritableCellFormat(color);//生成一个单元格样式控制对象
        colorFormat.setAlignment(jxl.format.Alignment.CENTRE);
        colorFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        colorFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        /*
        //这个方法本身没有问题
        //但是由于中文的存在，这个操作会失效，需要手动设置列宽
        CellView cellView = new CellView();
        cellView.setAutosize(true); //设置自动大小
        sheet.setColumnView(1, cellView);//根据内容自动设置列宽
        */
        Label Gradle = new Label(0,1,"年级",colorFormat);
        sheet.addCell(Gradle);

        Label Instructor = new Label(1,1,"辅导员",colorFormat);
        sheet.addCell(Instructor);

        Label Classes = new Label(2,1,"专业班级",colorFormat);
        sheet.addCell(Classes);

        Label Teacher = new Label(3,1,"任课教师",colorFormat);
        sheet.addCell(Teacher);

        Label Absences = new Label(4,1,"缺课人数",colorFormat);
        sheet.addCell(Absences);

        Label AllNumbers = new Label(5,1,"班级总人数",colorFormat);
        sheet.addCell(AllNumbers);

        Label Percent = new Label(6,1,"出勤率",colorFormat);
        sheet.addCell(Percent);

        Label Persons = new Label(7,1,"缺课人数",colorFormat);
        sheet.addCell(Persons);

        ArrayList<Integer> Wideth = new ArrayList<>();

        //这里使用setColumnView方法给每一列设置宽度
        //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
        //sheet.getCell(i, j)则是获取第j行第i列
        for(int i = 0; i < 8; i++){
            Wideth.add(sheet.getCell(i, 1).getContents().length()*2+6);
            sheet.setColumnView(i, sheet.getCell(i, 1).getContents().length()*2+6);
        }

        //获取数据库中的所有信息
        Collection<Record> allRecord = recordSerivce.getAllRecords1();

        //确定需要合并单元格的位置
        LinkedList<Integer> pect1 = new LinkedList<>();
        LinkedList<Integer> pect2 = new LinkedList<>();
        Object[] list = allRecord.toArray();
        for(int i = 1; i < allRecord.size(); i++){
            Record prerecord = (Record) list[i-1];
            Record record = (Record) list[i];
            if(!record.getGradle().equalsIgnoreCase(prerecord.getGradle())){
                pect1.add(i+1);
            }
            if(!ConvertDao.getTeacherbyClassName(record.getClasss()).equalsIgnoreCase(ConvertDao.getTeacherbyClassName(prerecord.getClasss()))){
                pect2.add(i+1);
            }
        }
        pect1.add(allRecord.size()+1);
        pect2.add(allRecord.size()+1);

        //设置没一行的信息
        int initRow = 2;

        WritableFont color1 = new WritableFont(WritableFont.createFont("仿宋"), 12);//选择字体
        color1.setColour(Colour.BLACK);
        WritableCellFormat colorFormat1 = new WritableCellFormat(color1);//生成一个单元格样式控制对象
        colorFormat1.setAlignment(jxl.format.Alignment.CENTRE);
        colorFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        colorFormat1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

        for(Record record:allRecord){
            Label gradle = new Label(0,initRow,record.getGradle(), colorFormat1);
            sheet.addCell(gradle);

            Label instructor = new Label(1,initRow,ConvertDao.getTeacherbyClassName(record.getClasss()),colorFormat1);
            sheet.addCell(instructor);

            Label classes = new Label(2,initRow,record.getClasss(),colorFormat1);
            sheet.addCell(classes);

            Label teacher = new Label(3,initRow,record.getTeacher(),colorFormat1);
            sheet.addCell(teacher);

            Label absences = new Label(4,initRow,record.getAbsence(),colorFormat1);
            sheet.addCell(absences);

            Label allNumbers = new Label(5,initRow,record.getAllNumber(),colorFormat1);
            sheet.addCell(allNumbers);

            //百分比转化
            int all = Integer.parseInt(record.getAllNumber());
            int abs = Integer.parseInt(record.getAbsence());
            double perc = ((all-abs)*1.0)/(all*1.0);
            perc = perc * 100;
            int perce = (int)perc;
            Label percent = new Label(6,initRow,String.valueOf(perce)+"%",colorFormat1);
            sheet.addCell(percent);

            Label persons = new Label(7,initRow,record.getPerson().split("（")[0],colorFormat1);
            sheet.addCell(persons);

            //这里使用setColumnView方法给每一列设置宽度
            //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
            //sheet.getCell(i, j)则是获取第j行第i列
            for(int i = 0; i < 8; i++){
                int k = sheet.getCell(i, initRow).getContents().length()*2+6;
                if(k > Wideth.get(i)){
                    Wideth.set(i, k);
                }
            }

            initRow++;
        }

        //这里使用setColumnView方法给每一列设置宽度
        //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
        //sheet.getCell(i, j)则是获取第j行第i列
        for(int i = 0; i < 8; i++){
            sheet.setColumnView(i, Wideth.get(i));
        }

        //合并单元格
        int startRow = 2;
        for(Integer index:pect1){
            sheet.mergeCells(0, startRow, 0, index);
            startRow = index + 1;
        }
        startRow = 2;
        for(Integer index:pect2){
            sheet.mergeCells(1, startRow, 1, index);
            startRow = index + 1;
        }

        workbook.write();
        workbook.close();
        os.close();
    }

    public void createExcelForTwo(WordRecordSerivce recordSerivce, String args) throws Exception
    {
        //FileInputStream io1 = new FileInputStream("F:\\work\\template\\"+"signal1.xls");
        FileInputStream io1 = new FileInputStream("/tmp/word/template/signal1.xls");
        //创建工作薄
        Workbook wb1 = Workbook.getWorkbook(io1);
        //WritableWorkbook workbook1 = wb1.createWorkbook(new File("F:\\work\\target\\"+args+"出勤率汇总文件.xls"), wb1);
        WritableWorkbook workbook1 = wb1.createWorkbook(new File("/tmp/word/target/"+args+"出勤率汇总文件.xls"), wb1);
        //创建新的一页
        WritableSheet sheet = workbook1.createSheet("sheet2", workbook1.getNumberOfSheets());
        //构造表头
        sheet.mergeCells(0, 0, 4, 0);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
        WritableFont bold = new WritableFont(WritableFont.createFont("仿宋"),12, WritableFont.BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleFormate.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        Label title = new Label(0,0,args+"查课结果", titleFormate);
        sheet.setRowView(0, 600, false);//设置第一行的高度
        sheet.addCell(title);

        //创建要显示的具体内容
        WritableFont color = new WritableFont(WritableFont.createFont("仿宋"), 12, WritableFont.BOLD);//选择字体
        color.setColour(Colour.BLACK);
        WritableCellFormat colorFormat = new WritableCellFormat(color);//生成一个单元格样式控制对象
        colorFormat.setAlignment(jxl.format.Alignment.CENTRE);
        colorFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        colorFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        /*
        //这个方法本身没有问题
        //但是由于中文的存在，这个操作会失效，需要手动设置列宽
        CellView cellView = new CellView();
        cellView.setAutosize(true); //设置自动大小
        sheet.setColumnView(1, cellView);//根据内容自动设置列宽
        */
        Label Gradle = new Label(0,1,"年级",colorFormat);
        sheet.addCell(Gradle);

        Label Instructor = new Label(1,1,"专业班级",colorFormat);
        sheet.addCell(Instructor);

        Label Classes = new Label(2,1,"缺课人数",colorFormat);
        sheet.addCell(Classes);

        Label Teacher = new Label(3,1,"班级总人数",colorFormat);
        sheet.addCell(Teacher);

        Label Absences = new Label(4,1,"出勤率",colorFormat);
        sheet.addCell(Absences);

        ArrayList<Integer> Wideth = new ArrayList<>();

        //这里使用setColumnView方法给每一列设置宽度
        //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
        //sheet.getCell(i, j)则是获取第j行第i列
        for(int i = 0; i < 5; i++){
            Wideth.add(sheet.getCell(i, 1).getContents().length()*2+6);
            sheet.setColumnView(i, sheet.getCell(i, 1).getContents().length()*2+6);
        }

        //获取数据库中的所有信息
        Collection<Record> allRecord = recordSerivce.getAllRecords1();

        //确定需要合并单元格的位置
        LinkedList<Integer> pect1 = new LinkedList<>();
        Object[] list = allRecord.toArray();
        for(int i = 1; i < allRecord.size(); i++){
            Record prerecord = (Record) list[i-1];
            Record record = (Record) list[i];
            if(!record.getGradle().equalsIgnoreCase(prerecord.getGradle())){
                pect1.add(i+1);
            }
        }
        pect1.add(allRecord.size()+1);

        //设置每一行的信息
        int initRow = 2;

        WritableFont color1 = new WritableFont(WritableFont.createFont("仿宋"), 12);//选择字体
        color1.setColour(Colour.BLACK);
        WritableCellFormat colorFormat1 = new WritableCellFormat(color1);//生成一个单元格样式控制对象
        colorFormat1.setAlignment(jxl.format.Alignment.CENTRE);
        colorFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        colorFormat1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

        for(Record record:allRecord){
            Label gradle = new Label(0,initRow,record.getGradle(), colorFormat1);
            sheet.addCell(gradle);

            Label classes = new Label(1,initRow,record.getClasss(),colorFormat1);
            sheet.addCell(classes);

            Label absences = new Label(2,initRow,record.getAbsence(),colorFormat1);
            sheet.addCell(absences);

            Label allNumbers = new Label(3,initRow,record.getAllNumber(),colorFormat1);
            sheet.addCell(allNumbers);

            //百分比转化
            int all = Integer.parseInt(record.getAllNumber());
            int abs = Integer.parseInt(record.getAbsence());
            double perc = ((all-abs)*1.0)/(all*1.0);
            perc = perc * 100;
            int perce = (int)perc;
            Label percent = new Label(4,initRow,String.valueOf(perce)+"%",colorFormat1);
            sheet.addCell(percent);

            //这里使用setColumnView方法给每一列设置宽度
            //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
            //sheet.getCell(i, j)则是获取第j行第i列
            for(int i = 0; i < 5; i++){
                int k = sheet.getCell(i, initRow).getContents().length()*2+6;
                if(k > Wideth.get(i)){
                    Wideth.set(i, k);
                }
            }
            initRow++;
        }

        //这里使用setColumnView方法给每一列设置宽度
        //需要注意的一点是sheet.setColumnView(i,j)第一个i是第几列（你要改的那一列）
        //sheet.getCell(i, j)则是获取第j行第i列
        for(int i = 0; i < 5; i++){
            sheet.setColumnView(i, Wideth.get(i));
        }

        //合并单元格
        int startRow = 2;
        for(Integer index:pect1){
            sheet.mergeCells(0, startRow, 0, index);
            startRow = index + 1;
        }

        workbook1.write();
        workbook1.close();
        io1.close();
    }
}
