package com.ocean.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IserviceShares {
    List findShares();
    List findShares(int top);
}
