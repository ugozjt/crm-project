package com.zjt.crm;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@SpringBootTest
class CrmApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(new Date().toString());

    }

    @Test
    void HssTest() throws IOException {
        //创建HSSFWorkbook对象，对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建excel中的一页
        HSSFSheet sheet = workbook.createSheet("学生列表");
        //放到哪行
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);
        //填入数据
        cell.setCellValue("张三");
        cell = row.createCell(1);
        cell.setCellValue("男");

        FileOutputStream fileOutputStream;

        fileOutputStream = new FileOutputStream("D:\\student.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        workbook.close();
    }

}
