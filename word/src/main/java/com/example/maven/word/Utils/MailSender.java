package com.example.maven.word.Utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class MailSender {

    //处理由于中文文件名引发的邮件附件异常
    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
        System.setProperty("mail.mime.charset", "UTF-8");
    }

    public void senderMailWtihDoc(String filename, String mail, JavaMailSenderImpl javasender, int type) throws Exception
    {
        MimeMessage message = javasender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("通知");
        helper.setText("佛前一跪三千年，可未见我佛心生怜");
        helper.setTo(mail);
        helper.setFrom("1510181330@qq.com");
        //helper.addAttachment(filename+"汇总表"+".docx", new File("/tmp/word/target/"+filename+"汇总表"+".docx"));
        helper.addAttachment(filename+"汇总表"+".docx", new File("/tmp/word/target/"+filename+"汇总表"+".docx"));
        helper.addAttachment(filename+"出勤率汇总文件（含辅导员）.xls", new File("/tmp/word/target/"+filename+"出勤率汇总文件（含辅导员）.xls"));
        helper.addAttachment(filename+"出勤率汇总文件.xls", new File("/tmp/word/target/"+filename+"出勤率汇总文件.xls"));
        //javasender.send(message);
        //type为1代表执行发送查课文件，此时需要给总邮箱发送，
        //type为2代表执行发送值班表文件，此时不需要给总邮箱发送，
        if(type==1) {
            helper.setTo("18838947769@163.com");
            javasender.send(message);
        }
    }

    public void senderMailWtihDoc1(String filename, String mail, JavaMailSenderImpl javasender, int type) throws Exception
    {
        MimeMessage message = javasender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("通知");
        helper.setText("佛前一跪三千年，可未见我佛心生怜");
        helper.setFrom("1510181330@qq.com");
        helper.addAttachment(filename+".docx", new File("/tmp/word/target/"+filename+".docx"));
        //helper.addAttachment(filename+".docx", new File("F:\\work\\target\\"+filename+".docx"));
        //javasender.send(message);
        //type为1代表执行发送查课文件，此时需要给总邮箱发送，
        //type为2代表执行发送值班表文件，此时不需要给总邮箱发送，
        if(type==1) {
            helper.setTo("18838947769@163.com");
            javasender.send(message);
        }
        else{
            helper.setTo(mail);
            javasender.send(message);
        }
    }
}
