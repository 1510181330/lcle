package com.example.maven.word.Controller;

import com.example.maven.word.Entities.Person.FreeClass;
import com.example.maven.word.Entities.Person.Person;
import com.example.maven.word.Entities.Person.TargetTable;
import com.example.maven.word.Entities.WordEntities.PaymentData;
import com.example.maven.word.Service.ClassToTeacherMapperService;
import com.example.maven.word.Service.FreeClassService;
import com.example.maven.word.Service.PersonService;
import com.example.maven.word.Service.WordRecordSerivce;
import com.example.maven.word.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;


import javax.servlet.http.HttpSession;

@Controller
public class opt {

    @Autowired
    private ClassToTeacherMapperService ConvertDao;

    @Autowired
    private PersonService personService;

    @Autowired
    private JavaMailSenderImpl javasender;

    @Autowired
    private FreeClassService freeClassService;

    @Autowired
    private WordRecordSerivce recordSerivce;

    private Integer localMaker = null;

    @PostMapping("userlogin")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session)
    {
        ModelAndView mv = new ModelAndView();
        String localpassword = personService.getPasswordByName(username);
        if(localpassword == null)
        {
            mv.setViewName("person/index");
            session.setAttribute("msg", "用户不存在，请找到合适的介绍人加入组织(๑•̀ㅂ•́)و✧");
            return mv;
        }
        if(localpassword.equals(password))
        {
            localMaker = personService.getPersonByName(username).getId();
            mv.setViewName("person/dashboard");
            session.setAttribute("isLogin", username);
            return mv;
        }
        else{
            mv.setViewName("person/index");
            session.setAttribute("msg", "用户名与密码不匹配(￣_,￣ )");
            return mv;
        }
    }


    //生成班级名称下拉框所需要的班级map，以及其他信息
    @ResponseBody
    @RequestMapping("getTableData")
    public Map<String, Object> GetTableData(String param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] args = param.split("-");
        int gradle = Integer.parseInt(args[4]);
        Collection<String> allClassName = ConvertDao.getClassesNameByGradle(gradle);
        result.put("allClasses", allClassName);
        return result;
    }

    //生成年级下拉框
    @ResponseBody
    @RequestMapping("getGradles")
    public Map<String, Object> getGradles(String param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Collection<Integer> gradles = ConvertDao.getAllGradles();
        result.put("gradles", gradles);
        return result;
    }

    @ResponseBody
    @RequestMapping("users_more")
    public Map<String, Object> getMoreInfo(int id)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Person p1 = personService.getPersonById(id);
        String name = personService.getMore1(p1.getName());
        String phone = personService.getMore2(p1.getName());
        result.put("name", name);
        result.put("phone", phone);
        return result;
    }

    @ResponseBody
    @RequestMapping("getNumberDownListdate")
    public Map<String, Object> getNumberDownListdate(String param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] names = param.split("=");
        for(int i = 0;  i< names.length; i++)
        {
            Integer Value = ConvertDao.getNumberByClassName(names[i]);
            String Key = names[i];
            System.out.println("key = "+Key+"  value = "+Value);
            result.put(Key, Value);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("maketable")
    public void Create(String param) throws Exception
    {
        MailSender sender = new MailSender();
        Word_Utils wordUtil = new Word_Utils();
        String[] args = param.split("=");
        Params_Utils utils = new Params_Utils();
        PaymentData datas = utils.getParams(args, "十六", ConvertDao, recordSerivce);
        String filename = utils.getFileName(args);
        wordUtil.createWordTable(filename, datas);
        String mail = personService.getPersonById(localMaker).getQQ()+"@qq.com";
        sender.senderMailWtihDoc1(filename, mail, javasender, 1);
    }

    //对于关于系统超链接的映射
    @RequestMapping("content")
    public ModelAndView GetContent()
    {
        ModelAndView mv = new ModelAndView("person/Content");
        return mv;
    }

    //对于关于系统超链接的映射
    @RequestMapping("dashboard")
    public ModelAndView GetDashboard()
    {
        ModelAndView mv = new ModelAndView("person/dashboard");
        return mv;
    }

    //对于文件生成超链接的映射
    @RequestMapping("totle")
    public ModelAndView GetTotle()
    {
        ModelAndView mv = new ModelAndView("person/totle");
        return mv;
    }

    @RequestMapping("createtable")
    public ModelAndView GetCreatetable()
    {
        ModelAndView mv = new ModelAndView("person/createtable");
        return mv;
    }

    @GetMapping("users_allemp")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("person/list");
        return mv;
    }

    @ResponseBody
    @RequestMapping("users_allempinfo")
    public Map<String, Object> GettableInfo()
    {
        TypeUtils utils = new TypeUtils();
        Map<String, Object> result = new HashMap<String, Object>();
        Collection<Person> row1 = personService.getRow1("学风督察部");
        result.put("row1", utils.PersonToDisPerson(row1));
        Collection<Person> row2 = personService.getRow1("信息管理部");
        result.put("row2", utils.PersonToDisPerson(row2));
        Collection<Person> row3 = personService.getRow1("思想教育部");
        result.put("row3", utils.PersonToDisPerson(row3));
        Collection<Person> row4 = personService.getRow1("组织宣传部");
        result.put("row4", utils.PersonToDisPerson(row4));
        Collection<Person> row5 = personService.getRow1("办公室");
        result.put("row5", utils.PersonToDisPerson(row5));
        return result;
    }


    @ResponseBody
    @RequestMapping("users_add")
    public Map<String, Object> AddPerson(String param){
        Map<String, Object> result = new HashMap<String, Object>();
        TypeUtils utils = new TypeUtils();
        Person person = utils.StringToPerson(param);
        Integer pri = personService.getPersonById(localMaker).getPriority();
        if(pri==1) {
            personService.addPerson(person);
            result.put("msg", "添加成员成功");
        }
        else{
            result.put("msg", "没有添加成员的权限，请尝试联系部门管理员");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("users_del")
    public Map<String, Object> DelPerson(int id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer pri = personService.getPersonById(localMaker).getPriority();
        if(pri==1){
            int useid = id;
            personService.delPersonById(useid);
            freeClassService.DelFreeClass(useid);
            result.put("msg", "删除成员成功");
        }
        else{
            result.put("msg", "没有删除成员的权限，请尝试联系部门管理员");
        }
        return result;

    }

    @ResponseBody
    @RequestMapping("users_update")
    public Map<String, Object> UpdatePerson(String param){
        Map<String, Object> result = new HashMap<String, Object>();
        TypeUtils utils = new TypeUtils();
        String[] args = param.split("%");
        int id = Integer.parseInt(args[0]);
        Person person = utils.GetUpdatePerson(param, personService.getPersonById(id));
        System.out.println(args[0]+"-->"+localMaker);
        Integer pri = personService.getPersonById(localMaker).getPriority();
        System.out.println("pri-->"+pri);
        System.out.println("localmaker-->"+localMaker+"*****"+args[0]);
        if(pri==1) {
            personService.UpdatePerson(person);
            result.put("msg", "修改成员信息成功");
        }
        else{
            Integer oid = Integer.parseInt(args[0]);
            System.out.println("oid-->"+oid);
            System.out.println(oid==localMaker);
            if(oid.equals(localMaker)){
                personService.UpdatePerson(person);
                result.put("msg", "修改成员信息成功");
            }
            else{
                result.put("msg", "没有修改成员的权限，请尝试联系部门管理员");
            }
        }
        return result;
    }

    @RequestMapping("users_freeclass")
    public ModelAndView GetFreeclass()
    {
        ModelAndView mv = new ModelAndView("person/freeclass");
        return mv;
    }

    @ResponseBody
    @RequestMapping("users_sendfreeClass")
    public void GetfreeClass(String param)
    {
        TypeUtils utils = new TypeUtils();
        String[] args = param.split("%");
        Person person = personService.getPersonById(localMaker);
        FreeClass freeclass = utils.StringToint(args, person.getId());
        freeClassService.saveFreeClass(freeclass);
    }

    @ResponseBody
    @RequestMapping("users_getTarget")
    public Map<String, Object> getTarget(String param) throws Exception
    {
        Map<String, Object> result = new HashMap<String, Object>();
        int pri = personService.getPersonById(localMaker).getPriority();
        if(pri==1){
            Collection<FreeClass> allfreeClass = freeClassService.getAllFreeClass();
            if(allfreeClass.size()<30){
                result.put("msg", "当前填写空课表人数不足30，系统默认匹配：每节课值班人数为一人");
                result.put("flag", 1);
                TargetTable4Utils utils = new TargetTable4Utils();
                TargetTable tTable = utils.getTargetTable(personService, freeClassService);
                result.put("name1", tTable.getPerName());
                result.put("phone1", tTable.getPerPhone());
                //发送值班表
                HashMap<String, Object> word = new HashMap<String, Object>();
                Params_Utils param_utils = new Params_Utils();
                word = param_utils.GetDutymap1(tTable);
                Word_Utils word_utils = new Word_Utils();
                word_utils.CreateDutyTable(word, "table1", "值班表1");
                MailSender sender = new MailSender();
                String mail = personService.getPersonById(localMaker).getQQ()+"@qq.com";
                String filename = "值班表1";
                sender.senderMailWtihDoc1(filename, mail, javasender, 2);
            }
            else{
                result.put("msg", "当前填写空课表人数较多，系统默认匹配：每节课值班人数为两人");
                result.put("flag", 2);
                TargetTable4Utils utils = new TargetTable4Utils();
                TargetTable tTable = utils.getTargetTable1(personService, freeClassService);
                result.put("name1", tTable.getPerName());
                result.put("phone1", tTable.getPerPhone());
                result.put("name2", tTable.getPerName1());
                result.put("phone2", tTable.getPerPhone1());
                //发送值班表
                HashMap<String, Object> word = new HashMap<String, Object>();
                Params_Utils param_utils = new Params_Utils();
                word = param_utils.GetDutymap(tTable);
                Word_Utils word_utils = new Word_Utils();
                word_utils.CreateDutyTable(word, "table2", "值班表1");
                MailSender sender = new MailSender();
                String mail = personService.getPersonById(localMaker).getQQ()+"@qq.com";
                String filename = "值班表1";
                sender.senderMailWtihDoc1(filename, mail, javasender, 2);
            }
        }
        else{
            result.put("flag", 3);
            result.put("msg", "权限不足，无法查看值班文件，请联系值班负责人索要值班表");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("judge_totle")
    public Map<String, Object> Judge_Totle(String param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        int pri = personService.getPersonById(localMaker).getPriority();
        if(pri==1){
            result.put("flag", 1);
        }
        else{
            result.put("flag", 0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("totle_files_word")
    public Map<String, Object> totle_files_word(String args) throws Exception
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Params_Utils utils = new Params_Utils();
        Word_Utils wordUtil = new Word_Utils();
        Excel_Utils excelUtil = new Excel_Utils();
        MailSender sender = new MailSender();
        PaymentData datas = utils.getParams_totle(args, ConvertDao, recordSerivce);
        String filename = args;
        wordUtil.createWordTableTotle(filename, datas);
        excelUtil.createExcelForOne(ConvertDao, recordSerivce, args);
        excelUtil.createExcelForTwo(recordSerivce, args);
        String mail = personService.getPersonById(localMaker).getQQ()+"@qq.com";
        sender.senderMailWtihDoc(filename, mail, javasender, 1);
        return result;
    }


}
