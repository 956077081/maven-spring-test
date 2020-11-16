package com.pht.drools;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateRules {
    @Test
   public void Test() throws FileNotFoundException {
       File file = new File("C:\\Users\\new\\Desktop\\评分评级_决策表.xls");
       InputStream is = new FileInputStream(file);
       SpreadsheetCompiler converter = new SpreadsheetCompiler();
       String drl = converter.compile(is, InputType.XLS);
       System.out.println("\n\n" + drl);
   }
}
