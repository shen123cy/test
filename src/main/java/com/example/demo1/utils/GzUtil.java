package com.example.demo1.utils;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;


public class GzUtil {

    public static void main(String[] args) throws Exception {
        FileReader fileReader = FileReader.create(new File("d:\\新建 Microsoft Excel 工作表.xlsx"));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileReader.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        List<Long> list = new ArrayList<>();

        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            DateTime c1 = DateUtil.parse(row.getCell(0).toString());
            DateTime c2 = DateUtil.parse(row.getCell(1).toString());
            long between = DateUtil.between(c1, c2, DateUnit.SECOND)-5400L;
            list.add(between);
        }
        long sum = list.stream().collect(Collectors.summarizingLong(e -> e)).getSum();
        System.out.println(new BigDecimal(String.valueOf(sum)).divide(new BigDecimal("28800"), 4, HALF_UP));
    }
}
