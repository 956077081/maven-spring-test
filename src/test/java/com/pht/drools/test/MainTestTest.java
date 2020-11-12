package com.pht.drools.test;

import com.pht.drools.entity.Senor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.management.Sensor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/spring-*.xml"})
public class MainTestTest {
    @Autowired
    KieSession kieSession;
    @Autowired
    KieBase kieBase;
    @Test
    public void  test1(){
        List list = new ArrayList<>();
        kieSession.setGlobal("list", list);
        kieSession.insert(new BigDecimal(1.5));
         kieSession.fireAllRules();
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }

    }
    @Test
    public void testaccu(){
        Senor senor1 = new Senor("1",13.4);
        Senor senor2 = new Senor("1",26.4);
        Senor senor3 = new Senor("3",30);
        kieSession.insert(senor1);
        kieSession.insert(senor2);
        kieSession.insert(senor3);
        kieSession.fireAllRules();
    }

}