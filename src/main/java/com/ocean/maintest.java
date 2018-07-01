package com.ocean;


import com.ocean.pojo.Shares;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 class formatfo  extends Applet {
    JButton b1 = new JButton("JButton 1"),
            b2 = new JButton("JButton 2");
    JTextField t = new JTextField(20);

    public void init() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name =
                        ((JButton) e.getSource()).getText();
                t.setText(name + " Pressed");
            }
        };
        b1.addActionListener(al);
        add(b1);
        b2.addActionListener(al);
        add(b2);
        add(t);
    }
}

public class maintest extends Thread{
    @Override
    public void run() {
        ConcurrentHashMap hashMap=new ConcurrentHashMap();
       for(int i=0;i<100;i++)
       {
           System.out.println("自定义线程"+this.getName());
       }
    }

    public static void main(String[] args)  {
//        maintest m=new maintest();
//        maintest m1=new maintest();
//        final ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.execute(m);  executorService.execute(m1);
        String resource = "mapping/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        Map<String,Integer> parameters=new java.util.HashMap<>();
        parameters.put("top",3);
        List<Map<String,Object>> objects = session.selectList("sharestop",parameters);
        for (Map<String,Object> a:objects) {
            System.out.println(a.get("sharesname"));
        }


    }
}
