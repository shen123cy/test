package com.example.demo1.service.def;

import org.springframework.web.multipart.MultipartFile;

public interface TestService {


    void uploadFile(MultipartFile multipartFile);


    void uploadZip(MultipartFile multipartFile);

    void test();

    void excel(MultipartFile multipartFile);

    void uploadGz(MultipartFile multipartFile)  throws Exception;
}
