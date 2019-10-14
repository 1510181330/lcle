package com.example.maven.word.Utils;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.example.maven.word.Entities.Person.TargetTable;
import com.example.maven.word.Entities.WordEntities.*;
import com.example.maven.word.Service.ClassToTeacherMapperService;
import com.example.maven.word.Service.WordRecordSerivce;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import com.example.maven.word.Entities.WordEntities.Record;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Params_Utils {

    public PaymentData getParams(String[] args, String cruuentWeek, ClassToTeacherMapperService ConvertDao, WordRecordSerivce recordSerivce)
    {
        PaymentData datas = new PaymentData();
        TypeUtils utils = new TypeUtils();
        //设置一些样式
        TableStyle rowStyle = new TableStyle();
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);

        //设置第一个表格
        TableDetail tabledetail = new TableDetail();

        LinkedList<RowRenderData> lessions = new LinkedList<RowRenderData>();

        //设置第二个表格
        RowRenderData header = utils.getTableHeader();
        header.setRowStyle(rowStyle);

        LinkedList<RowRenderData> instructors = new LinkedList<RowRenderData>();

        for(String arg:args){
            RowBean r1 = new RowBean();
            InstructorRowBean ist1 = new InstructorRowBean();
            String[] params = arg.split("%");

            datas.setWord_week(cruuentWeek);
            datas.setWord_laber1(params[0]);
            datas.setWord_laber2(params[1]);
            datas.setWord_laber3(params[2]);
            Integer allNumber = ConvertDao.getNumberByClassName(params[4]);
            //无人请假则不写请假这一项
            String level = "";
            if(!params[10].equals("无"))
            {
                level = "（请假："+params[10].replace("+", "、")+"）";
            }
            WordBean w1 = new WordBean(params[3]+"级", params[4], "第"+cruuentWeek+"周", allNumber.toString(), params[5], params[7], params[6], params[8], params[9].replace("+", "、")+level);
            //制作文件之前保存此次查课记录，便于制作值班总表
            Record record = utils.WordBeanToRecord(w1);
            recordSerivce.save(record);
            r1 = utils.StringtoText(w1);
            RowRenderData lession1 = RowRenderData.build(r1.getGradle(), r1.getClasss(), r1.getWeek(), r1.getAllNumber(), r1.getSection(), r1.getLession(), r1.getTeacher(), r1.getAbsence(), r1.getPerson());
            lession1.setRowStyle(rowStyle);
            lessions.add(lession1);
            //设置第一个班级信息
            String instructor = ConvertDao.getTeacherbyClassName(params[4]);
            ist1 = utils.StringtoText1(r1, instructor);
            RowRenderData instructor1 = RowRenderData.build(ist1.getGradle(), ist1.getInstructor(), ist1.getClasss(), ist1.getTeacher(), ist1.getAbsence(), ist1.getAllNumber(), ist1.getPerson());
            instructors.add(instructor1);

        }
        tabledetail.setBody1(lessions);
        datas.setTabledetail(tabledetail);
        MiniTableRenderData minitable = new MiniTableRenderData(header, instructors, 24.65f);
        datas.setMiniTableDate(minitable);
        return datas;
    }

    public PaymentData getParams_totle(String cruuentWeek, ClassToTeacherMapperService ConvertDao, WordRecordSerivce recordSerivce)
    {
        PaymentData datas = new PaymentData();
        TypeUtils utils = new TypeUtils();
        //设置一些样式
        TableStyle rowStyle = new TableStyle();
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);

        //设置第一个表格
        TableDetail tabledetail = new TableDetail();

        //汇总表每一行数据信息
        LinkedList<RowRenderData> lessions = new LinkedList<RowRenderData>();

        //从数据库中获取当前能拿到的所有数据信息
        Collection<Record> allRecords = recordSerivce.getAllRecords();

        //设置周次
        datas.setWord_week(cruuentWeek);

        //制作汇总文件（word）
        for(Record record:allRecords){
            RowBean r1 = new RowBean();
            record.setWeek(cruuentWeek);
            if(record.getPerson().contains("请假")&&!record.getPerson().startsWith("（")){
                String target = "";
                String[] res = record.getPerson().split("（");
                target = res[0] + "\n" + "（" + res[1];
                record.setPerson(target);
            }
            WordBean w1 = new WordBean(record.getGradle(), record.getClasss(), record.getWeek(), record.getAllNumber(), record.getSection(), record.getLession(), record.getTeacher(), record.getAbsence(), record.getPerson());
            r1 = utils.StringtoText(w1);
            RowRenderData lession1 = RowRenderData.build(r1.getGradle(), r1.getClasss(), r1.getWeek(), r1.getAllNumber(), r1.getSection(), r1.getLession(), r1.getTeacher(), r1.getAbsence(), r1.getPerson());
            lession1.setRowStyle(rowStyle);
            lessions.add(lession1);
        }
        tabledetail.setBody1(lessions);
        datas.setTabledetail(tabledetail);
        return datas;
    }

    public String getFileName(String[] args)
    {
        String filename = "";
        String[] params = args[0].split("%");
        filename = filename + params[3]+"级" + params[4] + "查课结果";
        return filename;
    }

    public HashMap<String, Object> GetDutymap(TargetTable table) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        LinkedList<String> name1 = table.getPerName();
        LinkedList<String> phone1 = table.getPerPhone();
        LinkedList<String> name2 = table.getPerName1();
        LinkedList<String> phone2 = table.getPerPhone1();
        for (int i = 0; i < 20; i++) {
            String local = "name" + i + "_1";
            result.put(local, name1.get(i));
            local = "phone" + i + "_1";
            result.put(local, phone1.get(i));
            local = "name" + i + "_2";
            result.put(local, name2.get(i));
            local = "phone" + i + "_2";
            result.put(local, phone2.get(i));
        }
        return result;
    }

    public HashMap<String, Object> GetDutymap1(TargetTable table) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        LinkedList<String> name1 = table.getPerName();
        LinkedList<String> phone1 = table.getPerPhone();
        for (int i = 0; i < 20; i++) {
            String local = "name" + i + "_1";
            result.put(local, name1.get(i));
            local = "phone" + i + "_1";
            result.put(local, phone1.get(i));
        }
        return result;
    }
}
