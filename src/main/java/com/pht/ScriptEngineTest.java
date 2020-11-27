package com.pht;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.List;

public class ScriptEngineTest {
    public static void main(String[] args)  throws Exception{
        //获取脚本引擎对象
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");
    //定义变量,存储到java平台和javascript平台的中间平台，两个平台都可以取到这个变量
        engine.put("msg","smart wang ergou is a good man");
    //javascript
        String str = "var user = {name :'wangErgou',age : 18,schools:['海事大学 ',' 尚学堂 ' ] } ;";
    //打印
        str += "print(user.name);";
    //执行脚本语言
        engine.eval(str);
    //修改msg变量的值
        engine.eval("msg = 'dmu is a good school';");
    //获得msg变量的值
        System.out.println(engine.get("msg"));
    }
    @Test
    public void Test()throws Exception{
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");
    //定义函数
            engine.eval("function add(a,b){var sum = a + b; return sum;}");
    //执行函数
    //取得接口
            Invocable jsInvocable = (Invocable)engine;
    //执行脚本中定义的方法
            Object result = jsInvocable.invokeFunction("add", new Object[] {22,33});
            System.out.println(result);
    }
    @Test
    public void Test2() throws Exception{
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");
        engine.eval("importPackage(java.util); var list = Arrays.asList([\"海事大学  \" ,\"dmu\"])");
        List<String> list = (List<String>) engine.get("list");
        System.out.println(list);
    }
    @Test
    public void  runTimeExceptiontest(){
            try{
                runk();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("asda");
            }
    }
    public void runk(){
        throw new RuntimeException();
    }

}
