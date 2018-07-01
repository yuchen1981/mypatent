package com.ocean.service.Impl;

import com.ocean.dao.SharesMapper;
import com.ocean.pojo.Shares;
import com.ocean.service.IserviceShares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "sharesService")
public class SharesServiceImpl implements IserviceShares {
    @Autowired
    private SharesMapper sharesmapper;
    @Override
    public List findShares() {
        return sharesmapper.findShares();
    }

    @Override
    public List findShares(int top) {
//        List list=sharesmapper.sharestop(top);
//        for (int i=0;i<list.size();i++)
//        {
//           Shares s=(Shares)list.get(1);
//            System.out.println(s.getSharesname());
//        }
          return sharesmapper.sharestop(top);

    }
}
