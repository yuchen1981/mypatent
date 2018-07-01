package com.ocean.controller;

import com.ocean.dao.indexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


@WebServlet(name = "IndexServlet",urlPatterns = "/hello")
public class IndexServlet  extends HttpServlet {
    private ApplicationContext context;
    @Autowired
    private indexMapper ind;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        List<Map<String, Object>> maps=ind.getindexServlet();
        try {
            Class cls = Class.forName("com.ocean.dao.indexMapper");
            final Method getindexServlet = cls.getDeclaredMethod("add",new Class[]{int.class,int.class});
            int a=(int) getindexServlet.invoke(cls.newInstance(),40,23);
            resp.addHeader("Content-Type","application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(maps);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        this.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ind=(com.ocean.dao.indexMapper) context.getBean("indexMapper");
    }
}
