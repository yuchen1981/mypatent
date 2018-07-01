package com.ocean.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class indexMapper {

    @Autowired

    private JdbcTemplate jdbcTemplate;
    @Transactional(readOnly = true)
    public  List<Map<String, Object>> getindexServlet()
    {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select  sharescode,sharesname,count(sharesname) as count from sharesinfo where 1=1 group by sharesname,sharescode");

        return maps;
    }
    public int add(int i,int c)
    {
        return i+c;
    }
}
