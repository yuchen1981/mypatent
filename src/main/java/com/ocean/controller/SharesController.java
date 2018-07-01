package com.ocean.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.dao.SharesMapper;
import com.ocean.dao.indexMapper;

import com.ocean.service.IserviceShares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/use"})
public class SharesController {

    @Autowired
    private IserviceShares iserviceShares;
    @RequestMapping(value = "/getallShares",method = RequestMethod.GET)
    public List getallShares()
    {
        List shares = iserviceShares.findShares();
        return shares;
    }

    @Autowired
    private indexMapper ind;

    @RequestMapping(value = "/getcountShares",method = RequestMethod.GET)
    public List getcountShares()
    {
        List<Map<String, Object>> maps = ind.getindexServlet();
        return  maps;
    }
    @RequestMapping(value = "/gettopshares",method = RequestMethod.GET)
    public List gettopshares(@RequestParam(name="top") Integer  top)
    {
        List list=iserviceShares.findShares(top);
        return list;
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SharesMapper m;
    @RequestMapping(value = "/getshnum",method = RequestMethod.GET)
    public  PageInfo<Map<String,Object>> getshnum(@RequestParam(name = "indexpage") int indexpage,@RequestParam(name="pagesize") int pagesize)
    {
         List<Map<String, Object>> list =  jdbcTemplate.queryForList("select  sharescode,sharesname,count(sharesname) as count from sharesinfo where 1=1 group by sharesname,sharescode");
         System.out.println(list);

        PageHelper.startPage(indexpage, pagesize);
        List<Map<String,Object>> p= m.showshnum();
        PageInfo<Map<String,Object>> page = new PageInfo(p);
       return page;
    }
}
