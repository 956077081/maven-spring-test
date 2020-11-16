package com.pht.drools;


import org.apache.ibatis.javassist.runtime.Inner;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.internal.utils.KieService;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DroolTest {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\文档\\新建文件夹\\吴江\\风控方案和决策文件\\借款人规则文件\\3.0\\WJRCB_JK3.0_额度计算_决策表.xls");
        FileReader fileInputStream = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb);
        bufferedReader.close();
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        knowledgeBuilder.add(ResourceFactory.newByteArrayResource(sb.toString().getBytes()), ResourceType.DRL);
        knowledgeBuilder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            System.out.println(knowledgeBuilder.getErrors().toString());
        }
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());
        KieSession knowledgeSession = knowledgeBase.newKieSession();
        List<Object> list = new ArrayList<>();
        knowledgeSession.setGlobal("list", list);
        knowledgeSession.insert(new BigDecimal(1.5));
        knowledgeSession.fireAllRules();
        list.forEach(item -> System.out.println(item));

    }
    @Test
    public void test2() {
        try {
//            load up the knowledge base
            InternalKnowledgeBase kbase = readKnowledgeBase();
            KieSession ksession = kbase.newKieSession();
            ItemCity item1 = new ItemCity();
            item1.setPurchaseCity(ItemCity.City.PUNE);
            item1.setTypeofItem(ItemCity.Type.MEDICINES);
            item1.setSellPrice(new BigDecimal(10));
            ksession.insert(item1);
            ItemCity item2 = new ItemCity();
            item2.setPurchaseCity(ItemCity.City.PUNE);
            item2.setTypeofItem(ItemCity.Type.GROCERIES);
            item2.setSellPrice(new BigDecimal(10));
            ksession.insert(item2);
            ItemCity item3 = new ItemCity();
            item3.setPurchaseCity(ItemCity.City.NAGPUR);
            item3.setTypeofItem(ItemCity.Type.MEDICINES);
            item3.setSellPrice(new BigDecimal(10));
            ksession.insert(item3);
            ItemCity item4 = new ItemCity();
            item4.setPurchaseCity(ItemCity.City.NAGPUR);
            item4.setTypeofItem(ItemCity.Type.GROCERIES);
            item4.setSellPrice(new BigDecimal(10));
            ksession.insert(item4);
//            QueryResults queryResults = ksession.getQueryResults("typeofitem==MEDICINES", item1);
//            System.out.println(queryResults);
            ksession.fireAllRules();
            System.out.println(item1.getPurchaseCity().toString() + " " + item1.getLocalTax().intValue());
            System.out.println(item2.getPurchaseCity().toString() + " " + item2.getLocalTax().intValue());
            System.out.println(item3.getPurchaseCity().toString() + " " + item3.getLocalTax().intValue());
            System.out.println(item4.getPurchaseCity().toString() + " " + item4.getLocalTax().intValue());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static InternalKnowledgeBase readKnowledgeBase() throws Exception {
        KieServices kieServices = KieServices.Factory.get();

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource("D:\\javaproject\\重构\\mavenrtest2\\src\\main\\java\\com\\pht\\drools\\Pune.drl"), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newFileResource("D:\\javaproject\\重构\\mavenrtest2\\src\\main\\java\\com\\pht\\drools\\Nagpur.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

}
