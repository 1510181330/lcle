package com.example.maven.word.Utils;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.example.maven.word.Entities.Person.*;
import com.example.maven.word.Entities.WordEntities.InstructorRowBean;
import com.example.maven.word.Entities.WordEntities.RowBean;
import com.example.maven.word.Entities.WordEntities.WordBean;
import com.example.maven.word.Entities.WordEntities.Record;

import java.util.*;

public class TypeUtils {


    public LinkedList<DisPerson> PersonToDisPerson(Collection<Person> allemp)
    {
        LinkedList<DisPerson> newallemp = new LinkedList<DisPerson>();
        for(Person person:allemp){
            DisPerson p1 = new DisPerson();
            p1.setId(person.getId());
            p1.setName(person.getName());
            p1.setPassword(person.getPassword());
            if(person.getSex()==1){
                p1.setSex("男");
            }
            else{
                p1.setSex("女");
            }
            p1.setPhone(person.getPhone());
            p1.setDepname(person.getDepname());
            p1.setQQ(person.getQQ());
            newallemp.add(p1);
        }
        return newallemp;
    }

    //组装需要装入mapper的对象
    public Person GetUpdatePerson(String param, Person oldperson)
    {
        String[] args = param.split("%");
        oldperson.setName(args[1]);
        oldperson.setPassword(args[2]);
        if(args[3].equals("男")){
            oldperson.setSex(1);
        }else{
            oldperson.setSex(0);
        }
        oldperson.setQQ(args[4]);
        oldperson.setPhone(args[5]);
        oldperson.setDepname(args[6]);
        return oldperson;
    }
    //对于传入的String包装成text对象，设置对应的格式(针对第一个表格)
    //设置字体：仿宋，大小：小四
    public RowBean StringtoText(WordBean ww){

        //设置字体格式
        Style textStyle = new Style();
        textStyle.setFontFamily("仿宋");
        textStyle.setFontSize(12);

        //对于每一个字段的值，设置内容与字体
        TextRenderData gradle = new TextRenderData();
        gradle.setText(ww.getStr1());
        gradle.setStyle(textStyle);

        //字段：班级
        TextRenderData classs = new TextRenderData();
        classs.setText(ww.getStr2());
        classs.setStyle(textStyle);

        //字段：周次
        TextRenderData week = new TextRenderData();
        week.setText(ww.getStr3());
        week.setStyle(textStyle);

        //字段：总人数
        TextRenderData allNumber = new TextRenderData();
        allNumber.setText(ww.getStr4());
        allNumber.setStyle(textStyle);

        //字段：周几第几大节
        TextRenderData section = new TextRenderData();
        section.setText(ww.getStr5());
        section.setStyle(textStyle);

        //字段：课程名称
        TextRenderData lession = new TextRenderData();
        lession.setText(ww.getStr6());
        lession.setStyle(textStyle);

        //字段：教师姓名
        TextRenderData teacher = new TextRenderData();
        teacher.setText(ww.getStr7());
        teacher.setStyle(textStyle);

        //字段：缺课人数
        TextRenderData absence = new TextRenderData();
        absence.setText(ww.getStr8());
        absence.setStyle(textStyle);

        //字段：缺课人员名单（含请假人员名单）
        TextRenderData person = new TextRenderData();
        person.setText(ww.getStr9());
        person.setStyle(textStyle);

        RowBean rr = new RowBean(gradle, classs, week, allNumber, section, lession, teacher, absence, person);
        return rr;
    }

    //对于传入的String包装秤text对象，设置对应的格式(针对第二个表格)
    //设置字体：仿宋，大小：小四
    public InstructorRowBean StringtoText1(RowBean ww, String instructor) {

        //设置字体：仿宋 大小：小四
        Style textStyle = new Style();
        textStyle.setFontFamily("仿宋");
        textStyle.setFontSize(12);

        //由于第二表的格式和第一个表极其相似，我们重用rowBean中的内容
        //但是辅导员姓名需要自定义，依赖传入的String
        TextRenderData Instructor = new TextRenderData();
        Instructor.setStyle(textStyle);
        Instructor.setText(instructor);

        InstructorRowBean instructorowBeanr = new InstructorRowBean(ww.getGradle(), Instructor, ww.getClasss(), ww.getTeacher(), ww.getAbsence(), ww.getAllNumber(), ww.getPerson());
        return instructorowBeanr;
    }

    //获取第二个表格的伪表头，返回RowRenderData对象
    //放弃动态生成，使用miniTable生成简单表格
    public RowRenderData getTableHeader()
    {

        //设置字体：华文仿宋 大小：四号 加粗
        Style textStyle = new Style();
        textStyle.setFontFamily("华文仿宋");
        textStyle.setFontSize(14);
        textStyle.setBold(true);

        TextRenderData t1 = new TextRenderData();
        t1.setText("年级");
        t1.setStyle(textStyle);

        TextRenderData t2 = new TextRenderData();
        t2.setText("辅导员");
        t2.setStyle(textStyle);

        TextRenderData t3 = new TextRenderData();
        t3.setText("专业班级");
        t3.setStyle(textStyle);

        TextRenderData t4 = new TextRenderData();
        t4.setText("任课老师");
        t4.setStyle(textStyle);

        TextRenderData t5 = new TextRenderData();
        t5.setText("缺课人数");
        t5.setStyle(textStyle);

        TextRenderData t6 = new TextRenderData();
        t6.setText("班级总人数");
        t6.setStyle(textStyle);

        TextRenderData t7 = new TextRenderData();
        t7.setText("缺课人员");
        t7.setStyle(textStyle);

        RowRenderData rowRenderData = RowRenderData.build(t1, t2, t3, t4, t5, t6, t7);
        return rowRenderData;
    }

    //对于增加模态框传回来的字符串，转化成person对象
    public Person StringToPerson(String param)
    {
        Person person = new Person();
        String[] args = param.split("%");
        person.setName(args[0]);
        person.setPassword(args[1]);
        if(args[2].equals("男")){
            person.setSex(1);
        }
        else{
            person.setSex(0);
        }
        person.setQQ(args[3]);
        person.setPhone(args[4]);
        person.setDepname(args[5]);
        person.setPriority(0);
        return person;
    }

    public FreeClass StringToint(String[] args, int id)
    {
        int[] a = new int[20];
        int i = 0;
        for(String str:args){
            if(str.equals("1")){
                a[i] = 1;
            }
            else{
                a[i] = 0;
            }
            i++;
        }
        FreeClass freeClass = new FreeClass(id, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12], a[13], a[14], a[15], a[16], a[17], a[18], a[19]);
        return freeClass;
    }

    //方法1: 将freeclass对象转换成数组，易于处理
    public LinkedList<Integer> ExchangeToList(FreeClass freeclass)
    {
        List<Integer> we = new LinkedList<Integer>();
        if(freeclass.getC11()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC12()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC13()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC14()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC21()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC22()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }

        if(freeclass.getC23()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC24()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC31()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC32()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }

        if(freeclass.getC33()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC34()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC41()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC42()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC43()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC44()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC51()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC52()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC53()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        if(freeclass.getC54()==1)
        {
            we.add(1);
        }
        else
        {
            we.add(0);
        }
        return (LinkedList<Integer>) we;
    }

    //方法2: 得到一个访问队列的顺序，尽量实现密铺
    public LinkedList<Integer> GetList(ArrayList<Queue<Integer>> SQueue)
    {
        LinkedList<Integer> beforeList = new LinkedList<Integer>();
        LinkedList<Integer> afterList = new LinkedList<Integer>();
        LinkedList<Integer> questList = new LinkedList<Integer>();
        for(Queue<Integer> queue:SQueue)
        {
            int size = queue.size();
            Integer size1 = size;
            afterList.add(size1);
            beforeList.add(size1);
        }
        for(int i = 0; i < afterList.size()-1; i++)
        {
            for(int j = (i+1); j < afterList.size(); j++)
            {
                if(afterList.get(i) > afterList.get(j))
                {
                    Integer t = afterList.get(i);
                    afterList.set(i, afterList.get(j));
                    afterList.set(j, t);
                }
            }
        }
        for(Integer local:afterList)
        {
            int index = beforeList.indexOf(local);
            beforeList.set(index, -1);
            Integer index1 = index;
            questList.add(index1);
        }
        return questList;
    }

    public Record WordBeanToRecord(WordBean wordbean)
    {
        Record record = new Record();
        record.setGradle(wordbean.getStr1());
        record.setClasss(wordbean.getStr2());
        record.setWeek(wordbean.getStr3());
        record.setAllNumber(wordbean.getStr4());
        record.setSection(wordbean.getStr5());
        record.setLession(wordbean.getStr6());
        record.setTeacher(wordbean.getStr7());
        record.setAbsence(wordbean.getStr8());
        record.setPerson(wordbean.getStr9());
        return record;
    }

}
