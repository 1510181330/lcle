package com.example.maven.word.Utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.example.maven.word.Entities.WordEntities.PaymentData;

import java.io.FileOutputStream;
import java.util.HashMap;

public class Word_Utils {

    public void createWordTable(String filename, PaymentData datas) throws Exception{
        //完成修改表格



        //linux
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("/tmp/word/template/signal.docx", config).render(datas);

        //如果没有指定文件，则会生成一个新文件，有的话，会覆盖源文件
        FileOutputStream out = new FileOutputStream("/tmp/word/target/"+filename+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();



        /*
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("F:\\work\\template\\signal.docx", config).render(datas);

        //如果没有指定文件，则会生成一个新文件，有的话，会覆盖源文件
        FileOutputStream out = new FileOutputStream("F:\\work\\target\\"+filename+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
        */



    }

    public void CreateDutyTable(HashMap<String, Object> param, String filename, String filename1) throws Exception{



        //linux
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("/tmp/word/template/"+filename+".docx", config).render(param);
        FileOutputStream out = new FileOutputStream("/tmp/word/target/"+filename1+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();



        /*
        XWPFTemplate template = XWPFTemplate.compile("F:\\work\\template\\"+filename+".docx").render(param);
        FileOutputStream out = new FileOutputStream("F:\\work\\duty\\"+filename1+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
        */



    }

    //生成汇总文件(word)
    public void createWordTableTotle(String filename, PaymentData datas) throws Exception{
        //完成修改表格


        //linux
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("/tmp/word/template/totle.docx", config).render(datas);

        //如果没有指定文件，则会生成一个新文件，有的话，会覆盖源文件
        FileOutputStream out = new FileOutputStream("/tmp/word/target/"+filename+"汇总表"+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();



        /*
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("F:\\work\\template\\totle.docx", config).render(datas);

        //如果没有指定文件，则会生成一个新文件，有的话，会覆盖源文件
        FileOutputStream out = new FileOutputStream("F:\\work\\target\\"+filename+"汇总表"+".docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
        */


    }
}
