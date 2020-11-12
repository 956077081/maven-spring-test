package com.pht;

import groovy.lang.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    private static GroovyClassLoader classLoader   = new GroovyClassLoader();
    private static GroovyShell  groovyShell =new GroovyShell();
    public static void main(String[] args) {


        String function1="def map = [:]\n" +
                "\n" +
                "map.\"an identifier with a space and double quotes\" = \"ALLOWED\"\n" +
                "map.'with-dash-signs-and-single-quotes' = \"ALLOWED\"\n" +
                "\n" +
                "assert map.\"an identifier with a space and double quotes\" == \"ALLOWED\"\n" +
                "assert map.'with-dash-signs-and-single-quotes' == \"ALLOWED\"";
        GroovyShell  groovyShell =new GroovyShell();
        Object evaluate = groovyShell.evaluate(function1);
        System.out.println(evaluate);

        String function =" def sum( int a,int b){  a+b }";

        Class aClass = classLoader.parseClass(function);
        try {
            Object [] param = {7,2};
            GroovyObject newInstance = (GroovyObject) aClass.newInstance();
            Object sum = newInstance.invokeMethod("sum", param);
            System.out.println(sum);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test  public void testLanguage(){


        String  shell1 ="      " +
                "def map = [:]\n" +
                "\n" +
                "map.\"an identifier with a space and double quotes\" = \"ALLOWED\"\n" +
                "map.'with-dash-signs-and-single-quotes' = \"ALLOWED\"\n" +
                "\n" + "" +
                " println(\"asdasd\"); "
                +
                "assert map.\"an identifier with a space and double quotes\" == \"ALLOWED\"\n" +
                "assert map.'with-dash-signs-and-single-quotes' == \"ALLOWED\" ;  " +
                "   1 ;" +
                "                          ";
        String shell2 ="def name = 'Groovy'\n" +
                "def template = \"\"\"\n" +
                "    Dear Mr ${name},\n" +
                "\n" +
                "    You're the winner of the lottery!\n" +
                "\n" +
                "    Yours sincerly,\n" +
                "\n" +
                "    Dave\n" +
                "\"\"\"\n" +
                "\n" +
                " println(template);"+
                "assert template.toString().contains('Groovy')";
        String shell3 ="def color = 'blue'\n" +
                "def interpolatedSlashy = /a ${color} car/\n" +
                "\n" +
                "assert interpolatedSlashy == 'a blue car'";
        System.out.println(shell3);
        Object evaluate = groovyShell.evaluate(shell3);

    }
    @Test
    public void testgroovyFile(){
        File file = new File("D:\\javaproject\\重构\\mavenrtest2\\src\\main\\java\\com\\pht\\groovy\\tetst.groovy");
        try {
//            Object evaluate = groovyShell.evaluate(file);
            Class aClass = classLoader.parseClass(file);
            GroovyObject newInstance = (GroovyObject) aClass.newInstance();
            Object test = newInstance.invokeMethod("test", null);
            System.out.println(test);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
