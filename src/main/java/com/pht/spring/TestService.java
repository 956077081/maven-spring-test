package com.pht.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {
    @Autowired
    CmsHelp cmsHelp;
    public Map getDataTest(){
        Map data = cmsHelp.getData();
        return data;
    }


}
