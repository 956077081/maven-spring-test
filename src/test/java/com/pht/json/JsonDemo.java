package com.pht.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class JsonDemo {
    public static void main(String[] args) throws IllegalAccessException {
        String str= "11";
        Integer jsonObject = JSONObject.parseObject(str,  Integer.class);
        System.out.println(jsonObject);
        TestClass testClass = new TestClass();
//        Field[] declaredFields = testClass.getClass().getDeclaredFields();
        Field[] declaredFields = TestClass.class.getDeclaredFields();
        TestClass testClass1 = new TestClass();
        testClass1.age="1";
        testClass1.naem="2";
        for (int i = 0; i <declaredFields.length ; i++) {
            System.out.println(declaredFields[i].getName()+"--"+declaredFields[i].get(testClass1));
        }
        String password ="166563-565656";
    }
    @Test
    public void testTemplate(){
        System.out.println(  (Integer) getClassTest(String.class));

    }
    public <T> T getClassTest(Class<? extends Object> classname){
        T a =null;
        a= (T) new Integer(10);
        return a;
    }
}
class  TestClass{
    String naem;
    String age;
    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }
    public void getStrTestMethod(){

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}