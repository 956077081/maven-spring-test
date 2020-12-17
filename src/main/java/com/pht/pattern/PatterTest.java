package com.pht.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */

/**
 * 正则总结
 *  /d /w对应的大写 与/d /w的的意思相反  非数字 非字符数字 下划线
 *  ^ 为开始符
 *  /w 代表任意 字符 数字 下划线
 *  /d 任意数字
 *  [a-z]     [A-Za-z0-9] 代表范围
 *  () 代表规则组
 *
 *    ？ 代表某个 字符 0个或者1个
 *    * 代表>=0个字符串
 *    + 代表>=1个字符串
 *    {1,}大于等于1个字符串
 *    {2}2个字符串 长度限定
 *    {1,4} 大于1小于等于4个字符串
 *  .  代表非换行符的字符
 *  规则表达式中\\代表   \有特殊意义     \\\\代表 \反斜杠没有特殊意思
 *
 */
public class PatterTest {
    public static  void main(String[] args) {

        String regrex ="^http://[A-Za-z]+(\\.)[A-Za-z]+(\\.)com/(.*)(\\.jsp)\\\\*";
        String value ="http://www.baidu.com/index.jsp";
        value ="http://www.pht.com/asdasd.jsp\\";
        Pattern pattern = Pattern.compile(regrex);
        if(pattern.matcher(value).matches()){
            System.out.println("匹配成功1！");
        }
        if(pattern.matcher(value).find()){
            System.out.println("匹配成功2！");
        }

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(5);

        Collections.sort(list, new Comparator<Integer>() {
             @Override
             public int compare(Integer o1, Integer o2) {
                 System.out.println(o1+"-------"+o2);
                 return o1-o2;//小于0 升序  大于0 降序
             }
         });
        System.out.println(list);
    }
}
