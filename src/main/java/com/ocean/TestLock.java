package com.ocean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLock extends Thread{



    int Qucount=0;

    public  TestLock(int qucount)
    {
        Qucount=qucount;
    }
    static int count=500000;
    private static   ConcurrentHashMap<String,Integer> concurrentHashMap;

    public static void main(String[] args) {

        String str="abc";
        System.out.println(str.hashCode());
        str=str+"defghivklvcxzsdefghivklvcxzsdefghivklvcxzsdefghivklvcxzsdefghivklvcxzsdefghivklvcxzs";
        System.out.println(str.hashCode());
        concurrentHashMap=new ConcurrentHashMap();
         Integer a = concurrentHashMap.putIfAbsent("a", 1);
        a=concurrentHashMap.putIfAbsent("a", 1);
        concurrentHashMap.put("c", 34);
        concurrentHashMap.put("d", 345);
        final Integer a1 = concurrentHashMap.put("b", 3);
        concurrentHashMap.forEach(2,(k,v)->System.out.println(k + "->" + v));
        for(Map.Entry<String,Integer> asd : concurrentHashMap.entrySet())
        {
           System.out.println(asd.getKey());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        TestLock t1=new TestLock(200);
        //t1.setName("t1");
        TestLock t2=new TestLock(200);
        //t2.setName("t2");
        TestLock t3=new TestLock(200);
        //t3.setName("t3");

        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);

    }

    @Override
    public void run() {
        loke(Thread.currentThread().getName());
//        while (true) {
//            synchronized ("acs") {
//                if (count > 0) {
//                    count = count - Qucount;
//                    System.out.println(Thread.currentThread().getName() + "取走" + Qucount + "元，余额为：" + count);
//
//                } else {
//                    System.out.println("余额不足！");
//                    break;
//                }
//            }
//        }
    }
    //死锁现象
    public void loke(String thname)
    {
        if("pool-1-thread-1".equals(Thread.currentThread().getName()))
        {
            synchronized("遥控器")
            {
                System.out.println(thname+"拿到遥控器");
                synchronized("电池")
                {
                    System.out.println(thname+"拿到电池，并吹空调！");
                }
            }
        }
        else {
            synchronized("电池")
            {
                System.out.println(thname+"拿到电池");
                synchronized("遥控器")
                {
                    System.out.println(thname+"遥控器，并吹空调！");
                }
            }
        }
    }
}
