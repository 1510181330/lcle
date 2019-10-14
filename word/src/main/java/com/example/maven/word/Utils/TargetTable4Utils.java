package com.example.maven.word.Utils;

import com.example.maven.word.Entities.Person.FreeClass;
import com.example.maven.word.Entities.Person.TargetTable;
import com.example.maven.word.Service.FreeClassService;
import com.example.maven.word.Service.PersonService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TargetTable4Utils {

    //人数较少时使用
    public TargetTable getTargetTable(PersonService personService, FreeClassService freeClassService)
    {
        TypeUtils utils = new TypeUtils();
        Collection<FreeClass> allFreeClass = freeClassService.getAllFreeClass();
        ArrayList<Queue<Integer>> SQueue = new ArrayList<Queue<Integer>>(20);
        for(int i = 0; i < 20; i++)
        {
            Queue<Integer> queue = new LinkedList<Integer>();
            SQueue.add(queue);
        }
        for(FreeClass fc:allFreeClass)
        {
            LinkedList<Integer> we = new LinkedList<Integer>();
            we = utils.ExchangeToList(fc);
            for(int i = 0; i < 20; i++)
            {
                if(we.get(i) == 1)
                {
                    int personId = fc.getId();
                    Integer peId = personId;
                    SQueue.get(i).add(peId);
                }
            }
        }
        TargetTable tTable = new TargetTable();
        tTable.setPerId(new LinkedList<Integer>());
        tTable.setPerName(new LinkedList<String>());
        tTable.setPerPhone(new LinkedList<String>());
        for(int i = 0; i < 20; i++)
        {
            tTable.getPerId().add(i);
            tTable.getPerName().add("no people");
            tTable.getPerPhone().add("no phone");
        }
        Queue<Integer> Visit = new LinkedList<Integer>();
        for(Integer ele:Visit)
        {
            ele = -1;
        }
        LinkedList<Integer> questList = utils.GetList(SQueue);
        for(int i = 0; i < 20; i++) {
            System.out.println("Class->" + questList.get(i) + "queue->" + SQueue.get(questList.get(i)));
            while (!SQueue.get(questList.get(i)).isEmpty()) {
                Integer tId = SQueue.get(questList.get(i)).poll();
                if (Visit.contains(tId)) {
                    ;
                } else {
                    String tName = personService.getPersonById(tId).getName();
                    System.out.println("tId :" + tId + "tName :" + tName);
                    tTable.getPerId().set(questList.get(i), tId);
                    tTable.getPerName().set(questList.get(i), tName);
                    tTable.getPerPhone().set(questList.get(i), personService.getPersonById(tId).getPhone());
                    Visit.add(tId);
                    break;
                }
            }
        }
        return tTable;
    }


    //人数较多时使用
    public TargetTable getTargetTable1(PersonService personService, FreeClassService freeClassService)
    {
        TypeUtils utils = new TypeUtils();
        Collection<FreeClass> allFreeClass = freeClassService.getAllFreeClass();
        ArrayList<Queue<Integer>> SQueue = new ArrayList<Queue<Integer>>(20);
        for(int i = 0; i < 20; i++)
        {
            Queue<Integer> queue = new LinkedList<Integer>();
            SQueue.add(queue);
        }
        for(FreeClass fc:allFreeClass)
        {
            LinkedList<Integer> we = new LinkedList<Integer>();
            we = utils.ExchangeToList(fc);
            for(int i = 0; i < 20; i++)
            {
                if(we.get(i) == 1)
                {
                    int personId = fc.getId();
                    Integer peId = personId;
                    SQueue.get(i).add(peId);
                }
            }
        }
        TargetTable tTable = new TargetTable();
        tTable.setPerId(new LinkedList<Integer>());
        tTable.setPerName(new LinkedList<String>());
        tTable.setPerPhone(new LinkedList<String>());
        tTable.setPerId1(new LinkedList<Integer>());
        tTable.setPerName1(new LinkedList<String>());
        tTable.setPerPhone1(new LinkedList<String>());
        for(int i = 0; i < 20; i++)
        {
            tTable.getPerId().add(i);
            tTable.getPerName().add("no people");
            tTable.getPerPhone().add("no phone");
            tTable.getPerId1().add(i);
            tTable.getPerName1().add("no people");
            tTable.getPerPhone1().add("no phone");
        }
        Queue<Integer> Visit = new LinkedList<Integer>();
        for(Integer ele:Visit)
        {
            ele = -1;
        }
        LinkedList<Integer> questList = utils.GetList(SQueue);
        for(int i = 0; i < 20; i++) {
            System.out.println("Class->" + questList.get(i) + "queue->" + SQueue.get(questList.get(i)));
            while (!SQueue.get(questList.get(i)).isEmpty()) {
                Integer tId = SQueue.get(questList.get(i)).poll();
                if (Visit.contains(tId)) {
                    ;
                } else {
                    String tName = personService.getPersonById(tId).getName();
                    System.out.println("tId :" + tId + "tName :" + tName);
                    tTable.getPerId().set(questList.get(i), tId);
                    tTable.getPerName().set(questList.get(i), tName);
                    tTable.getPerPhone().set(questList.get(i), personService.getPersonById(tId).getPhone());
                    Visit.add(tId);
                    break;
                }
            }
            while (!SQueue.get(questList.get(i)).isEmpty()) {
                Integer tId1 = SQueue.get(questList.get(i)).poll();
                if (Visit.contains(tId1)) {
                    ;
                } else {
                    String tName1 = personService.getPersonById(tId1).getName();
                    System.out.println("tId1 :" + tId1 + "tName1 :" + tName1);
                    tTable.getPerId1().set(questList.get(i), tId1);
                    tTable.getPerName1().set(questList.get(i), tName1);
                    tTable.getPerPhone1().set(questList.get(i), personService.getPersonById(tId1).getPhone());
                    Visit.add(tId1);
                    break;
                }
            }
        }
        return tTable;
    }
}
