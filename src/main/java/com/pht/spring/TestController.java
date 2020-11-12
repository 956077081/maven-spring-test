package com.pht.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestController {
    @Autowired
    TestService testService;
    @RequestMapping("/test")
    @ResponseBody
    public Object getTETS(){
        Map dataTest = testService.getDataTest();
        return dataTest;
    }

}
