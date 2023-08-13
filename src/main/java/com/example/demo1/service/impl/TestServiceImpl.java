package com.example.demo1.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.demo1.service.def.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    public void excel(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            //do something
            String originalFilename = multipartFile.getOriginalFilename();
            log.info("uploadFile  originalFilename:{}", originalFilename);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void uploadGz(MultipartFile multipartFile) throws Exception {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();

        List<Long> l1 = new ArrayList<>();
        List<Long> l2 = new ArrayList<>();

        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);

            DateTime c1 = DateUtil.parse(row.getCell(0).toString());
            DateTime c2 = DateUtil.parse(row.getCell(1).toString());

            DateTime c3 = DateUtil.parse(row.getCell(2).toString());
            DateTime c4 = DateUtil.parse(row.getCell(3).toString());

            DateTime dateTime1 = DateUtil.parse(c1.toString().substring(0, 10) + " 11:30:00", "yyyy-MM-dd HH:mm:ss");

            long between1 = DateUtil.between(c1, c2, DateUnit.MINUTE);
            long between2 = DateUtil.between(c3, c4, DateUnit.MINUTE);

            if (c1.compareTo(dateTime1) <= 0 && !c1.isWeekend()) {
                between1 = between1 - 120;
            }
            if (c3.compareTo(dateTime1) <= 0 && !c3.isWeekend()) {
                between2 = between2 - 120;
            }
            l1.add(between1);
            l2.add(between2);

        }
        long sum1 = l1.stream().collect(Collectors.summarizingLong(e -> e)).getSum();
        long sum2 = l2.stream().collect(Collectors.summarizingLong(e -> e)).getSum();

        System.out.println(l1);
        System.out.println(sum1);
        System.out.println(l2);
        System.out.println(sum2);

    }



    @Override
    public void uploadFile(MultipartFile multipartFile) {
        //JAVA7 try
        try (InputStream inputStream = multipartFile.getInputStream()) {
            //do something
            String originalFilename = multipartFile.getOriginalFilename();
            log.info("uploadFile  originalFilename:{}", originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadZip(MultipartFile multipartFile) {
        try {
            File file = File.createTempFile("prefix_", multipartFile.getOriginalFilename());
            FileOutputStream stream = new FileOutputStream(file);

            log.info("uploadZip filePath:{}", file.getPath());

           /* multipartFile 转 file  方法1
            容易出现 FileNotFoundException
            multipartFile.transferTo(file);*/

            //multipartFile 转 file   方法2
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
            ZipFile zipFile = new ZipFile(file);
            for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements(); ) {
                ZipEntry zipEntry = entries.nextElement();
                String fileName = zipEntry.getName();//文件名
                log.info("uploadZip fileName:{}", fileName);
                InputStream inputStream = zipFile.getInputStream(zipEntry);//每个文件流
                OutputStream outputStream = new FileOutputStream("D:\\a.txt");
                IOUtils.copy(inputStream, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test() {

    }


    //redisTemplate 表单提交文件
    private void from(MultipartFile multipartFile) {
        try {
            File file = File.createTempFile("prefix_", multipartFile.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
            param.add("my_file", fileSystemResource);
            HttpEntity httpEntity = new HttpEntity(param, httpHeaders);
            // redisTemplate ...
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity("url", httpEntity, String.class);
//            responseEntity.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
