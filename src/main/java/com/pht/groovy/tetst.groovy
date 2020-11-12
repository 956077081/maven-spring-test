package com.pht.groovy

import groovy.transform.ToString
import org.codehaus.groovy.runtime.InvokerHelper
import sun.nio.fs.WindowsUserPrincipals
trait  Extra{

}
class tetst extends Script{
    @Override
    Object run() {
        println("aadddd");
        return null
    }

    public static void main(String[] args)  {
        InvokerHelper.runScript(tetst,args);
        def var = new person("沾上干", 21);
//        println var;
        def user = new User('Bob')
        assert user?.name == 'Name: Bob'
//        println user.@name;
        String str11 ="pppppppppppp";
        def funtiona =str11.&toUpperCase();
        println(funtiona);
        assert  funtiona==str11.toUpperCase()
//        aa.with {name=name?:"王五"};
//        println aa;
//        String [] strs = new String[]{"pht","age","demo"};
//         for(int i = 0; i <strs.size() ; i++) {
//            println(  strs[i]);
//         }
//        println "asdasd";
    }
     public  def test() {
        println "test";
        return "1111111";
    }

}
class User {
    public final String name
    User(String name) { this.name = name}
    String getName() { "Name: $name" }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
@ToString
class person{
    person(String name, int age) {
        this.name = name
        this.age = age
    }
    String name;
    int age;
}